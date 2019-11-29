using MySql.Data.MySqlClient;
using System;

/**
 * Author: Pantelis Andrianakis
 * Date: November 29th 2019
 */
public class DeleteCommand
{
    private static readonly string SPAWN_DELETE_QUERY = "DELETE FROM spawnlist WHERE npc_id=@npc_id AND x=@x AND y=@y AND z=@z AND heading=@heading AND respawn_delay=@respawn_delay";

    public static void Handle(Player player)
    {
        // Gather information.
        WorldObject target = player.GetTarget();
        if (target == null)
        {
            ChatManager.SendSystemMessage(player, "You must select a target.");
            return;
        }
        Npc npc = target.AsNpc();
        if (npc == null)
        {
            ChatManager.SendSystemMessage(player, "You must select an NPC.");
            return;
        }

        // Log admin activity.
        SpawnHolder npcSpawn = npc.GetSpawnHolder();
        LocationHolder npcLocation = npcSpawn.GetLocation();
        if (Config.LOG_ADMIN)
        {
            LogManager.LogAdmin(player.GetName() + " used command /delete " + npc + " " + npcLocation);
        }

        // Delete NPC.
        npc.DeleteMe();

        // Send player success message.
        int npcId = npc.GetNpcHolder().GetNpcId();
        ChatManager.SendSystemMessage(player, "You have deleted " + npcId + " from " + npcLocation);

        // Store in database.
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(SPAWN_DELETE_QUERY, con);
            cmd.Parameters.AddWithValue("npc_id", npcId);
            cmd.Parameters.AddWithValue("x", npcLocation.GetX());
            cmd.Parameters.AddWithValue("y", npcLocation.GetY());
            cmd.Parameters.AddWithValue("z", npcLocation.GetZ());
            cmd.Parameters.AddWithValue("heading", npcLocation.GetHeading());
            cmd.Parameters.AddWithValue("respawn_delay", npcSpawn.GetRespawnDelay());
            cmd.ExecuteNonQuery();
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
    }
}
