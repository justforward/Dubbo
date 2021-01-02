package com.study.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.dubbo.FutureAdapter;
import com.study.sdk.service.GreetingService;

import java.util.concurrent.ExecutionException;

/**
 * @author wangtan
 * @Date 2021/1/1
 */
public class ApiAsyncConsumer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1)创建引用实例，并设置属性
        ReferenceConfig<GreetingService> referenceConfig=new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");

        //2) 设置为异步
        referenceConfig.setAsync(true);

        //3)直接返回null
        GreetingService greetingService=referenceConfig.get();
        System.out.println(greetingService.sayHello("world"));

        //4) 等待结果:
        java.util.concurrent.Future<String> future= RpcContext.getContext().getFuture();

        //future.get() 会阻塞线程直到结果返回
       // System.out.println(future.get());

        //上述的接口 线程调用future.get() 会被阻塞，所以Dubbo提供了在future对象上设置回调函数的方法，实现真正的异步调用

        //4) 异步执行回调
        ((FutureAdapter)RpcContext.getContext().getFuture()).getFuture().setCallback(new ResponseCallback() {
            @Override
            public void done(Object response) {
                //当远端正常返回响应结果后，会执行该方法。其参数response就是响应结果值；
                System.out.println("result"+response);
            }

            @Override
            public void caught(Throwable exception) {
                //当发起远程调用发生错误时会调用该方法以打印错误信息
                System.out.println("error: "+exception.getLocalizedMessage());
            }
        });
        Thread.currentThread().join();
    }
}
