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
                int npcId = reader.GetInt32("npc_id");
                NpcHolder npcHolder = NpcData.GetNpcHolder(npcId);
                SpawnHolder spawn = new SpawnHolder(new LocationHolder(reader.GetFloat("x"), reader.GetFloat("y"), reader.GetFloat("z"), reader.GetFloat("heading")), reader.GetInt32("respawn_delay"));
                switch (npcHolder.GetNpcType())
                {
                    case NpcType.NPC:
                        new Npc(npcHolder, spawn);
                        break;

                    case NpcType.MONSTER:
                        new Monster(npcHolder, spawn);
                        break;
                }
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
}
