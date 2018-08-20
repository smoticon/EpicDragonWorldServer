package com.epicdragonworld.gameserver.managers;

import java.sql.Connection;
import java.util.logging.Logger;

import com.epicdragonworld.Config;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Pantelis Andrianakis
 */
public class DatabaseManager
{
	private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
	
	private static final HikariDataSource _hds = new HikariDataSource();
	
	public static void init()
	{
		_hds.setDriverClassName(Config.DATABASE_DRIVER);
		_hds.setJdbcUrl(Config.DATABASE_URL);
		_hds.setUsername(Config.DATABASE_LOGIN);
		_hds.setPassword(Config.DATABASE_PASSWORD);
		_hds.setMaximumPoolSize(Config.DATABASE_MAX_CONNECTIONS);
		_hds.setIdleTimeout(Config.DATABASE_MAX_IDLE_TIME);
		
		// Test if connection is valid.
		try
		{
			_hds.getConnection().close();
		}
		catch (Exception e)
		{
			System.exit(1); // Close server.
		}
	}
	
	public static Connection getConnection()
	{
		Connection con = null;
		while (con == null)
		{
			try
			{
				con = _hds.getConnection();
			}
			catch (Exception e)
			{
				LOGGER.severe("DatabaseManager: Cound not get a connection. " + e);
			}
		}
		return con;
	}
	
	public static void close()
	{
		try
		{
			_hds.close();
		}
		catch (Exception e)
		{
			LOGGER.severe("DatabaseManager: There was a problem closing the data source. " + e);
		}
	}
}
