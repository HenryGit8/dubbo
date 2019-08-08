/**
* GrpcChannel.java 2019/8/8 14:45
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import java.net.InetSocketAddress;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.transport.AbstractChannel;
import org.apache.dubbo.remoting.transport.grpc.proto.GrpcRequest;

/**
 * File：GrpcChannel.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcChannel extends AbstractChannel {

  public GrpcChannel(URL url,
      ChannelHandler handler) {
    super(url, handler);
  }

  @Override
  public InetSocketAddress getLocalAddress() {
    return null;
  }

  @Override
  public InetSocketAddress getRemoteAddress() {
    return null;
  }

  @Override
  public boolean isConnected() {
    //TODO
    return true;
  }

  @Override
  public void send(Object message, boolean sent) throws RemotingException {

    //TODO
  }

  @Override
  public void close() {

    //TODO
  }

  @Override
  public boolean hasAttribute(String key) {
    //TODO
    return true;
  }

  @Override
  public Object getAttribute(String key) {
    //TODO
    return null;
  }

  @Override
  public void setAttribute(String key, Object value) {
    //session.setAttribute(key, value);
    //TODO
  }

  @Override
  public void removeAttribute(String key) {
    //session.removeAttribute(key);
    //TODO
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    //result = prime * result + ((session == null) ? 0 : session.hashCode());
    //TODO
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
    /*MinaChannel other = (MinaChannel) obj;
    if (session == null) {
      if (other.session != null) {
        return false;
      }
    } else if (!session.equals(other.session)) {
      return false;
    }*/
    return true;
  }

  @Override
  public String toString() {
    return "";
  }
}
