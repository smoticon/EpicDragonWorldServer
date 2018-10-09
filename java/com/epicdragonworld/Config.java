package com.epicdragonworld;

import java.util.logging.Logger;

import com.epicdragonworld.gameserver.model.Location;
import com.epicdragonworld.util.ConfigReader;

/**
 * @author Pantelis Andrianakis
 */
public final class Config
{
	// --------------------------------------------------
	// Constants
	// --------------------------------------------------
	private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
	public static final String EOL = System.lineSeparator();
	
	// --------------------------------------------------
	// Config File Definitions
	// --------------------------------------------------
	private static final String ACCOUNT_CONFIG_FILE = "./config/Account.ini";
	private static final String LOGGING_CONFIG_FILE = "./config/Logging.ini";
	private static final String PLAYER_CONFIG_FILE = "./config/Player.ini";
	private static final String SERVER_CONFIG_FILE = "./config/Server.ini";
	
	// --------------------------------------------------
	// Accounts
	// --------------------------------------------------
	public static boolean ACCOUNT_AUTO_CREATE;
	public static int ACCOUNT_MAX_CHARACTERS;
	
	// --------------------------------------------------
	// Logging
	// --------------------------------------------------
	public static boolean LOG_CHAT;
	public static boolean LOG_WORLD;
	
	// --------------------------------------------------
	// Player
	// --------------------------------------------------
	public static Location STARTING_LOCATION;
	
	// --------------------------------------------------
	// Server
	// --------------------------------------------------
	public static int GAMESERVER_PORT;
	public static String GAMESERVER_HOSTNAME;
	public static String DATABASE_DRIVER;
	public static String DATABASE_URL;
	public static String DATABASE_LOGIN;
	public static String DATABASE_PASSWORD;
	public static int DATABASE_MAX_CONNECTIONS;
	public static int DATABASE_MAX_IDLE_TIME;
	public static int SCHEDULED_THREAD_POOL_COUNT;
	public static int THREADS_PER_SCHEDULED_THREAD_POOL;
	public static int INSTANT_THREAD_POOL_COUNT;
	public static int THREADS_PER_INSTANT_THREAD_POOL;
	public static int IO_PACKET_THREAD_CORE_SIZE;
	public static int MAXIMUM_ONLINE_USERS;
	public static double CLIENT_VERSION;
	
	public static void load()
	{
		final ConfigReader accountConfigs = new ConfigReader(ACCOUNT_CONFIG_FILE);
		ACCOUNT_AUTO_CREATE = accountConfigs.getBoolean("AccountAutoCreate", false);
		ACCOUNT_MAX_CHARACTERS = accountConfigs.getInt("AccountMaxCharacters", 5);
		
		final ConfigReader loggingConfigs = new ConfigReader(LOGGING_CONFIG_FILE);
		LOG_CHAT = loggingConfigs.getBoolean("LogChat", true);
		LOG_WORLD = loggingConfigs.getBoolean("LogWorld", true);
		
		final ConfigReader playerConfigs = new ConfigReader(PLAYER_CONFIG_FILE);
		final String[] startingLocation = playerConfigs.getString("StartingLocation", "9945.9;9.2;10534.9").split(";");
		STARTING_LOCATION = new Location(Float.parseFloat(startingLocation[0]), Float.parseFloat(startingLocation[1]), Float.parseFloat(startingLocation[2]), startingLocation.length > 3 ? Integer.parseInt(startingLocation[3]) : 0);
		
		final ConfigReader serverConfigs = new ConfigReader(SERVER_CONFIG_FILE);
		GAMESERVER_PORT = serverConfigs.getInt("GameserverPort", 5055);
		GAMESERVER_HOSTNAME = serverConfigs.getString("GameserverHostname", "0.0.0.0");
		DATABASE_DRIVER = serverConfigs.getString("Driver", "org.mariadb.jdbc.Driver");
		DATABASE_URL = serverConfigs.getString("URL", "jdbc:mariadb://localhost/edws");
		DATABASE_LOGIN = serverConfigs.getString("Login", "root");
		DATABASE_PASSWORD = serverConfigs.getString("Password", "");
		DATABASE_MAX_CONNECTIONS = serverConfigs.getInt("MaximumDbConnections", 100);
		DATABASE_MAX_IDLE_TIME = serverConfigs.getInt("MaximumDbIdleTime", 0);
		SCHEDULED_THREAD_POOL_COUNT = serverConfigs.getInt("ScheduledThreadPoolCount", -1);
		if (SCHEDULED_THREAD_POOL_COUNT == -1)
		{
			SCHEDULED_THREAD_POOL_COUNT = Runtime.getRuntime().availableProcessors();
		}
		THREADS_PER_SCHEDULED_THREAD_POOL = serverConfigs.getInt("ThreadsPerScheduledThreadPool", 4);
		INSTANT_THREAD_POOL_COUNT = serverConfigs.getInt("InstantThreadPoolCount", -1);
		if (INSTANT_THREAD_POOL_COUNT == -1)
		{
			INSTANT_THREAD_POOL_COUNT = Runtime.getRuntime().availableProcessors();
		}
		THREADS_PER_INSTANT_THREAD_POOL = serverConfigs.getInt("ThreadsPerInstantThreadPool", 2);
		IO_PACKET_THREAD_CORE_SIZE = serverConfigs.getInt("UrgentPacketThreadCoreSize", 2);
		MAXIMUM_ONLINE_USERS = serverConfigs.getInt("MaximumOnlineUsers", 2000);
		CLIENT_VERSION = serverConfigs.getDouble("ClientVersion", 1.0);
		
		LOGGER.info("Configs: Initialized.");
	}
}
