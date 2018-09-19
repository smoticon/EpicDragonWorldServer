package com.epicdragonworld.gameserver.model.holders;

import com.epicdragonworld.gameserver.enums.SkillType;

/**
 * @author Pantelis Andrianakis
 */
public class SkillHolder
{
	private final int _skillId;
	private final int _level;
	private final SkillType _skillType;
	private final int _param1;
	private final int _param2;
	
	public SkillHolder(int skillId, int level, SkillType skillType, int param1, int param2)
	{
		_skillId = skillId;
		_level = level;
		_skillType = skillType;
		_param1 = param1;
		_param2 = param2;
	}
	
	public int getSkillId()
	{
		return _skillId;
	}
	
	public int getLevel()
	{
		return _level;
	}
	
	public SkillType getSkillType()
	{
		return _skillType;
	}
	
	public int getParam1()
	{
		return _param1;
	}
	
	public int getParam2()
	{
		return _param2;
	}
}
