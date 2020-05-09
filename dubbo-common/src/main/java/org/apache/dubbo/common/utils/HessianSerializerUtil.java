/**
* HessianSerializerUtil.java 2019/8/19 10:01
* Copyright ©2019 www.bmsoft.com.cn All rights reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.apache.dubbo.common.utils;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * File：HessianSerializerUtil.java<br>
 * Title: <br>
 * Description: <br>
 * Company: www.bmsoft.com.cn <br>
 * @author heyouchi
 */
public class HessianSerializerUtil {
  public static <T> byte[] serialize(T obj) {
    byte[] bytes = null;
    // 1、创建字节输出流
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    // 2、对字节数组流进行再次封装

    // step 1. 定义外部序列化工厂
    //ExtSerializerFactory extSerializerFactory = new ExtSerializerFactory();
    //extSerializerFactory.addSerializer(java.time.OffsetDateTime.class, new OffsetDateTimeRedisSerializer());
    //extSerializerFactory.addDeserializer(java.time.OffsetDateTime.class, new OffsetDateTimeRedisDeserializer());
    // step 2. 序列化工厂
    //SerializerFactory serializerFactory = new SerializerFactory();
    //serializerFactory.addFactory(extSerializerFactory);

    HessianOutput hessianOutput = new HessianOutput(bos);
    //hessianOutput.setSerializerFactory(serializerFactory);

    try {
      // 注意，obj 必须实现Serializable接口
      hessianOutput.writeObject(obj);
      bytes = bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return bytes;
  }

  public static <T> T deserialize(byte[] data, Class T) {
    if (data == null) {
      return null;
    }
    // 1、将字节数组转换成字节输入流
    ByteArrayInputStream bis = new ByteArrayInputStream(data);

    // step 1. 定义外部序列化工厂
    //ExtSerializerFactory extSerializerFactory = new ExtSerializerFactory();
    //extSerializerFactory.addSerializer(java.time.OffsetDateTime.class, new OffsetDateTimeRedisSerializer());
    //extSerializerFactory.addDeserializer(java.time.OffsetDateTime.class, new OffsetDateTimeRedisDeserializer());
    // step 2. 序列化工厂
    //SerializerFactory serializerFactory = new SerializerFactory();
    //serializerFactory.addFactory(extSerializerFactory);
    HessianInput hessianInput = new HessianInput(bis);
    //hessianInput.setSerializerFactory(serializerFactory);
    Object object = null;

    try {
      object = hessianInput.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return (T) object;
  }
}
