package day02.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

/**
 * @author : ddv
 * @date : 2019/3/3 下午7:49
 */

public class ClientHandler extends SimpleChannelInboundHandler<String> {

	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("response: " + msg);

		Scanner sc = new Scanner(System.in);
		ctx.write(sc.next());
		ctx.flush();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("active...");
		ctx.writeAndFlush("client active...");
//		super.channelActive(ctx);
	}
}
