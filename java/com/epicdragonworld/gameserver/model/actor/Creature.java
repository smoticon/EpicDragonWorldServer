package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.util.Rnd;

/**
 * @author Pantelis Andrianakis
 */
public class Creature extends WorldObject
{
	private static final int BASE_HP = 90;
	private static final int BASE_MP = 90;
	private static final float LEVEL_HP_MODIFIER = 1.1f;
	private static final float LEVEL_MP_MODIFIER = 1.2f;
	private static final int BASE_LAND_RATE = 70;
	private static final int BASE_CRIT_RATE = 5;
	private static final float MELEE_DAMAGE_MODIFIER = 1.2f;
	private static final float MELEE_SKILL_DAMAGE_MODIFIER = 1.4f;
	private static final float MAGIC_SKILL_DAMAGE_MODIFIER = 2.0f;
	
	private int _level = 1;
	private int _hp = BASE_HP;
	private int _mp = BASE_MP;
	private int _sta = 10;
	private int _str = 10;
	private int _dex = 10;
	private int _int = 10;
	private boolean _isAlive = true;
	
	public Creature()
	{
	}
	
	public int getLevel()
	{
		return _level;
	}
	
	public void setLevel(int level)
	{
		_level = level;
		// TODO: If player - Send Level status update to client.
		calculateHP();
		calculateMP();
	}
	
	public int getHP()
	{
		return _hp;
	}
	
	public synchronized void setHP(int value)
	{
		if (_hp < 1)
		{
			_hp = 0;
			if (_isAlive)
			{
				_isAlive = false;
				onDeath();
			}
		}
		else
		{
			_hp = value;
		}
	}
	
	public int getMP()
	{
		return _mp;
	}
	
	public synchronized void setMP(int value)
	{
		_mp = value;
	}
	
	public int getSTA()
	{
		return _sta;
	}
	
	public void setSTA(int value)
	{
		_sta = value;
		calculateHP();
	}
	
	public int getSTR()
	{
		return _str;
	}
	
	public void setSTR(int value)
	{
		_str = value;
	}
	
	public int getDEX()
	{
		return _dex;
	}
	
	public void setDEX(int value)
	{
		_dex = value;
	}
	
	public int getINT()
	{
		return _int;
	}
	
	public void setINT(int value)
	{
		_int = value;
		calculateMP();
	}
	
	private void calculateHP()
	{
		final float hpModifier = _level * LEVEL_HP_MODIFIER;
		_hp = (int) (BASE_HP + (_sta * hpModifier));
		// TODO: If player - Send HP status update to client.
	}
	
	private void calculateMP()
	{
		final float mpModifier = _level * LEVEL_MP_MODIFIER;
		_mp = (int) (BASE_MP + (_int * mpModifier));
		// TODO: If player - Send MP status update to client.
	}
	
	public boolean calculateHitSuccess(Creature enemy)
	{
		return Rnd.get(100) < ((BASE_LAND_RATE + _dex) - enemy.getDEX());
	}
	
	public boolean calculateHitCritical()
	{
		return Rnd.get(100) < (BASE_CRIT_RATE + _dex);
	}
	
	public int calculateHitDamage()
	{
		final float hitModifier = _level * MELEE_DAMAGE_MODIFIER;
		return (int) ((_str * hitModifier) + Rnd.get(-hitModifier, hitModifier));
	}
	
	public boolean calculateSkillSuccess(Creature enemy)
	{
		return Rnd.get(100) < ((BASE_LAND_RATE + _dex) - enemy.getDEX());
	}
	
	public boolean calculateSkillCritical()
	{
		return Rnd.get(100) < (BASE_CRIT_RATE + _dex);
	}
	
	public int calculateMagicSkillDamage()
	{
		final float skillModifier = _level * MAGIC_SKILL_DAMAGE_MODIFIER;
		return (int) ((_int * skillModifier) + Rnd.get(-skillModifier, skillModifier));
	}
	
	public int calculateMeleeSkillDamage()
	{
		final float skillModifier = _level * MELEE_SKILL_DAMAGE_MODIFIER;
		return (int) ((_str * skillModifier) + Rnd.get(-skillModifier, skillModifier));
	}
	
	public boolean isAlive()
	{
		return _isAlive;
	}
	
	public void onDeath()
	{
		// TODO: Send death animation.
	}
	
	@Override
	public boolean isCreature()
	{
		return true;
	}
	
	@Override
	public Creature asCreature()
	{
		return this;
	}
}
