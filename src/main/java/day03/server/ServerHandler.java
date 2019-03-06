package day03.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author : ddv
 * @date : 2019/3/3 上午12:36
 */

public class ServerHandler extends SimpleChannelInboundHandler<String> {

	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		channelGroup.forEach(ch -> {
			if (ch != ctx.channel()) {
				ch.writeAndFlush("[" + ctx.channel().remoteAddress() + "]: " + msg + "\n");
			}
		});
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + "加入聊天室! \n");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + "离开聊天室! \n");
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + "上线! \n");
		channelGroup.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("[服务器]: " + ctx.channel().remoteAddress() + "下线! \n");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
