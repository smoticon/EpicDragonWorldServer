package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.model.WorldObject;

/**
 * @author Pantelis Andrianakis
 */
public class Creature extends WorldObject
{
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
