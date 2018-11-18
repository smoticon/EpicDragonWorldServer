using System.Globalization;
using System.IO;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class Config
{
    // --------------------------------------------------
    // Config File Definitions
    // --------------------------------------------------
	static readonly string ACCOUNT_CONFIG_FILE = "Config" + Path.DirectorySeparatorChar + "Account.ini";
	static readonly string LOGGING_CONFIG_FILE = "Config" + Path.DirectorySeparatorChar + "Logging.ini";
	static readonly string PLAYER_CONFIG_FILE = "Config" + Path.DirectorySeparatorChar + "Player.ini";
	static readonly string SERVER_CONFIG_FILE = "Config" + Path.DirectorySeparatorChar + "Server.ini";

    // --------------------------------------------------
    // Account
    // --------------------------------------------------
    public static bool ACCOUNT_AUTO_CREATE;
    public static int ACCOUNT_MAX_CHARACTERS;

    // --------------------------------------------------
    // Logging
    // --------------------------------------------------
    public static bool LOG_CHAT;
    public static bool LOG_WORLD;

    // --------------------------------------------------
    // Player
    // --------------------------------------------------
    public static LocationHolder STARTING_LOCATION;

    // --------------------------------------------------
    // Server
    // --------------------------------------------------
    public static int SERVER_PORT;
    public static string DATABASE_CONNECTION_PARAMETERS;
    public static int DATABASE_MAX_CONNECTIONS;
    public static int MAXIMUM_ONLINE_USERS;
    public static double CLIENT_VERSION;

    public static void Load()
    {
        ConfigReader accountConfigs = new ConfigReader(ACCOUNT_CONFIG_FILE);
        ACCOUNT_AUTO_CREATE = accountConfigs.GetBool("AccountAutoCreate", false);
        ACCOUNT_MAX_CHARACTERS = accountConfigs.GetInt("AccountMaxCharacters", 5);

        ConfigReader loggingConfigs = new ConfigReader(LOGGING_CONFIG_FILE);
        LOG_CHAT = loggingConfigs.GetBool("LogChat", true);
        LOG_WORLD = loggingConfigs.GetBool("LogWorld", true);

        ConfigReader playerConfigs = new ConfigReader(PLAYER_CONFIG_FILE);
        string[] startingLocation = playerConfigs.GetString("StartingLocation", "9945.9;9.2;10534.9").Split(";");
        STARTING_LOCATION = new LocationHolder(float.Parse(startingLocation[0], CultureInfo.InvariantCulture), float.Parse(startingLocation[1], CultureInfo.InvariantCulture), float.Parse(startingLocation[2], CultureInfo.InvariantCulture), startingLocation.Length > 3 ? float.Parse(startingLocation[3], CultureInfo.InvariantCulture) : 0);

        ConfigReader serverConfigs = new ConfigReader(SERVER_CONFIG_FILE);
        SERVER_PORT = serverConfigs.GetInt("ServerPort", 5055);
        DATABASE_CONNECTION_PARAMETERS = serverConfigs.GetString("DbConnectionParameters", "Server=127.0.0.1;User ID=root;Password=;Database=edws");
        DATABASE_MAX_CONNECTIONS = serverConfigs.GetInt("MaximumDbConnections", 50);
        MAXIMUM_ONLINE_USERS = serverConfigs.GetInt("MaximumOnlineUsers", 2000);
        CLIENT_VERSION = serverConfigs.GetDouble("ClientVersion", 1.0);

        LogManager.Log("Configs: Initialized.");
    }
}
