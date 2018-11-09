using MySql.Data.MySqlClient;
using System;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class SqlConnection : IDisposable
{
    public readonly MySqlConnection Connection;

    public SqlConnection()
    {
        Connection = new MySqlConnection(Config.SQL_CONNECTION_PARAMETERS);
    }

    public void Dispose()
    {
        Connection.Close();
    }
}
