/**
* GrpcHandler.java 2019/8/8 16:58
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import com.alibaba.fastjson.JSONObject;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcRequest;

/**
 * File：GrpcHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcHandler extends GreeterGrpc.GreeterImplBase {

  private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>();

  private final URL url;

  private final ChannelHandler handler;

  public GrpcHandler(URL url, ChannelHandler handler) {
    this.url = url;
    this.handler = handler;
  }

  @Override
  public StreamObserver<GrpcRequest> getRp(StreamObserver<GrpcReply> responseObserver) {
    StreamObserver<GrpcRequest> streamObserver = new StreamObserver<GrpcRequest>() {
      @Override
      public void onNext(GrpcRequest grpcRequest) {
        GrpcServerChannel channel = GrpcServerChannel.getOrAddChannel(responseObserver, url, handler);
        channel.setLocalAddress(new InetSocketAddress(url.getPort()));
        String grpcRequestData = grpcRequest.getData();
        JSONObject jsonObject = JSONObject.parseObject(grpcRequestData);
        Object msg = jsonObject.get("msg");
        InetSocketAddress inetSocketAddress = jsonObject.getObject("addr", InetSocketAddress.class);
        channel.setRemoteAddress(inetSocketAddress);
        try {
          if (channel != null) {
            channels.put(NetUtils.toAddressString(inetSocketAddress), channel);
          }
          handler.received(channel, msg);
        } catch (RemotingException remotingException){
          remotingException.printStackTrace();
        }
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
    return streamObserver;
  }

  public Map<String, Channel> getChannels() {
    return channels;
  }
}
