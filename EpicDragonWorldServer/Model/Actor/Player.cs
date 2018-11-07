using MySql.Data.MySqlClient;
using System;

/**
 * @author Pantelis Andrianakis
 */
public class Player : Creature
{
    private static readonly string RESTORE_CHARACTER = "SELECT * FROM characters WHERE name=@name";
    private static readonly string STORE_CHARACTER = "UPDATE characters SET name=@name, class_id=@class_id WHERE account=@account AND name=@name";

    private readonly GameClient client;
    private readonly string name;
    private readonly int classId;

    public Player(GameClient client, string name)
    {
        this.client = client;
        this.name = name;

        // Load information from database.
        try
        {
            using (SqlConnection con = new SqlConnection())
            {
                con.Connection.Open();
                using (MySqlCommand cmd = new MySqlCommand(RESTORE_CHARACTER, con.Connection))
                {
                    cmd.Parameters.AddWithValue("name", name);
                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            classId = reader.GetInt32("class_id");
                            GetLocation().SetX(reader.GetFloat("x"));
                            GetLocation().SetY(reader.GetFloat("y"));
                            GetLocation().SetZ(reader.GetFloat("z"));
                            // TODO: Restore player stats (STA/STR/DEX/INT).
                            // TODO: Restore player level.
                            // TODO: Restore player Current HP.
                            // TODO: Restore player Current MP.
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
    }

    public void StoreMe()
    {
        try
        {
            using (SqlConnection con = new SqlConnection())
            {
                con.Connection.Open();
                using (MySqlCommand cmd = new MySqlCommand(STORE_CHARACTER, con.Connection))
                {
                    cmd.Parameters.AddWithValue("name", name);
                    cmd.Parameters.AddWithValue("class_id", classId);
                    // TODO: Save location.
                    // TODO: Save player stats (STA/STR/DEX/INT).
                    // TODO: Save player level.
                    // TODO: Save player Current HP.
                    // TODO: Save player Current MP.
                    cmd.Parameters.AddWithValue("account", client.GetAccountName());
                    // Parameter already added above?
                    // cmd.Parameters.AddWithValue("name", _name);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
    }

    public GameClient GetClient()
    {
        return client;
    }

    public string GetName()
    {
        return name;
    }

    public int GetClassId()
    {
        return classId;
    }

    public void ChannelSend(SendablePacket packet)
    {
        client.ChannelSend(packet);
    }

    public override bool IsPlayer()
    {
        return true;
    }

    public override Player AsPlayer()
    {
        return this;
    }
}
