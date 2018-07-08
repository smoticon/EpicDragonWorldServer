package com.epicdragonworld.util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Pantelis Andrianakis
 */
public final class ConfigReader
{
	private static final Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());
	
	private final Properties _properties = new Properties();
	private final String _fileName;
	
	public ConfigReader(String fileName)
	{
		_fileName = fileName;
		try
		{
			_properties.load(new FileInputStream(fileName));
		}
		catch (Exception e)
		{
			LOGGER.warning("Could not load " + fileName + ". " + e.getMessage());
		}
	}
	
	public String getString(String value, String defaultValue)
	{
		if (!_properties.containsKey(value))
		{
			LOGGER.warning("Missing config " + value + " from file " + _fileName + ". Default value " + defaultValue + " will be used instead.");
			return defaultValue;
		}
		return _properties.getProperty(value);
	}
	
	public boolean getBoolean(String value, boolean defaultValue)
	{
		if (!_properties.containsKey(value))
		{
			LOGGER.warning("Missing config " + value + " from file " + _fileName + ". Default value " + defaultValue + " will be used instead.");
			return defaultValue;
		}
		
		try
		{
			return Boolean.parseBoolean(_properties.getProperty(value));
		}
		catch (Exception e)
		{
			LOGGER.warning("Config " + value + " from file " + _fileName + " should be Boolean. Using default value " + defaultValue + " instead.");
			return defaultValue;
		}
	}
	
	public int getInt(String value, int defaultValue)
	{
		if (!_properties.containsKey(value))
		{
			LOGGER.warning("Missing config " + value + " from file " + _fileName + ". Default value " + defaultValue + " will be used instead.");
			return defaultValue;
		}
		
		try
		{
			return Integer.parseInt(_properties.getProperty(value));
		}
		catch (Exception e)
		{
			LOGGER.warning("Config " + value + " from file " + _fileName + " should be Integer. Using default value " + defaultValue + " instead.");
			return defaultValue;
		}
	}
	
	public long getLong(String value, long defaultValue)
	{
		if (!_properties.containsKey(value))
		{
			LOGGER.warning("Missing config " + value + " from file " + _fileName + ". Default value " + defaultValue + " will be used instead.");
			return defaultValue;
		}
		
		try
		{
			return Long.parseLong(_properties.getProperty(value));
		}
		catch (Exception e)
		{
			LOGGER.warning("Config " + value + " from file " + _fileName + " should be Long. Using default value " + defaultValue + " instead.");
			return defaultValue;
		}
	}
	
	public float getFloat(String value, float defaultValue)
	{
		if (!_properties.containsKey(value))
		{
			LOGGER.warning("Missing config " + value + " from file " + _fileName + ". Default value " + defaultValue + " will be used instead.");
			return defaultValue;
		}
		
		try
		{
			return Float.parseFloat(_properties.getProperty(value));
		}
		catch (Exception e)
		{
			LOGGER.warning("Config " + value + " from file " + _fileName + " should be Float. Using default value " + defaultValue + " instead.");
			return defaultValue;
		}
	}
	
	public double getDouble(String value, double defaultValue)
	{
		if (!_properties.containsKey(value))
		{
			LOGGER.warning("Missing config " + value + " from file " + _fileName + ". Default value " + defaultValue + " will be used instead.");
			return defaultValue;
		}
		
		try
		{
			return Double.parseDouble(_properties.getProperty(value));
		}
		catch (Exception e)
		{
			LOGGER.warning("Config " + value + " from file " + _fileName + " should be Double. Using default value " + defaultValue + " instead.");
			return defaultValue;
		}
	}
}
