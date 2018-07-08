package com.epicdragonworld.gameserver.managers;

import java.sql.Connection;
import java.util.logging.Logger;

import com.epicdragonworld.Config;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Pantelis Andrianakis
 */
public class DatabaseManager
{
	private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
	
	private static final ComboPooledDataSource _cpds = new ComboPooledDataSource();
	
	public DatabaseManager()
	{
		try
		{
			_cpds.setDriverClass(Config.DATABASE_DRIVER);
			_cpds.setJdbcUrl(Config.DATABASE_URL);
			_cpds.setUser(Config.DATABASE_LOGIN);
			_cpds.setPassword(Config.DATABASE_PASSWORD);
			_cpds.setInitialPoolSize(2);
			_cpds.setMinPoolSize(2);
			_cpds.setMaxPoolSize(Math.max(2, Config.DATABASE_MAX_CONNECTIONS));
			_cpds.setAcquireIncrement(5);
			_cpds.setAcquireRetryDelay(500);
			_cpds.setCheckoutTimeout(0);
			_cpds.setMaxIdleTime(Config.DATABASE_MAX_IDLE_TIME);
			_cpds.setBreakAfterAcquireFailure(false);
			_cpds.setAutoCommitOnClose(true);
		}
		catch (Exception e)
		{
			LOGGER.severe("DatabaseManager: Problem initializing connection. " + e);
		}
	}
	
	public Connection getConnection()
	{
		try
		{
			return _cpds.getConnection();
		}
		catch (Exception e)
		{
			LOGGER.severe("DatabaseManager: There was a problem getting the connection. " + e);
		}
		return null;
	}
	
	public void close()
	{
		try
		{
			_cpds.close();
		}
		catch (Exception e)
		{
			LOGGER.severe("DatabaseManager: There was a problem closing the connection. " + e);
		}
	}
	
	public static DatabaseManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final DatabaseManager INSTANCE = new DatabaseManager();
	}
}
