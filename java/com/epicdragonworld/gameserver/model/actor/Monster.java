package com.epicdragonworld.gameserver.model.actor;

/**
 * @author Pantelis Andrianakis
 */
public class Monster extends Creature
{
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
