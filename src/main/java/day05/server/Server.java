package day05.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * webSocket支持
 */

public class Server {

	public static void main(String[] args) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();

		serverBootstrap.group(boss, worker);

		serverBootstrap.channel(NioServerSocketChannel.class);

		//  handler针对boss线程连接处理
		serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
		//  childHandler对worker线程组连接处理
		serverBootstrap.childHandler(new ServerWebSocketInitializer());

		try {
			ChannelFuture future = serverBootstrap.bind(8000).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
