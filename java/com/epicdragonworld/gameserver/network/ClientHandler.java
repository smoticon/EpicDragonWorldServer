package com.epicdragonworld.gameserver.network;

import java.util.logging.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Pantelis Andrianakis
 */
public class ClientHandler extends SimpleChannelInboundHandler<String>
{
	private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());
	private static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx)
	{
		Channel incoming = ctx.channel();
		LOGGER.info(getClass().getSimpleName() + ": New connection[" + incoming.remoteAddress() + "]");
		CHANNELS.add(ctx.channel());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx)
	{
		Channel incoming = ctx.channel();
		LOGGER.info(getClass().getSimpleName() + ": Connection closed! [" + incoming.remoteAddress() + "]");
		CHANNELS.remove(ctx.channel());
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1)
	{
		// TODO:
		// Channel incoming = arg0.channel();
	}
}