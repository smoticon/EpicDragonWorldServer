using MySql.Data.MySqlClient;
using System;

/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class SpawnData
{
    private static readonly string RESTORE_SPAWNS = "SELECT * FROM spawnlist";
    private static int COUNT;

    public static void Load()
    {
        Util.PrintSection("Spawns");

        COUNT = 0;
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(RESTORE_SPAWNS, con);
            MySqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                SpawnNpc(reader.GetInt32("npc_id"), new LocationHolder((float)reader.GetDouble("x"), (float)reader.GetDouble("y"), (float)reader.GetDouble("z"), (float)reader.GetDouble("heading")), reader.GetInt32("respawn_delay"));
                COUNT++;
            }
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        LogManager.Log("SpawnData: Loaded " + COUNT + " spawns.");
    }

    public static Npc SpawnNpc(int npcId, LocationHolder location, int respawnDelay)
    {
        NpcHolder npcHolder = NpcData.GetNpcHolder(npcId);
        SpawnHolder spawn = new SpawnHolder(location, respawnDelay);
        Npc npc = null;
        switch (npcHolder.GetNpcType())
        {
            case NpcType.NPC:
                npc = new Npc(npcHolder, spawn);
                break;

            case NpcType.MONSTER:
                npc = new Monster(npcHolder, spawn);
                break;
        }
        return npc;
    }
}
