using MySql.Data.MySqlClient;
using System;

/**
 * @author Pantelis Andrianakis
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
