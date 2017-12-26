/*
 * This file is part of the Epic Dragon World project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.epicdragonworld.gameserver.network;

import java.util.logging.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;

/**
 * @author Pantelis Andrianakis
 */
public class GameClient extends SimpleChannelInboundHandler<byte[]>
{
	private static final Logger LOGGER = Logger.getLogger(GameClient.class.getName());
	
	private Channel _channel;
	private String _ip;
	private String _accountName;
	private PlayerInstance _activeChar;
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx)
	{
		// Connected.
		final Channel incoming = ctx.channel();
		_channel = incoming;
		_ip = incoming.remoteAddress().toString();
		_ip = _ip.substring(1, _ip.lastIndexOf(':')); // Trim out /127.0.0.1:12345
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx)
	{
		// Disconnected.
	}
	
	public void channelSend(SendablePacket packet)
	{
		_channel.writeAndFlush(packet.getSendableBytes());
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, byte[] bytes)
	{
		RecievablePacketManager.handle(this, new ReceivablePacket(Encryption.decrypt(bytes)));
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx)
	{
		LOGGER.finer("Client Disconnected: " + ctx.channel());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
	}
	
	public String getIp()
	{
		return _ip;
	}
	
	public String getAccountName()
	{
		return _accountName;
	}
	
	public void setAccountName(String accountName)
	{
		_accountName = accountName;
	}
	
	public PlayerInstance getActiveChar()
	{
		return _activeChar;
	}
	
	public void setActiveChar(PlayerInstance activeChar)
	{
		_activeChar = activeChar;
	}
}