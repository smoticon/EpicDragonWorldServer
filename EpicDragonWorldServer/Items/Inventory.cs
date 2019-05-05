using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

/**
 * Author: Pantelis Andrianakis
 * Date: May 5th 2019
 */
public class Inventory
{
    private static readonly string RESTORE_INVENTORY = "SELECT * FROM character_items WHERE owner=@owner";
    private static readonly string DELETE_INVENTORY = "DELETE FROM character_items WHERE owner=@owner";
    private static readonly string STORE_ITEM = "INSERT INTO character_items (owner, slot_id, item_id) values (@owner, @slot_id, @item_id)";
    private readonly Dictionary<int, int> items = new Dictionary<int, int>();
    private readonly object itemLock = new object();

    public Inventory(string ownerName)
    {
        lock (itemLock)
        {
            // Restore information from database.
            try
            {
                MySqlConnection con = DatabaseManager.GetConnection();
                MySqlCommand cmd = new MySqlCommand(RESTORE_INVENTORY, con);
                cmd.Parameters.AddWithValue("owner", ownerName);
                MySqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    items.Add(reader.GetInt32("slot_id"), reader.GetInt32("item_id"));
                }
                con.Close();
            }
            catch (Exception e)
            {
                LogManager.Log(e.ToString());
            }
        }
    }

    // Only used when player exits the game.
    public void Store(string ownerName)
    {
        // Delete old records.
        try
        {
            MySqlConnection con = DatabaseManager.GetConnection();
            MySqlCommand cmd = new MySqlCommand(DELETE_INVENTORY, con);
            cmd.Parameters.AddWithValue("owner", ownerName);
            cmd.ExecuteNonQuery();
            con.Close();
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // No need to store if item list is empty.
        if (items.Count == 0)
        {
            return;
        }

        // Store new records.
        try
        {
            // TODO: Use some method like AddBatch.
            foreach (KeyValuePair<int, int> item in items)
            {
                MySqlConnection con = DatabaseManager.GetConnection();
                MySqlCommand cmd = new MySqlCommand(STORE_ITEM, con);
                cmd.Parameters.AddWithValue("owner", ownerName);
                cmd.Parameters.AddWithValue("slot_id", item.Key);
                cmd.Parameters.AddWithValue("item_id", item.Value);
                cmd.ExecuteNonQuery();
                con.Close();
            }
        }
        catch (Exception e)
        {
            LogManager.Log(e.ToString());
        }

        // Clear item list.
        items.Clear();
    }

    public int GetSlot(int slotId)
    {
        return items[slotId];
    }

    public void SetSlot(int slotId, int itemId)
    {
        lock (itemLock)
        {
            items.Add(slotId, itemId);
        }
    }

    public void RemoveSlot(int slotId)
    {
        lock (itemLock)
        {
            items.Remove(slotId);
        }
    }

    public Dictionary<int, int> GetItems()
    {
        return items;
    }
}
