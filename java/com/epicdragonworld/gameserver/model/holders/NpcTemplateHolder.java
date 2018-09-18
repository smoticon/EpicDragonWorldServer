package com.epicdragonworld.gameserver.model.holders;

/**
 * @author Pantelis Andrianakis
 */
public class NpcTemplateHolder
{
	private final int _npcId;
	private final int _level;
	private final String _type;
	private final int _sta;
	private final int _str;
	private final int _dex;
	private final int _int;
	
	public NpcTemplateHolder(int npcId, int level, String type, int stamina, int strength, int dexterity, int intelect)
	{
		_npcId = npcId;
		_level = level;
		_type = type;
		_sta = stamina;
		_str = strength;
		_dex = dexterity;
		_int = intelect;
	}
	
	public int getNpcId()
	{
		return _npcId;
	}
	
	public int getLevel()
	{
		return _level;
	}
	
	public String getType()
	{
		return _type;
	}
	
	public int getSTA()
	{
		return _sta;
	}
	
	public int getSTR()
	{
		return _str;
	}
	
	public int getDEX()
	{
		return _dex;
	}
	
	public int getINT()
	{
		return _int;
	}
}
