package com.epicdragonworld.gameserver.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.enums.SkillType;
import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.model.holders.SkillHolder;

/**
 * @author Pantelis Andrianakis
 */
public class SkillData
{
	private static final Logger LOGGER = Logger.getLogger(SkillData.class.getName());
	
	private static final String RESTORE_SKILLS = "SELECT * FROM skills";
	
	private static final Map<Integer, SkillHolder> _skills = new ConcurrentHashMap<>();
	
	public static void init()
	{
		_skills.clear();
		
		try (Connection con = DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_SKILLS))
		{
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					final int skillId = rset.getInt("skill_id");
					_skills.put(skillId, new SkillHolder(skillId, rset.getInt("level"), Enum.valueOf(SkillType.class, rset.getString("type")), rset.getInt("param_1"), rset.getInt("param_2")));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		LOGGER.info("SkillData: Loaded " + _skills.size() + " skills.");
	}
	
	public static SkillHolder getSkill(int skillId)
	{
		return _skills.get(skillId);
	}
}
