package day01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author : ddv
 * @date : 2019/2/28 上午1:50
 */

public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

//		System.out.println("new conn coming...");

		if (msg instanceof HttpRequest) {

			HttpRequest request = (HttpRequest) msg;

			String url = request.uri();

			if (url.equals("/favicon.ico")) {
				return;
			}

			System.out.println(url);

			ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK, content);

			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

			ctx.writeAndFlush(response);
		}
	}
}
