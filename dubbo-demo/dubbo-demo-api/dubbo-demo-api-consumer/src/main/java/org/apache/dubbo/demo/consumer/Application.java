/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.dubbo.demo.consumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.demo.DemoService;

public class Application {

    private static int re = 0;
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(100, 101, 10l, TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(10000));
    public static void main(String[] args) {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-api-consumer");
        applicationConfig.setQosPort(22344);
        reference.setApplication(applicationConfig);
        reference.setRegistry(new RegistryConfig("zookeeper://106.12.10.34:2181"));
        reference.setInterface(DemoService.class);
        DemoService service = reference.get();
        long before = System.currentTimeMillis();
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        System.out.println(service.sayHello("dubbo"));
        /*for(int i=0;i<2;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    if (service.sayHello("dubbo").equals("true")) {
                        re = re + 1;
                        System.out.println("完成"+re);
                    }
                }
            };
            executor.execute(task);
        }*/
        long after = System.currentTimeMillis();
        System.out.println("总共花费时间："+(after - before)+",完成："+re);
    }
}
