package com.lagou.client;

import com.lagou.service.IUserService;
import jdk.nashorn.internal.ir.WhileNode;

public class ClientBootStrap {
    public  static final String providerName="IUserService#sayHello";
    public static void main(String[] args) throws InterruptedException {
        RpcConsumer rpcConsumer = new RpcConsumer();
        IUserService proxy = (IUserService)rpcConsumer.createProxy(IUserService.class, providerName);
        while(true){
            Thread.sleep(2000);
            System.out.println(proxy.sayHello("are you ok ?"));
        }
    }
}
