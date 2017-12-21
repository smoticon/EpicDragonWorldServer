package com.epicdragonworld.gameserver.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author Pantelis Andrianakis
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel>
{
	@Override
	protected void initChannel(SocketChannel ch)
	{
		ChannelPipeline pipeline = ch.pipeline();
		// Decoders
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new ByteArrayEncoder());
		pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(120));
		pipeline.addLast("handler", new ServerHandler());
		pipeline.addLast("ServerTimeoutHandler", new ServerTimeoutHandler());
	}
}