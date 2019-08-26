/**
* GrpcServerChannel.java 2019/8/8 14:45
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.HessianSerializerUtil;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.transport.AbstractChannel;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcRequest;

/**
 * File：GrpcServerChannel.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcClientChannel extends AbstractChannel {

  private static final Logger logger = LoggerFactory.getLogger(GrpcClientChannel.class);

  private InetSocketAddress localAddress;

  private InetSocketAddress remoteAddress;

  private final GreeterGrpc.GreeterStub greeterStub;

  private final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();

  private static final ConcurrentMap<StreamObserver<GrpcReply>, StreamObserver<GrpcRequest>> STREAM_OBSERVER_STREAM_OBSERVER_CONCURRENT_MAP = new ConcurrentHashMap<>();

  private static final ConcurrentMap<GreeterGrpc.GreeterStub, GrpcClientChannel> CHANNEL_MAP = new ConcurrentHashMap<GreeterGrpc.GreeterStub, GrpcClientChannel>();

  private GrpcClientChannel(GreeterGrpc.GreeterStub greeterStub, URL url,
      ChannelHandler handler) {
    super(url, handler);
    if (greeterStub == null) {
      throw new IllegalArgumentException("grpc connection == null");
    }
    this.greeterStub = greeterStub;
  }

  static GrpcClientChannel getOrAddChannel(GreeterGrpc.GreeterStub ch, URL url, ChannelHandler handler) {
    if (ch == null) {
      return null;
    }
    GrpcClientChannel ret = CHANNEL_MAP.get(ch);
    if (ret == null) {
      GrpcClientChannel grpcChannel = new GrpcClientChannel(ch, url, handler);
      ret = CHANNEL_MAP.putIfAbsent(ch, grpcChannel);
      if (ret == null) {
        ret = grpcChannel;
      }
    }
    return ret;
  }

  @Override
  public InetSocketAddress getLocalAddress() {
    return localAddress;
  }

  @Override
  public InetSocketAddress getRemoteAddress() {
    return remoteAddress;
  }

  public void setLocalAddress(InetSocketAddress localAddress) {
    this.localAddress = localAddress;
  }

  public void setRemoteAddress(InetSocketAddress remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  @Override
  public boolean isConnected() {
    return !isClosed();
  }

  @Override
  public void send(Object message, boolean sent) throws RemotingException {
    //System.out.println("客户端即将发送消息："+ message);
    super.send(message, sent);
    boolean success = true;
    int timeout = 0;
    try {
      HashMap rq = new HashMap();
      rq.put("msg", message);
      rq.put("addr", getUrl().getHost());
      rq.put("port", new Random().nextInt(9999 - 3000 + 1) + 3000);
      StreamObserver<GrpcReply> responseObserver = new StreamObserver<GrpcReply>() {
        @Override
        public void onNext(GrpcReply result) {
          ByteString str = result.getData();
          byte[] map = str.toByteArray();
          HashMap hashMap = HessianSerializerUtil.deserialize(map, HashMap.class);
          //System.out.println("客户端接收到消息："+ hashMap);
          try {
            ChannelHandler channelHandler = getChannelHandler();
            GrpcClientChannel channel = GrpcClientChannel.getOrAddChannel(greeterStub, getUrl(), channelHandler);
            channelHandler.received(channel, hashMap.get("msg"));
            grpcRequestStreamObserver(this).onCompleted();
          } catch (RemotingException e){
            e.printStackTrace();
          }
        }
        @Override
        public void onError(Throwable throwable) {
          logger.error("call error:{}", throwable);
        }

        @Override
        public void onCompleted() {
          logger.info("call finish");
        }
      };
      GrpcRequest grpcRequest = GrpcRequest.newBuilder().setData(ByteString.copyFrom(HessianSerializerUtil.serialize(rq))).build();
      //System.out.println("客户端发送请求："+ grpcRequest);
      grpcRequestStreamObserver(responseObserver).onNext(grpcRequest);
    } catch (Throwable e) {
      throw new RemotingException(this, "Failed to send message " + message + " to " + getRemoteAddress() + ", cause: " + e.getMessage(), e);
    }
    if (!success) {
      throw new RemotingException(this, "Failed to send message " + message + " to " + getRemoteAddress()
          + "in timeout(" + timeout + "ms) limit");
    }
  }

  private StreamObserver<GrpcRequest> grpcRequestStreamObserver(StreamObserver<GrpcReply> responseObserver){
    if(STREAM_OBSERVER_STREAM_OBSERVER_CONCURRENT_MAP.get(responseObserver) == null){
      synchronized (this){
        if(STREAM_OBSERVER_STREAM_OBSERVER_CONCURRENT_MAP.get(responseObserver) == null){
          StreamObserver<GrpcRequest> streamObserver = greeterStub.getRp(responseObserver);
          STREAM_OBSERVER_STREAM_OBSERVER_CONCURRENT_MAP.putIfAbsent(responseObserver, streamObserver);
          return streamObserver;
        }
      }
    }
    return STREAM_OBSERVER_STREAM_OBSERVER_CONCURRENT_MAP.get(responseObserver);
  }

  @Override
  public void close() {
    /*for (StreamObserver<GrpcRequest> streamObserver : STREAM_OBSERVER_STREAM_OBSERVER_CONCURRENT_MAP
        .values()) {
      streamObserver.onCompleted();
    }*/
  }

  @Override
  public boolean hasAttribute(String key) {
    return attributes.containsKey(key);
  }

  @Override
  public Object getAttribute(String key) {
    return attributes.get(key);
  }

  @Override
  public void setAttribute(String key, Object value) {
    // The null value is unallowed in the ConcurrentHashMap.
    if (value == null) {
      attributes.remove(key);
    } else {
      attributes.put(key, value);
    }
  }

  @Override
  public void removeAttribute(String key) {
    attributes.remove(key);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((greeterStub == null) ? 0 : greeterStub.hashCode());
    return result;
  }

  static void removeChannelIfDisconnected(GreeterGrpc.GreeterStub connection) {
    if (connection != null) {
      CHANNEL_MAP.remove(connection);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    //TODO
    GrpcClientChannel other = (GrpcClientChannel) obj;
    if (greeterStub == null) {
      if (other.greeterStub != null) {
        return false;
      }
    } else if (!greeterStub.equals(other.greeterStub)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "GrpcClientChannel ";
  }
}
