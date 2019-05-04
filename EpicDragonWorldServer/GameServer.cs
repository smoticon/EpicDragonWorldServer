using System;
using System.Threading;
using System.Threading.Tasks;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class GameServer
{
    private static readonly ManualResetEvent quitEvent = new ManualResetEvent(false);

    static void Main(string[] args)
    {
        // Set console title.
        Console.Title = "Epic Dragon World - Game Server";

        // Keep start time for later.
        DateTime serverLoadStart = DateTime.Now;

        LogManager.Init();
        Config.Load();
        DatabaseManager.Init();
        WorldManager.Init();

        // Post info.
        Util.PrintSection("Info");
        LogManager.Log("Server loaded in " + Math.Round((DateTime.Now - serverLoadStart).TotalSeconds, 2) + " seconds.");

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
}
