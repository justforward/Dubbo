package com.study.provider.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.study.sdk.pojo.POJO;
import com.study.sdk.pojo.Result;
import com.study.sdk.service.GreetingService;

import java.io.IOException;

/**
 * @author wangtan
 * @Date 2020/12/29
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello"+name+" "+ RpcContext.getContext().getAttachment("company");
    }

    @Override
    public Result<String> testGeneric(POJO pojo) {
        Result<String> result=new Result<>();
        result.setSuccess(true);
        try {
            result.setData(JSON.json(pojo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
