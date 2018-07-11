package com.epicdragonworld.gameserver;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.epicdragonworld.Config;
import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.managers.ThreadPoolManager;
import com.epicdragonworld.gameserver.network.ClientInitializer;
import com.epicdragonworld.gameserver.network.Encryption;

/**
 * @author Pantelis Andrianakis
 */
public class GameServer
{
	private static final Logger LOGGER = Logger.getLogger(GameServer.class.getName());
	
	public static void main(String[] args) throws Exception
	{
		new GameServer();
	}
	
	private GameServer() throws Exception
	{
		// Create log folder.
		final File logFolder = new File(".", "log");
		logFolder.mkdir();
		
		// Create input stream for log file.
		try (InputStream is = new FileInputStream(new File("./log.cfg")))
		{
			LogManager.getLogManager().readConfiguration(is);
		}
		
		// Keep start time for later.
		final long serverLoadStart = System.currentTimeMillis();
		
		printSection("Configs");
		Config.load();
		
		printSection("Database");
		DatabaseManager.getInstance();
		
		printSection("Encryption");
		Encryption.getInstance();
		
		printSection("ThreadPool");
		ThreadPoolManager.init();
		
		// Post info.
		printSection("Info");
		LOGGER.info("Server loaded in " + ((System.currentTimeMillis() - serverLoadStart) / 1000) + " seconds.");
		System.gc();
		LOGGER.info("Started, using " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576) + " of " + (Runtime.getRuntime().maxMemory() / 1048576) + " MB total memory.");
		
		// Initialize Network.
		new ServerBootstrap() //
			.group(new NioEventLoopGroup(1), new NioEventLoopGroup(Config.IO_PACKET_THREAD_CORE_SIZE)) //
			.channel(NioServerSocketChannel.class) //
			.childHandler(new ClientInitializer()) //
			.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT) //
			.bind(Config.GAMESERVER_HOSTNAME, Config.GAMESERVER_PORT) //
			.sync();
		LOGGER.info("Listening on " + Config.GAMESERVER_HOSTNAME + ":" + Config.GAMESERVER_PORT);
		
		// Notify sound.
		Toolkit.getDefaultToolkit().beep();
	}
	
	private void printSection(String s)
	{
		s = "=[ " + s + " ]";
		while (s.length() < 62)
		{
			s = "-" + s;
		}
		LOGGER.info(s);
	}
}