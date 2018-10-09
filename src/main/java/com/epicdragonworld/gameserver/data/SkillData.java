package com.epicdragonworld.gameserver.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
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
	
	private static final Map<Long, SkillHolder> _skills = new HashMap<>();
	
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
					final int skillLevel = rset.getInt("level");
					_skills.put(getSkillHashCode(skillId, skillLevel), new SkillHolder(skillId, skillLevel, Enum.valueOf(SkillType.class, rset.getString("type")), rset.getInt("reuse"), rset.getInt("range"), rset.getInt("param_1"), rset.getInt("param_2")));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		LOGGER.info("SkillData: Loaded " + _skills.size() + " skills.");
	}
	
	private static long getSkillHashCode(int skillId, int skillLevel)
	{
		return (skillId * 100000) + skillLevel;
	}
	
	public static SkillHolder getSkillHolder(int skillId, int skillLevel)
	{
		return _skills.get(getSkillHashCode(skillId, skillLevel));
	}
}
