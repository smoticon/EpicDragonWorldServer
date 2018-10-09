package com.epicdragonworld.gameserver.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.enums.NpcType;
import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.model.holders.NpcHolder;

/**
 * @author Pantelis Andrianakis
 */
public class NpcData
{
	private static final Logger LOGGER = Logger.getLogger(NpcData.class.getName());
	
	private static final String RESTORE_NPCS = "SELECT * FROM npcs";
	
	private static final Map<Integer, NpcHolder> _npcs = new HashMap<>();
	
	public static void init()
	{
		_npcs.clear();
		
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_NPCS))
		{
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					final int npcId = rset.getInt("npc_id");
					_npcs.put(npcId, new NpcHolder(npcId, rset.getInt("level"), Enum.valueOf(NpcType.class, rset.getString("type")), rset.getInt("stamina"), rset.getInt("strength"), rset.getInt("dexterity"), rset.getInt("intelect")));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		LOGGER.info("NpcData: Loaded " + _npcs.size() + " npcs.");
	}
	
	public static NpcHolder getNpcHolder(int npcId)
	{
		return _npcs.get(npcId);
	}
}
