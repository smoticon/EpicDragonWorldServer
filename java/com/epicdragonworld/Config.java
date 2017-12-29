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
	public static final String ACCOUNTS_CONFIG_FILE = "./config/Accounts.ini";
	public static final String SERVER_CONFIG_FILE = "./config/Server.ini";
	
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
	
	public static void load()
	{
		final PropertiesParser accountsSettings = new PropertiesParser(ACCOUNTS_CONFIG_FILE);
		ACCOUNT_AUTO_CREATE = accountsSettings.getBoolean("AccountAutoCreate", false);
		ACCOUNT_MAX_CHARACTERS = accountsSettings.getInt("AccountMaxCharacters", 5);
		
		final PropertiesParser serverSettings = new PropertiesParser(SERVER_CONFIG_FILE);
		GAMESERVER_PORT = serverSettings.getInt("GameserverPort", 5055);
		GAMESERVER_HOSTNAME = serverSettings.getString("GameserverHostname", "0.0.0.0");
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
		IO_PACKET_THREAD_CORE_SIZE = serverSettings.getInt("UrgentPacketThreadCoreSize", 2);
	}
}
