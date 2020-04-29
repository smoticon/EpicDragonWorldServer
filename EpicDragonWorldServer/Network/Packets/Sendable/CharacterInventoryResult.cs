using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

public class CharacterInventoryResult : SendablePacket
{
    private static readonly string CHARACTER_INVENTORY_ITEMS = "SELECT * FROM character_inventory WHERE owner=@owner ORDER BY item_id ASC";

    public CharacterInventoryResult(Player player)
    {
        List<ItemInfoHolder> itemList = new List<ItemInfoHolder>();
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(CHARACTER_INVENTORY_ITEMS, con);
            cmd.Parameters.AddWithValue("owner", player.GetName());
            MySqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                int itemId = reader.GetInt32("item_id");
                bool equipped = reader.GetBoolean("equiped");
                int amount = reader.GetInt32("amount");
                int enchantLvl = reader.GetInt32("enchant");

                ItemInfoHolder item = new ItemInfoHolder(itemId, equipped, amount, enchantLvl);

                itemList.Add(item);
            }
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        WriteShort(13); // Packet id.
        WriteInt(itemList.Count);
        foreach (ItemInfoHolder item in itemList)
        {
            WriteInt(item.GetItemId());
            WriteInt(item.IsEquipped() ? 1 : 0);
            WriteInt(item.GetAmount());
            WriteInt(item.GetEnchantLvl());
        }
    }
}
