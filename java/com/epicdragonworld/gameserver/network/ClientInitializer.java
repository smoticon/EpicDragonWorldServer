package com.epicdragonworld.gameserver.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * @author Pantelis Andrianakis
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel>
{
	@Override
	protected void initChannel(SocketChannel ch)
	{
		ChannelPipeline pipeline = ch.pipeline();
		// Decoders.
		pipeline.addLast("decoder", new ByteArrayDecoder());
		pipeline.addLast("encoder", new ByteArrayEncoder());
		// Handle the client.
		pipeline.addLast("clientHandler", new GameClient());
	}
}