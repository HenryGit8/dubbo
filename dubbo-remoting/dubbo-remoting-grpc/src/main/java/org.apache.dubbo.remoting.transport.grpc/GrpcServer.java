/**
* GrpcServer.java 2019/8/8 11:51
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(GrpcServer.class);

  private io.grpc.Server server;

  private Map<String, Channel> channels;

  public GrpcServer(URL url, ChannelHandler handler)
      throws RemotingException {
    super(url, ChannelHandlers.wrap(handler, ExecutorUtil.setThreadName(url, SERVER_THREAD_POOL_NAME)));
  }

  @Override
  protected void doOpen() throws Throwable {
    GrpcHandler grpcHandler = new GrpcHandler(getUrl(), this);
    server = NettyServerBuilder.forPort(getBindAddress().getPort()).addService(grpcHandler).build().start();
    channels = grpcHandler.getChannels();
    //System.out.println("Server started, listening on " + getBindAddress().getPort());
    logger.info("Grpc server started, listening on " + getBindAddress().getPort());
    //TODO
  }

  @Override
  protected void doClose() throws Throwable {
    server.shutdown();
  }

  @Override
  public boolean isBound() {
    return server.isTerminated();
  }

  @Override
  public Collection<Channel> getChannels() {
    return channels.values();
  }

  @Override
  public Channel getChannel(InetSocketAddress remoteAddress) {
    return channels.get(NetUtils.toAddressString(remoteAddress));
  }
  @Override
  public boolean canHandleIdle(){
    return true;
  }
}
