using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class NpcData
{
    private static readonly string RESTORE_NPCS = "SELECT * FROM npcs";
    private static readonly Dictionary<long, NpcHolder> NPCS = new Dictionary<long, NpcHolder>();

    public static void Load()
    {
        Util.PrintSection("NPCs");

        NPCS.Clear();
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(RESTORE_NPCS, con);
            MySqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                int npcId = reader.GetInt32("npc_id");
                NPCS.Add(npcId, new NpcHolder(npcId, (NpcType)Enum.Parse(typeof(NpcType), reader.GetString("type")), reader.GetInt32("level"), reader.GetBoolean("sex"), reader.GetInt32("stamina"), reader.GetInt32("strength"), reader.GetInt32("dexterity"), reader.GetInt32("intelect")));
            }
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        LogManager.Log("NpcData: Loaded " + NPCS.Count + " NPCs.");
    }

    public static NpcHolder GetNpcHolder(int npcId)
    {
        if (!NPCS.ContainsKey(npcId))
        {
            return null;
        }
        return NPCS[npcId];
    }
}
