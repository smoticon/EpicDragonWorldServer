package com.epicdragonworld;

import com.epicdragonworld.util.PropertiesParser;

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
	public static final String SERVER_CONFIG_FILE = "./config/Server.ini";
	
	// --------------------------------------------------
	// Server
	// --------------------------------------------------
	public static int GAME_PORT;
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
	
	public static void load()
	{
		final PropertiesParser serverSettings = new PropertiesParser(SERVER_CONFIG_FILE);
		GAME_PORT = serverSettings.getInt("GamePort", 5055);
		DATABASE_DRIVER = serverSettings.getString("Driver", "com.mysql.jdbc.Driver");
		DATABASE_URL = serverSettings.getString("URL", "jdbc:mysql://localhost/edws");
		DATABASE_LOGIN = serverSettings.getString("Login", "root");
		DATABASE_PASSWORD = serverSettings.getString("Password", "");
		DATABASE_MAX_CONNECTIONS = serverSettings.getInt("MaximumDbConnections", 10);
		DATABASE_MAX_IDLE_TIME = serverSettings.getInt("MaximumDbIdleTime", 0);
		SCHEDULED_THREAD_POOL_COUNT = serverSettings.getInt("ScheduledThreadPoolCount", -1);
		THREADS_PER_SCHEDULED_THREAD_POOL = serverSettings.getInt("ThreadsPerScheduledThreadPool", 4);
		INSTANT_THREAD_POOL_COUNT = serverSettings.getInt("InstantThreadPoolCount", -1);
		THREADS_PER_INSTANT_THREAD_POOL = serverSettings.getInt("ThreadsPerInstantThreadPool", 2);
	}
}
