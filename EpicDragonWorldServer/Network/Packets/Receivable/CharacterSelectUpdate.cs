using MySql.Data.MySqlClient;
using System;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterSelectUpdate
{
    private static readonly string CHARACTER_SELECTED_RESET_QUERY = "UPDATE characters SET selected=0 WHERE account=@account";
    private static readonly string CHARACTER_SELECTED_UPDATE_QUERY = "UPDATE characters SET selected=1 WHERE account=@account AND slot=@slot";

    public CharacterSelectUpdate(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        int slot = packet.ReadByte();

        // Make existing characters selected value false.
        try
        {
            using (SqlConnection con = new SqlConnection())
            {
                con.Connection.Open();
                using (MySqlCommand cmd = new MySqlCommand(CHARACTER_SELECTED_RESET_QUERY, con.Connection))
                {
                    cmd.Parameters.AddWithValue("account", client.GetAccountName());
                    cmd.ExecuteNonQuery();
                }
            }
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // Set character selected.
        try
        {
            using (SqlConnection con = new SqlConnection())
            {
                con.Connection.Open();
                using (MySqlCommand cmd = new MySqlCommand(CHARACTER_SELECTED_UPDATE_QUERY, con.Connection))
                {
                    cmd.Parameters.AddWithValue("account", client.GetAccountName());
                    cmd.Parameters.AddWithValue("slot", slot);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
    }
}
