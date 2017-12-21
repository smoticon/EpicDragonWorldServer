package com.epicdragonworld.gameserver.network;

import java.util.logging.Logger;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;

/**
 * Handles a ReadTimeoutException.
 * @author Pantelis Andrianakis
 */
public class ServerTimeoutHandler extends ChannelDuplexHandler
{
	private static final Logger LOGGER = Logger.getLogger(ServerHandler.class.getName());
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		if (cause instanceof ReadTimeoutException)
		{
			LOGGER.info(getClass().getName() + " Read time out exception!");
			// TODO:
		}
		else
		{
			LOGGER.info(getClass().getName() + " Read time out!");
			// TODO:
		}
	}
}