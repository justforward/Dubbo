package com.study.sdk.service;

import java.util.concurrent.CompletableFuture;

/**
 * 主要用来演示基于定义CompletableFuture签名的接口如何实现异步执行
 */
public interface GrettingServiceAsync {
    CompletableFuture<String> sayHello(String name);
}
