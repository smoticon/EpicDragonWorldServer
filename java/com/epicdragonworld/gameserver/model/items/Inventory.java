package com.epicdragonworld.gameserver.model.items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;

/**
 * @author Pantelis Andrianakis
 */
public class Inventory
{
	private static final Logger LOGGER = Logger.getLogger(Inventory.class.getName());
	
	private static final String RESTORE_INVENTORY = "SELECT * FROM character_items WHERE owner=?";
	private static final String DELETE_INVENTORY = "DELETE FROM character_items WHERE owner=?";
	private static final String STORE_ITEM = "INSERT INTO character_items (owner, slot_id, item_id) values (?, ?, ?)";
	
	private final Map<Integer, Integer> _items = new ConcurrentHashMap<>();
	
	public Inventory(String ownerName)
	{
		// Restore information from database.
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_INVENTORY))
		{
			ps.setString(1, ownerName);
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					_items.put(rset.getInt("slot_id"), rset.getInt("item_id"));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
	}
	
	/**
	 * Only used when player exits the game.
	 * @param ownerName
	 */
	public void store(String ownerName)
	{
		// Delete old records.
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE_INVENTORY))
		{
			ps.setString(1, ownerName);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// No need to store if item list is empty.
		if (_items.isEmpty())
		{
			return;
		}
		
		// Store new records.
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(STORE_ITEM))
		{
			for (Entry<Integer, Integer> slot : _items.entrySet())
			{
				ps.setString(1, ownerName);
				ps.setInt(2, slot.getKey());
				ps.setInt(3, slot.getValue());
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Clear item list.
		_items.clear();
	}
	
	public int getSlot(int slotId)
	{
		return _items.get(slotId);
	}
	
	public void setSlot(int slotId, int itemId)
	{
		_items.put(slotId, itemId);
	}
	
	public void removeSlot(int slotId)
	{
		_items.remove(slotId);
	}
	
	/**
	 * @return a Map that contains all (slotId, itemId) information.
	 */
	public Map<Integer, Integer> getItems()
	{
		return _items;
	}
}
