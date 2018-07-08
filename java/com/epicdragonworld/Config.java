package com.epicdragonworld;

import com.epicdragonworld.util.ConfigReader;

/**
 * @author Pantelis Andrianakis
 */
public final class Config
{
	// --------------------------------------------------
	// Constants
	// --------------------------------------------------
	public static final String EOL = System.lineSeparator();
	
	// --------------------------------------------------
	// Config File Definitions
	// --------------------------------------------------
	private static final String ACCOUNTS_CONFIG_FILE = "./config/Accounts.ini";
	private static final String SERVER_CONFIG_FILE = "./config/Server.ini";
	
	// --------------------------------------------------
	// Accounts
	// --------------------------------------------------
	public static boolean ACCOUNT_AUTO_CREATE;
	public static int ACCOUNT_MAX_CHARACTERS;
	
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
	
	public static void load()
	{
		final ConfigReader accountsConfigs = new ConfigReader(ACCOUNTS_CONFIG_FILE);
		ACCOUNT_AUTO_CREATE = accountsConfigs.getBoolean("AccountAutoCreate", false);
		ACCOUNT_MAX_CHARACTERS = accountsConfigs.getInt("AccountMaxCharacters", 5);
		
		final ConfigReader serverConfigs = new ConfigReader(SERVER_CONFIG_FILE);
		GAMESERVER_PORT = serverConfigs.getInt("GameserverPort", 5055);
		GAMESERVER_HOSTNAME = serverConfigs.getString("GameserverHostname", "0.0.0.0");
		DATABASE_DRIVER = serverConfigs.getString("Driver", "com.mysql.jdbc.Driver");
		DATABASE_URL = serverConfigs.getString("URL", "jdbc:mysql://localhost/edws");
		DATABASE_LOGIN = serverConfigs.getString("Login", "root");
		DATABASE_PASSWORD = serverConfigs.getString("Password", "");
		DATABASE_MAX_CONNECTIONS = serverConfigs.getInt("MaximumDbConnections", 100);
		DATABASE_MAX_IDLE_TIME = serverConfigs.getInt("MaximumDbIdleTime", 0);
		SCHEDULED_THREAD_POOL_COUNT = serverConfigs.getInt("ScheduledThreadPoolCount", -1);
		THREADS_PER_SCHEDULED_THREAD_POOL = serverConfigs.getInt("ThreadsPerScheduledThreadPool", 4);
		INSTANT_THREAD_POOL_COUNT = serverConfigs.getInt("InstantThreadPoolCount", -1);
		THREADS_PER_INSTANT_THREAD_POOL = serverConfigs.getInt("ThreadsPerInstantThreadPool", 2);
		IO_PACKET_THREAD_CORE_SIZE = serverConfigs.getInt("UrgentPacketThreadCoreSize", 2);
		MAXIMUM_ONLINE_USERS = serverConfigs.getInt("MaximumOnlineUsers", 2000);
	}
}
