/**
* GrpcServerChannel.java 2019/8/8 14:45
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import com.alibaba.fastjson.JSONObject;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.net.InetSocketAddress;
import java.util.Map;
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
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.Builder;

/**
 * File：GrpcServerChannel.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcServerChannel extends AbstractChannel {

  private static final Logger logger = LoggerFactory.getLogger(GrpcServerChannel.class);

  private InetSocketAddress localAddress;

  private InetSocketAddress remoteAddress;

  private final StreamObserver<GrpcReply> grpcReplyStreamObserver;

  private final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();

  private static final ConcurrentMap<StreamObserver<GrpcReply>, GrpcServerChannel> CHANNEL_MAP = new ConcurrentHashMap<StreamObserver<GrpcReply>, GrpcServerChannel>();

  private GrpcServerChannel(StreamObserver<GrpcReply> grpcReplyStreamObserver, URL url,
      ChannelHandler handler) {
    super(url, handler);
    if (grpcReplyStreamObserver == null) {
      throw new IllegalArgumentException("grpc connection == null");
    }
    this.grpcReplyStreamObserver = grpcReplyStreamObserver;
  }

  static GrpcServerChannel getOrAddChannel(StreamObserver<GrpcReply> ch, URL url, ChannelHandler handler) {
    if (ch == null) {
      return null;
    }
    GrpcServerChannel ret = CHANNEL_MAP.get(ch);
    if (ret == null) {
      GrpcServerChannel grpcChannel = new GrpcServerChannel(ch, url, handler);
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
    System.out.println("发送消息："+ message);
    super.send(message, sent);
    boolean success = true;
    int timeout = 0;

    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("msg", HessianSerializerUtil.serialize(message));
      jsonObject.put("addr", getUrl().getHost());
      jsonObject.put("port", getUrl().getPort());
      String str = jsonObject.toJSONString();
      Builder response = GrpcReply.newBuilder().setData(str);
      System.out.println("json："+ str);
      grpcReplyStreamObserver.onNext(response.build());
    } catch (Throwable e) {
      throw new RemotingException(this, "Failed to send message " + message + " to " + getRemoteAddress() + ", cause: " + e.getMessage(), e);
    }
    if (!success) {
      throw new RemotingException(this, "Failed to send message " + message + " to " + getRemoteAddress()
          + "in timeout(" + timeout + "ms) limit");
    }
  }

  @Override
  public void close() {
    grpcReplyStreamObserver.onCompleted();
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

  static void removeChannelIfDisconnected(StreamObserver<GrpcReply> streamObserver) {
    if (streamObserver != null) {
      CHANNEL_MAP.remove(streamObserver);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((grpcReplyStreamObserver == null) ? 0 : grpcReplyStreamObserver.hashCode());
    return result;
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
    GrpcServerChannel other = (GrpcServerChannel) obj;
    if (grpcReplyStreamObserver == null) {
      if (other.grpcReplyStreamObserver != null) {
        return false;
      }
    } else if (!grpcReplyStreamObserver.equals(other.grpcReplyStreamObserver)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "";
  }
}
