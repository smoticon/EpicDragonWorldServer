using MySql.Data.MySqlClient;
using System;

/**
 * Author: Pantelis Andrianakis
 * Date: November 29th 2019
 */
public class SpawnCommand
{
    private static readonly string SPAWN_SAVE_QUERY = "INSERT INTO spawnlist (npc_id, x, y, z, heading, respawn_delay) values (@npc_id, @x, @y, @z, @heading, @respawn_delay)";

    public static void Handle(Player player, string command)
    {
        // Gather information from parameters.
        string[] commandSplit = command.Split(' ');
        int npcId;
        if (commandSplit.Length > 1)
        {
            npcId = int.Parse(commandSplit[1]);
        }
        else
        {
            ChatManager.SendSystemMessage(player, "Proper syntax is /spawn npcId delaySeconds(optional).");
            return;
        }
        int respawnDelay = 60;
        if (commandSplit.Length > 2)
        {
            respawnDelay = int.Parse(commandSplit[2]);
        }

        // Log admin activity.
        if (Config.LOG_ADMIN)
        {
            LogManager.LogAdmin(player.GetName() + " used command /spawn " + npcId + " " + respawnDelay);
        }

        // Spawn NPC.
        Npc npc = SpawnData.SpawnNpc(npcId, player.GetLocation(), respawnDelay);

        // Store in database.
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(SPAWN_SAVE_QUERY, con);
            cmd.Parameters.AddWithValue("npc_id", npcId);
            cmd.Parameters.AddWithValue("x", npc.GetLocation().GetX());
            cmd.Parameters.AddWithValue("y", npc.GetLocation().GetY());
            cmd.Parameters.AddWithValue("z", npc.GetLocation().GetZ());
            cmd.Parameters.AddWithValue("heading", npc.GetLocation().GetHeading());
            cmd.Parameters.AddWithValue("respawn_delay", respawnDelay);
            cmd.ExecuteNonQuery();
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // Broadcast NPC information.
        NpcInformation info = new NpcInformation(npc);
        player.ChannelSend(info);
        foreach (Player nearby in WorldManager.GetVisiblePlayers(player))
        {
            nearby.ChannelSend(info);
        }

        // Send player success message.
        ChatManager.SendSystemMessage(player, "You have spawned " + npcId + " at " + npc.GetLocation());
    }
}
