package com.lagou.handler;

import com.lagou.service.UserServiceImpl;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

public class UserServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果符合规则 IUserService#sayHello#are you ok?
        if (msg.toString().startsWith("IUserService")) {
            String result = new UserServiceImpl()
                    .sayHello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }
}
