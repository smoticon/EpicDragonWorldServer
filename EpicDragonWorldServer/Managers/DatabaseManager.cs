using MySql.Data.MySqlClient;
using System;
using System.Data;

/**
 * Author: Pantelis Andrianakis
 * Date: November 10th 2018
 */
public class DatabaseManager
{
    static readonly MySqlConnection[] connections = new MySqlConnection[Config.DATABASE_MAX_CONNECTIONS];
    static readonly object taskLock = new object();
    static int connectionCounter = 0;

    public static void Init()
    {
        try
        {
            for (int i = 0; i < Config.DATABASE_MAX_CONNECTIONS; i++)
            {
                connections[i] = new MySqlConnection(Config.DATABASE_CONNECTION_PARAMETERS);
                connections[i].Open();
            }
            for (int i = 0; i < Config.DATABASE_MAX_CONNECTIONS; i++)
            {
                connections[i].Close();
            }
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
        LogManager.Log("Database: Initialized.");
    }

    public static MySqlConnection GetConnection()
    {
        MySqlConnection connection = null;
        lock (taskLock)
        {
            while (connection == null)
            {
                if (connectionCounter >= Config.DATABASE_MAX_CONNECTIONS)
                {
                    connectionCounter = 0;
                }
                if (connections[connectionCounter].State == ConnectionState.Closed)
                {
                    connection = connections[connectionCounter];
                    connection.Open();
                }
                connectionCounter++;
            }
        }
        return connection;
    }
}
