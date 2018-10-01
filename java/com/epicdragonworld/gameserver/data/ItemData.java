package com.epicdragonworld.gameserver.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.enums.ItemType;
import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.model.holders.ItemHolder;
import com.epicdragonworld.gameserver.model.holders.SkillHolder;

/**
 * @author Pantelis Andrianakis
 */
public class ItemData
{
	private static final Logger LOGGER = Logger.getLogger(ItemData.class.getName());
	
	private static final String RESTORE_ITEMS = "SELECT * FROM items";
	
	private static final Map<Integer, ItemHolder> _items = new HashMap<>();
	
	public static void init()
	{
		_items.clear();
		
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_ITEMS))
		{
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					final int itemId = rset.getInt("item_id");
					final int skillId = rset.getInt("skill_id");
					final int skillLevel = rset.getInt("skill_level");
					final SkillHolder skillHolder = SkillData.getSkillHolder(skillId, skillLevel);
					if ((skillId > 0) && (skillHolder == null))
					{
						LOGGER.warning("ItemData: Could not find skill with id " + skillId + " and level " + skillLevel + " for item " + itemId + ".");
					}
					else
					{
						_items.put(itemId, new ItemHolder(itemId, rset.getInt("slot_id"), Enum.valueOf(ItemType.class, rset.getString("type")), rset.getBoolean("stackable"), rset.getBoolean("tradable"), rset.getLong("price"), rset.getInt("stamina"), rset.getInt("strength"), rset.getInt("dexterity"), rset.getInt("intelect"), skillHolder));
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		LOGGER.info("ItemData: Loaded " + _items.size() + " items.");
	}
	
	public static ItemHolder getItemHolder(int itemId)
	{
		return _items.get(itemId);
	}
}
