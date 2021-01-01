package com.study.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.study.provider.service.impl.GreetingServiceImpl;
import com.study.sdk.service.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) throws IOException {
        //1)创建serviceConfig实例
        ServiceConfig<GreetingService> serviceConfig=new ServiceConfig<>();
        //2)设置应用程序配置
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        //3)设置服务注册中心信息
        RegistryConfig registryConfig=new RegistryConfig("zookeeper://127.0.0.1:2181");
        serviceConfig.setRegistry(registryConfig);
        //4)设置接口与实现类
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());

        //5)设置服务分组与版本:在dubbo中："服务接口+服务分组+服务版本"唯一地确定一个服务，
        //同一个服务接口可以有不同的版本以便服务升级。每个服务接口可以属于不同的分组。
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setGroup("dubbo");

        //6)设置线程池策略
//        HashMap<String ,String> parameters=new HashMap<>();
//        parameters.put("threadpool","mythreadpool");
//        serviceConfig.setParameters(parameters);

        //7）导出服务，启动NettyServer监听链接请求，并将服务注册到服务注册中心
        serviceConfig.export();

        //8)挂起线程，避免服务停止
        System.out.println("server is started");
        System.in.read();
        
        //SpringApplication.run(ProviderApplication.class, args);
    }

}
