package day02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : ddv
 * @date : 2019/3/3 上午12:14
 * Socket服务器
 */

public class Server {

	public static void main(String[] args) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();

		serverBootstrap.group(boss, worker);

		serverBootstrap.channel(NioServerSocketChannel.class);

		//  handler针对boss线程连接处理
//		serverBootstrap.handler(null);
		//  childHandler对worker线程组连接处理
		serverBootstrap.childHandler(new ServerInitializer());

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
