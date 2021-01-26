package com.lagou.service;

public class ServerBootstrap {
    public static void main(String[] args) throws InterruptedException {
        UserServiceImpl.startServer("localhost", 8990);
    }
}
