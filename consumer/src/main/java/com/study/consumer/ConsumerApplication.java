package com.study.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.study.sdk.service.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        //9) 创建服务引用对象实例
        ReferenceConfig<GreetingService> referenceConfig=new ReferenceConfig<>();
        //10) 设置应用程序信息
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        //11) 设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        // 12) 设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);
        //13）设置自定义负载均衡策略和集群容错策略
        //referenceConfig.setLoadbalance("myLoadBalance");
        //referenceConfig.setCluster("myBroadcast");

        //14)设置服务分组与版本
        referenceConfig.setVersion("2.6.2");
        referenceConfig.setGroup("dubbo");

        //15)引用服务
        GreetingService greetingService=referenceConfig.get();

        //16)设置隐式参数:服务提供者就可以在服务实现类方法里获取参数值
        RpcContext.getContext().setAttachment("company","alibaba");

        //17) 调用服务，同步发起远程调用，然后当前线程会被阻塞知道服务提供方把结果返回。
        System.out.println(greetingService.sayHello("world"));

        //SpringApplication.run(ConsumerApplication.class, args);
    }

}
