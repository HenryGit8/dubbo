/**
* GrpcClient.java 2019/8/8 11:51
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.transport.AbstractClient;

/**
 * File：GrpcClient.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcClient extends AbstractClient {

  private volatile GreeterGrpc.GreeterStub greeterStub;

  private volatile ManagedChannel managedChannel;

  public GrpcClient(URL url, ChannelHandler handler)
      throws RemotingException {
    super(url, handler);
  }

  @Override
  protected void doOpen() throws Throwable {
    managedChannel = ManagedChannelBuilder.forAddress(getUrl().getHost(),
        getUrl().getPort()).usePlaintext().build();
  }


  @Override
  protected void doConnect() throws Throwable {
    greeterStub = GreeterGrpc.newStub(managedChannel);
  }

  @Override
  protected void doDisConnect() throws Throwable {
    managedChannel.shutdown();
  }

  @Override
  protected void doClose() throws Throwable {
    managedChannel.shutdownNow();
  }

  @Override
  protected Channel getChannel() {
    GreeterGrpc.GreeterStub c = greeterStub;
    if (c == null) {
      return null;
    }
    Channel channel = GrpcClientChannel.getOrAddChannel(c, getUrl(), this);
    ((GrpcClientChannel) channel).setRemoteAddress(new InetSocketAddress(NetUtils.filterLocalHost(getUrl().getHost()), getUrl().getPort()));
    return channel;
  }
}
