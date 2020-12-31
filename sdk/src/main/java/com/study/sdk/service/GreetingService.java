package com.study.sdk.service;

import com.study.sdk.pojo.POJO;
import com.study.sdk.pojo.Result;

/**
 * @author wangtan
 * @Date 2020/12/29
 */
public interface GreetingService {
    String sayHello(String name);
    Result<String> testGeneric(POJO pojo);
}
