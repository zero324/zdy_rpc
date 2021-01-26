package com.lagou.service;

import com.lagou.handler.UserServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class UserServiceImpl implements IUserService {
    public String sayHello(String word) {
        System.out.println("调用成功   参数是: "+word);
        return "调用成功   参数是: "+word;
    }
    //hostName ip地址  port端口号
    public static void startServer(String hostName,int port) throws InterruptedException {
        //创建NioEventLoopGroup两个实例  bossGroup workGroup
        //当前这两个实例代表两个线程池 默认线程数是cpu核心数乘以2
        //bossGroup 是接收客户端传来的请求
        //workerGroup 是处理请求
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        //创建服务启动辅助类:组装一些必要组件
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                //channel方法   指定服务器监听的通道
                .channel(NioServerSocketChannel.class)
                //设置channel的handler
        .childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new StringEncoder());
                pipeline.addLast(new StringDecoder());
                pipeline.addLast(new UserServerHandler());
            }
        });
        bootstrap.bind(hostName, port).sync();
    }
}
