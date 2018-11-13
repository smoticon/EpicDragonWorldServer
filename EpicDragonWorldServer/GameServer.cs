using System;
using System.IO;
using System.Threading;
using System.Threading.Tasks;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class GameServer
{
    static readonly ManualResetEvent quitEvent = new ManualResetEvent(false);

    static void Main(string[] args)
    {
        // Set console title.
        Console.Title = "Epic Dragon World - Game Server";

        // Keep start time for later.
        DateTime serverLoadStart = DateTime.Now;

        // Create Log directory used by LogManager.
        Directory.CreateDirectory("Log");

        PrintSection("Configs");
        Config.Load();

        PrintSection("Database");
        DatabaseManager.Init();

        // Post info.
        PrintSection("Info");
        LogManager.Log("Server loaded in " + (DateTime.Now - serverLoadStart).TotalSeconds + " seconds.");

        // Initialize async network listening.
        Task.Run(() => GameClientNetworkListener.Init());

        // Wait.
        Console.CancelKeyPress += (sender, eArgs) =>
        {
            quitEvent.Set();
            eArgs.Cancel = true;
        };
        quitEvent.WaitOne();
    }

    static void PrintSection(string section)
    {
        section = "=[ " + section + " ]";
        while (section.Length < 62)
        {
            section = "-" + section;
        }
        LogManager.Log(section);
    }
}
