using MySql.Data.MySqlClient;
using System;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class CharacterCreationRequest
{
    static readonly string ACCOUNT_CHARACTER_QUERY = "SELECT * FROM characters WHERE access_level>'-1' AND account=@account";
    static readonly string NAME_EXISTS_QUERY = "SELECT * FROM characters WHERE name=@name";
    static readonly string CHARACTER_SELECTED_RESET_QUERY = "UPDATE characters SET selected=0 WHERE account=@account";
    static readonly string CHARACTER_CREATE_QUERY = "INSERT INTO characters (account, name, slot, selected, class_id, location_name, x, y, z, heading, experience, hp, mp) values (@account, @name, @slot, @selected, @class_id, @location_name, @x, @y, @z, @heading, @experience, @hp, @mp)";

    static readonly int INVALID_NAME = 0;
    static readonly int NAME_IS_TOO_SHORT = 1;
    static readonly int NAME_ALREADY_EXISTS = 2;
    static readonly int CANNOT_CREATE_ADDITIONAL_CHARACTERS = 3;
    static readonly int SUCCESS = 100;

    public CharacterCreationRequest(GameClient client, ReceivablePacket packet)
    {
        // Make sure player has authenticated.
        if ((client.GetAccountName() == null) || (client.GetAccountName().Length == 0))
        {
            return;
        }

        // Read data.
        string characterName = packet.ReadString();
        int classId = packet.ReadByte();

        // Replace illegal characters.
        foreach (char c in Util.ILLEGAL_CHARACTERS)
        {
            characterName = characterName.Replace(c, '\'');
        }

        // Name character checks.
        if (characterName.Contains("'"))
        {
            client.ChannelSend(new CharacterCreationResult(INVALID_NAME));
            return;
        }
        if ((characterName.Length < 2) || (characterName.Length > 12)) // 12 should not happen, checking it here in case of client cheat.
        {
            client.ChannelSend(new CharacterCreationResult(NAME_IS_TOO_SHORT));
            return;
        }

        // Account character count database check.
        int characterCount = 0;
        int lastCharacterSlot = 0;
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(ACCOUNT_CHARACTER_QUERY, con);
            cmd.Parameters.AddWithValue("account", client.GetAccountName());
            MySqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                characterCount++;
                int slot = reader.GetInt32("slot");
                if (slot > lastCharacterSlot)
                {
                    lastCharacterSlot = slot;
                }
            }
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
        if (characterCount >= Config.ACCOUNT_MAX_CHARACTERS)
        {
            client.ChannelSend(new CharacterCreationResult(CANNOT_CREATE_ADDITIONAL_CHARACTERS));
            return;
        }

        // Check database if name exists.
        bool characterExists = false;
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(NAME_EXISTS_QUERY, con);
            {
                cmd.Parameters.AddWithValue("name", characterName);
                MySqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    characterExists = true;
                }
            }
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }
        if (characterExists)
        {
            client.ChannelSend(new CharacterCreationResult(NAME_ALREADY_EXISTS));
            return;
        }

        // Make existing characters selected value false.
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(CHARACTER_SELECTED_RESET_QUERY, con);
            cmd.Parameters.AddWithValue("account", client.GetAccountName());
            cmd.ExecuteNonQuery();
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // Create character.
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(CHARACTER_CREATE_QUERY, con);
            cmd.Parameters.AddWithValue("account", client.GetAccountName());
            cmd.Parameters.AddWithValue("name", characterName);
            cmd.Parameters.AddWithValue("slot", lastCharacterSlot + 1);
            cmd.Parameters.AddWithValue("selected", 1); // Selected character.
            cmd.Parameters.AddWithValue("class_id", classId);
            cmd.Parameters.AddWithValue("location_name", "Start Location");
            cmd.Parameters.AddWithValue("x", Config.STARTING_LOCATION.GetX());
            cmd.Parameters.AddWithValue("y", Config.STARTING_LOCATION.GetY());
            cmd.Parameters.AddWithValue("z", Config.STARTING_LOCATION.GetZ());
            cmd.Parameters.AddWithValue("heading", Config.STARTING_LOCATION.GetHeading());
            cmd.Parameters.AddWithValue("experience", 0); // TODO: Starting level experience.
            cmd.Parameters.AddWithValue("hp", 1); // TODO: Character stats HP.
            cmd.Parameters.AddWithValue("mp", 1); // TODO: Character stats MP.
            cmd.ExecuteNonQuery();
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // Send success result.
        client.ChannelSend(new CharacterCreationResult(SUCCESS));
    }
}
