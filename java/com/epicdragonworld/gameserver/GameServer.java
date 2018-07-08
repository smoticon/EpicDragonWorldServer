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
import com.epicdragonworld.gameserver.managers.IdManager;
import com.epicdragonworld.gameserver.managers.ThreadPoolManager;
import com.epicdragonworld.gameserver.network.ClientInitializer;
import com.epicdragonworld.gameserver.network.Encryption;

/**
 * @author Pantelis Andrianakis
 */
public class GameServer
{
	private static final Logger LOGGER = Logger.getLogger(GameServer.class.getName());
	
	// Local Constants
	private static final String LOG_FOLDER = "log"; // Name of folder for log file
	private static final String LOG_NAME = "./log.cfg"; // Name of log file
	
	public static void main(String[] args) throws Exception
	{
		new GameServer();
	}
	
	private GameServer() throws Exception
	{
		// Create log folder
		final File logFolder = new File(".", LOG_FOLDER);
		logFolder.mkdir();
		
		// Create input stream for log file -- or store file data into memory
		try (InputStream is = new FileInputStream(new File(LOG_NAME)))
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
		
		printSection("IdManager");
		IdManager.getInstance();
		
		// Post info.
		printSection("Info");
		LOGGER.info("Server loaded in " + ((System.currentTimeMillis() - serverLoadStart) / 1000) + " seconds.");
		System.gc();
		final long totalMem = Runtime.getRuntime().maxMemory() / 1048576;
		LOGGER.info("Started, using " + getUsedMemoryMB() + " of " + totalMem + " MB total memory.");
		
		// Initialize Network.
		new ServerBootstrap() //
			.group(new NioEventLoopGroup(1), new NioEventLoopGroup(Config.IO_PACKET_THREAD_CORE_SIZE)) //
			.channel(NioServerSocketChannel.class) //
			.childHandler(new ClientInitializer()) //
			.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT) //
			.bind(Config.GAMESERVER_HOSTNAME, Config.GAMESERVER_PORT) //
			.sync();
		LOGGER.info(getClass().getSimpleName() + ": Listening on " + Config.GAMESERVER_HOSTNAME + ":" + Config.GAMESERVER_PORT);
		
		// Notify sound.
		Toolkit.getDefaultToolkit().beep();
	}
	
	private long getUsedMemoryMB()
	{
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
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