/**
* GrpcTransporter.java 2019/8/8 11:49
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.Client;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.Server;
import org.apache.dubbo.remoting.Transporter;

/**
 * File：GrpcTransporter.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcTransporter implements Transporter {
  public static final String NAME = "grpc";

  @Override
  public Server bind(URL url, ChannelHandler listener) throws RemotingException {
    return new GrpcServer(url, listener);
  }

  @Override
  public Client connect(URL url, ChannelHandler listener) throws RemotingException {
    return new GrpcClient(url, listener);
  }
}
