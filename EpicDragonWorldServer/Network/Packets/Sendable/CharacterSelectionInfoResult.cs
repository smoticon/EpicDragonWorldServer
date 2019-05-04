using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class CharacterSelectionInfoResult : SendablePacket
{
    private static readonly string CHARACTER_QUERY = "SELECT * FROM characters WHERE account=@account AND access_level>'-1' ORDER BY slot ASC";

    public CharacterSelectionInfoResult(string accountName)
    {
        // Local data.
        List<CharacterDataHolder> characterList = new List<CharacterDataHolder>();

        // Get data from database.
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(CHARACTER_QUERY, con);
            cmd.Parameters.AddWithValue("account", accountName);
            MySqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                CharacterDataHolder characterData = new CharacterDataHolder();
                characterData.SetName(reader.GetString("name"));
                characterData.SetSlot((byte)reader.GetInt16("slot")); // TODO: Remove cast?
                characterData.SetSelected(reader.GetBoolean("selected"));
                characterData.SetRace((byte)reader.GetInt16("race")); // TODO: Remove cast?
                characterData.SetHeight(reader.GetFloat("height"));
                characterData.SetBelly(reader.GetFloat("belly"));
                characterData.SetHairType((byte)reader.GetInt16("hair_type")); // TODO: Remove cast?
                characterData.SetHairColor(reader.GetInt32("hair_color"));
                characterData.SetSkinColor(reader.GetInt32("skin_color"));
                characterData.SetEyeColor(reader.GetInt32("eye_color"));
                characterData.SetX(reader.GetFloat("x"));
                characterData.SetY(reader.GetFloat("y"));
                characterData.SetZ(reader.GetFloat("z"));
                characterData.SetHeading(reader.GetFloat("heading"));
                characterData.SetExperience(reader.GetInt64("experience"));
                characterData.SetHp(reader.GetInt64("hp"));
                characterData.SetMp(reader.GetInt64("mp"));
                characterData.SetAccessLevel((byte)reader.GetInt16("access_level")); // TODO: Remove cast?
                characterList.Add(characterData);
            }
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // Send the data.
        WriteShort(2); // Packet id.
        WriteByte(characterList.Count);
        foreach (CharacterDataHolder characterData in characterList)
        {
            WriteString(characterData.GetName());
            WriteByte(characterData.GetSlot());
            WriteByte(characterData.IsSelected() ? 1 : 0);
            WriteByte(characterData.GetRace());
            WriteFloat(characterData.GetHeight());
            WriteFloat(characterData.GetBelly());
            WriteByte(characterData.GetHairType());
            WriteInt(characterData.GetHairColor());
            WriteInt(characterData.GetSkinColor());
            WriteInt(characterData.GetEyeColor());
            WriteFloat(characterData.GetX());
            WriteFloat(characterData.GetY());
            WriteFloat(characterData.GetZ());
            WriteFloat(characterData.GetHeading());
            WriteLong(characterData.GetExperience());
            WriteLong(characterData.GetHp());
            WriteLong(characterData.GetMp());
            WriteByte(characterData.GetAccessLevel());
        }
    }
}
