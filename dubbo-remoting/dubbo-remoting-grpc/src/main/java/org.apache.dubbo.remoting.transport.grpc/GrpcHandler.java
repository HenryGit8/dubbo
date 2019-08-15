/**
 * GrpcHandler.java 2019/8/8 16:58 Copyright ©2019 www.bmsoft.com.cn All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.apache.dubbo.remoting.transport.grpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.exchange.Request;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcRequest;
import org.apache.dubbo.rpc.RpcInvocation;

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
      GrpcServerChannel channel = GrpcServerChannel.getOrAddChannel(responseObserver, url, handler);

      @Override
      public void onNext(GrpcRequest grpcRequest) {
        channel.setLocalAddress(new InetSocketAddress(url.getPort()));
        String grpcRequestData = grpcRequest.getData();
        JSONObject jsonObject = JSONObject.parseObject(grpcRequestData);
        String msgType = jsonObject.getString("msgType");
        JSONObject msg = jsonObject.getJSONObject("msg");
        Request request = new Request();
        if(Request.class.getName().equals(msgType)){
          request.setBroken(msg.getBoolean("broken"));
          if(msg.getBoolean("event")){
            request.setEvent(msg.getString("data"));
          }
          request.setTwoWay(msg.getBoolean("twoWay"));
          request.setVersion(msg.getString("version"));
          try{
            request.setData(codeRpc(msg.getJSONObject("data")));
          }catch (Exception e){
            e.printStackTrace();
          }
          request.setHeartbeat(msg.getBoolean("heartbeat"));
        }else {
          System.out.println("msgType");
        }
        System.out.println("服务器接收到消息："+jsonObject.toJSONString());
        String addr = jsonObject.getString("addr");
        Integer port = jsonObject.getInteger("port");
        InetSocketAddress inetSocketAddress = new InetSocketAddress(addr, port);
        channel.setRemoteAddress(inetSocketAddress);
        try {
          if (channel != null) {
            channels.put(NetUtils.toAddressString(inetSocketAddress), channel);
          }
          handler.received(channel, request);
        } catch (RemotingException remotingException) {
          remotingException.printStackTrace();
        }
      }

      @Override
      public void onError(Throwable throwable) {
        try {
          handler.disconnected(channel);
        } catch (RemotingException remotingException) {
          remotingException.printStackTrace();
        } finally {
          GrpcServerChannel.removeChannelIfDisconnected(responseObserver);
        }
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
    return streamObserver;
  }

  private RpcInvocation codeRpc(JSONObject data) throws Exception{
    RpcInvocation rpcInvocation = new RpcInvocation();
    JSONObject attachmentsjs = data.getJSONObject("attachments");
    HashMap attachments = new HashMap();
    for (String s : attachmentsjs.keySet()) {
      attachments.put(s, attachmentsjs.getString(s));
    }
    rpcInvocation.setAttachments(attachments);
    JSONArray parameterTypesjs = data.getJSONArray("parameterTypes");
    Class<?>[] classes = new Class[parameterTypesjs.size()];
    for (int i = 0; i < parameterTypesjs.size(); i++) {
      classes[i] = Class.forName(parameterTypesjs.getString(i));
    }
    rpcInvocation.setParameterTypes(classes);
    rpcInvocation.setMethodName(data.getString("methodName"));
    JSONArray argumentsjs = data.getJSONArray("arguments");
    Object[] arguments = new Object[argumentsjs.size()];
    for (int i = 0; i < argumentsjs.size(); i++) {
      arguments[i] = argumentsjs.get(i);
    }
    rpcInvocation.setArguments(arguments);

    return rpcInvocation;
  }

  public Map<String, Channel> getChannels() {
    return channels;
  }
}
