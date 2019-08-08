/**
* GrpcClient.java 2019/8/8 11:51
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import java.util.concurrent.TimeUnit;
import org.apache.dubbo.common.URL;
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

  public GrpcClient(URL url, ChannelHandler handler)
      throws RemotingException {
    super(url, handler);
  }

  @Override
  protected void doOpen() throws Throwable {
    //TODO
  }


  @Override
  protected void doConnect() throws Throwable {
    //TODO
  }

  @Override
  protected void doDisConnect() throws Throwable {
    //TODO
  }

  @Override
  protected void doClose() throws Throwable {
    //TODO
  }

  @Override
  protected Channel getChannel() {
    return null;
  }
}
