package day04.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author : ddv
 * @date : 2019/3/3 上午12:36
 */

public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;

			String eventType;

			switch (event.state()) {
				case READER_IDLE: {
					eventType = "read free...";
					break;
				}
				case WRITER_IDLE: {
					eventType = "write free...";
					break;
				}
				case ALL_IDLE: {
					eventType = "read and write free...";
					break;
				}
				default: {
					eventType = "";
					break;
				}
			}

			System.out.println(ctx.channel().remoteAddress() + " " + eventType);
			ctx.channel().close();
		}
	}
}
