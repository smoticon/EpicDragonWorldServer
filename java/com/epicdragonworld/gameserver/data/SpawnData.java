package com.epicdragonworld.gameserver.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.model.Location;
import com.epicdragonworld.gameserver.model.actor.Monster;
import com.epicdragonworld.gameserver.model.actor.Npc;
import com.epicdragonworld.gameserver.model.holders.NpcTemplateHolder;
import com.epicdragonworld.gameserver.model.holders.SpawnHolder;

/**
 * @author Pantelis Andrianakis
 */
public class SpawnData
{
	private static final Logger LOGGER = Logger.getLogger(SpawnData.class.getName());
	private static final String RESTORE_SPAWNS = "SELECT * FROM spawnlist";
	
	public static void init()
	{
		int spawnCount = 0;
		
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_SPAWNS))
		{
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					final int npcId = rset.getInt("npc_id");
					final NpcTemplateHolder template = NpcData.getTemplate(npcId);
					if (template == null)
					{
						LOGGER.warning("SpawnData: Could not find NPC template with id " + npcId + ".");
					}
					else
					{
						final SpawnHolder spawn = new SpawnHolder(new Location(rset.getFloat("x"), rset.getFloat("y"), rset.getFloat("z"), rset.getInt("heading")), rset.getInt("respawn_time"));
						final String type = template.getType();
						if (type.equals("Monster"))
						{
							new Monster(template, spawn);
						}
						else
						{
							new Npc(template, spawn);
						}
						spawnCount++;
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		LOGGER.info("SpawnData: Loaded " + spawnCount + " spawns.");
	}
}
