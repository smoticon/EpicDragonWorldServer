package com.epicdragonworld.gameserver.model.actor;

/**
 * @author Pantelis Andrianakis
 */
public class Monster extends Creature
{
	public Monster()
	{
		// TODO: Monster templates.
		// TODO: Set monster stats (STA/STR/DEX/INT).
		// TODO: Set monster level.
	}
	
	@Override
	public boolean isMonster()
	{
		return true;
	}
	
	@Override
	public Monster asMonster()
	{
		return this;
	}
}
