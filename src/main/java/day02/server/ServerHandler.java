package day02.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author : ddv
 * @date : 2019/3/3 上午12:36
 */

public class ServerHandler extends SimpleChannelInboundHandler<String> {

	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("request: " + msg);
		ctx.writeAndFlush(msg.toUpperCase());
	}
}
