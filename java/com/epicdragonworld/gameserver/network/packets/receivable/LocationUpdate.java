package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.Player;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.MoveToLocation;

/**
 * @author Pantelis Andrianakis
 */
public class LocationUpdate
{
	public LocationUpdate(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final long objectId = packet.readLong();
		final float posX = (float) packet.readDouble(); // TODO: Client WriteFloat
		final float posY = (float) packet.readDouble(); // TODO: Client WriteFloat
		final float posZ = (float) packet.readDouble(); // TODO: Client WriteFloat
		final float heading = (float) packet.readDouble(); // TODO: Client WriteFloat
		final int animState = packet.readShort();
		final int waterState = packet.readShort();
		
		// Update player location.
		final WorldObject object = WorldManager.getObject(objectId);
		if (object != null)
		{
			object.getLocation().setX(posX);
			object.getLocation().setY(posY);
			object.getLocation().setZ(posZ);
			
			// Broadcast movement.
			for (Player nearby : WorldManager.getVisiblePlayers(object))
			{
				nearby.channelSend(new MoveToLocation(object, heading, animState, waterState));
			}
		}
	}
}
