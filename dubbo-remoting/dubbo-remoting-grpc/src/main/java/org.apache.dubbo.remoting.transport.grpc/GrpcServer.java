/**
* GrpcServer.java 2019/8/8 11:51
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.ExecutorUtil;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.Server;
import org.apache.dubbo.remoting.transport.AbstractServer;
import org.apache.dubbo.remoting.transport.dispatcher.ChannelHandlers;

/**
 * File：GrpcServer.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcServer extends AbstractServer implements Server {

  public GrpcServer(URL url, ChannelHandler handler)
      throws RemotingException {
    super(url, ChannelHandlers.wrap(handler, ExecutorUtil.setThreadName(url, SERVER_THREAD_POOL_NAME)));
  }

  @Override
  protected void doOpen() throws Throwable {

    //TODO
  }

  @Override
  protected void doClose() throws Throwable {
    //TODO
  }

  @Override
  public boolean isBound() {
    //TODO
    return true;
  }

  @Override
  public Collection<Channel> getChannels() {
    //TODO
    return null;
  }

  @Override
  public Channel getChannel(InetSocketAddress remoteAddress) {
    //TODO
    return null;
  }

  @Override
  public void connected(Channel ch) throws RemotingException {
    //TODO
  }

  @Override
  public void disconnected(Channel ch) throws RemotingException {
    //TODO
  }
}
