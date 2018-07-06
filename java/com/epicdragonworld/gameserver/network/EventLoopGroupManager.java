package com.epicdragonworld.gameserver.network;

import io.netty.channel.nio.NioEventLoopGroup;

import com.epicdragonworld.Config;

/**
 * @author Nos
 */
public class EventLoopGroupManager
{
	private final NioEventLoopGroup _bossGroup = new NioEventLoopGroup(1);
	private final NioEventLoopGroup _workerGroup = new NioEventLoopGroup(Config.IO_PACKET_THREAD_CORE_SIZE);
	
	public NioEventLoopGroup getBossGroup()
	{
		return _bossGroup;
	}
	
	public NioEventLoopGroup getWorkerGroup()
	{
		return _workerGroup;
	}
	
	public void shutdown()
	{
		_bossGroup.shutdownGracefully();
		_workerGroup.shutdownGracefully();
	}
	
	public static EventLoopGroupManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final EventLoopGroupManager _instance = new EventLoopGroupManager();
	}
}
