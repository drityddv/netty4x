package day01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * http支持
 */

public class Server {

	public static void main(String[] args) {

		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();

		serverBootstrap.group(boss, worker)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerInitializer());

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
