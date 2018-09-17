package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.model.WorldObject;

/**
 * @author Pantelis Andrianakis
 */
public class Npc extends WorldObject
{
	@Override
	public boolean isNpc()
	{
		return true;
	}
	
	@Override
	public Npc asNpc()
	{
		return this;
	}
}
