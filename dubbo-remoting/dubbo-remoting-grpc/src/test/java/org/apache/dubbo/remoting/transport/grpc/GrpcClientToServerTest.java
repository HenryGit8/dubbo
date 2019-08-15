/**
* GrpcClientToServerTest.java 2019/8/13 17:01
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.remoting.transport.grpc;

import static org.apache.dubbo.common.constants.CommonConstants.GROUP_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.INTERFACE_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.PATH_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.VERSION_KEY;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.Constants;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.exchange.ExchangeChannel;
import org.apache.dubbo.remoting.exchange.ExchangeHandler;
import org.apache.dubbo.remoting.exchange.ExchangeServer;
import org.apache.dubbo.remoting.exchange.Exchangers;
import org.apache.dubbo.remoting.exchange.support.ExchangeHandlerAdapter;
import org.apache.dubbo.remoting.exchange.support.Replier;

/**
 * File：GrpcClientToServerTest.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class GrpcClientToServerTest {


  protected static ExchangeServer newServer(int port, Replier<?> receiver) throws RemotingException {
    // add heartbeat cycle to avoid unstable ut.
    URL url = URL.valueOf("exchange://localhost:" + port + "?server=grpc");
    url = url.addParameter(Constants.HEARTBEAT_KEY, 600 * 1000);
    return Exchangers.bind(url, receiver);
  }
  protected static ExchangeServer newServer(int port, ExchangeHandler handler) throws RemotingException {
    // add heartbeat cycle to avoid unstable ut.
    URL url = URL.valueOf("exchange://localhost:" + port + "?server=grpc");
    url = url.addParameter(Constants.HEARTBEAT_KEY, 600 * 1000);
    return Exchangers.bind(url, handler);
  }

  protected static ExchangeChannel newClient(int port) throws RemotingException {
    // add heartbeat cycle to avoid unstable ut.
    URL url = URL.valueOf("exchange://localhost:" + port + "?client=grpc&timeout=3000");
    url = url.addParameter(Constants.HEARTBEAT_KEY, 600 * 1000);
    return Exchangers.connect(url);
  }

  public static void main(String[] args) {
    ExchangeHandler requestHandler = new ExchangeHandlerAdapter() {

      @Override
      public CompletableFuture<Object> reply(ExchangeChannel channel, Object message)
          throws RemotingException {
        System.out.println(message);
        return new CompletableFuture<>();
      }
    };
    try {
      ExchangeServer exchangeServer = newServer(24933, requestHandler);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
