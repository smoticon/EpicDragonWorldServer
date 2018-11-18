using System;
using System.IO;
using System.Runtime.CompilerServices;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class LogManager
{
    static readonly string LOG_PATH = "Log" + Path.DirectorySeparatorChar;
    static readonly string LOG_FILE_CONSOLE = "Console ";
    static readonly string LOG_FILE_WORLD = "World ";
    static readonly string LOG_FILE_CHAT = "Chat ";
    static readonly string LOG_FILE_EXT = ".txt";
    static readonly string LOG_DATE_FORMAT = "{0:dd/MM HH:mm:ss}";
    static readonly string LOG_FILE_NAME_FORMAT = "{0:yyyy-MM-dd}";

    [MethodImpl(MethodImplOptions.Synchronized)]
    public static void Log(string message)
    {
        DateTime currentTime = DateTime.Now;
        message = "[" + string.Format(LOG_DATE_FORMAT, currentTime) + "] " + message;
        // Write to console.
        Console.WriteLine(message);
        // Append to "log\Console yyyy-MM-dd.txt" file.
        using (StreamWriter writer = File.AppendText(LOG_PATH + LOG_FILE_CONSOLE + string.Format(LOG_FILE_NAME_FORMAT, currentTime) + LOG_FILE_EXT))
        {
            writer.WriteLine(message);
        }
    }

    [MethodImpl(MethodImplOptions.Synchronized)]
    public static void LogWorld(string message)
    {
        DateTime currentTime = DateTime.Now;
        // Append to "log\World yyyy-MM-dd.txt" file.
        using (StreamWriter writer = File.AppendText(LOG_PATH + LOG_FILE_WORLD + string.Format(LOG_FILE_NAME_FORMAT, currentTime) + LOG_FILE_EXT))
        {
            writer.WriteLine("[" + string.Format(LOG_DATE_FORMAT, currentTime) + "] " + message);
        }
    }

    [MethodImpl(MethodImplOptions.Synchronized)]
    public static void LogChat(string message)
    {
        DateTime currentTime = DateTime.Now;
        // Append to "log\Chat yyyy-MM-dd.txt" file.
        using (StreamWriter writer = File.AppendText(LOG_PATH + LOG_FILE_CHAT + string.Format(LOG_FILE_NAME_FORMAT, currentTime) + LOG_FILE_EXT))
        {
            writer.WriteLine("[" + string.Format(LOG_DATE_FORMAT, currentTime) + "] " + message);
        }
    }
}
