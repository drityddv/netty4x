package day01;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author : ddv
 * @date : 2019/2/28 上午1:47
 */

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast("httpServerCodec", new HttpServerCodec());
		pipeline.addLast("httpServerHandler", new MyHttpServerHandler());


	}
}
