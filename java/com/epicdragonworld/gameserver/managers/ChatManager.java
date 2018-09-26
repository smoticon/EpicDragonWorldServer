package com.epicdragonworld.gameserver.managers;

import com.epicdragonworld.gameserver.model.Location;
import com.epicdragonworld.gameserver.model.actor.Player;
import com.epicdragonworld.gameserver.network.packets.sendable.ChatResult;

/**
 * @author Pantelis Andrianakis
 */
public class ChatManager
{
	private static final byte CHAT_TYPE_SYSTEM = 0;
	private static final byte CHAT_TYPE_NORMAL = 1;
	private static final byte CHAT_TYPE_MESSAGE = 2;
	private static final String COMMAND_PERSONAL_MESSAGE = "/tell ";
	private static final String COMMAND_LOCATION = "/loc";
	private static final String SYS_NAME = "System";
	private static final String MSG_TO = "To ";
	
	public static void handleChat(Player sender, String message)
	{
		// Check if message is empty.
		message = message.trim();
		if (message.isEmpty())
		{
			return;
		}
		
		final String lowercaseMessage = message.toLowerCase().replaceAll("\\s{2,}", " "); // Also remove all double spaces.
		if (lowercaseMessage.equals(COMMAND_LOCATION))
		{
			final Location location = sender.getLocation();
			sender.channelSend(new ChatResult(CHAT_TYPE_SYSTEM, SYS_NAME, "Your location is " + location.getX() + " " + location.getY() + " " + location.getZ()));
		}
		else if (lowercaseMessage.startsWith(COMMAND_PERSONAL_MESSAGE))
		{
			final String[] lowercaseMessageSplit = lowercaseMessage.split(" ");
			if (lowercaseMessageSplit.length < 3) // Check for parameters.
			{
				sender.channelSend(new ChatResult(CHAT_TYPE_SYSTEM, SYS_NAME, "Incorrect syntax. Use /tell [name] [message]."));
				return;
			}
			
			final Player receiver = WorldManager.getPlayerByName(lowercaseMessageSplit[1]);
			if (receiver == null)
			{
				sender.channelSend(new ChatResult(CHAT_TYPE_SYSTEM, SYS_NAME, "Player was not found."));
			}
			else
			{
				// Step by step cleanup, to avoid problems with extra/double spaces on original message.
				message = message.substring(lowercaseMessageSplit[0].length(), message.length()).trim(); // Remove command.
				message = message.substring(lowercaseMessageSplit[1].length(), message.length()).trim(); // Remove receiver name.
				sender.channelSend(new ChatResult(CHAT_TYPE_MESSAGE, MSG_TO + receiver.getName(), message));
				receiver.channelSend(new ChatResult(CHAT_TYPE_MESSAGE, sender.getName(), message));
			}
		}
		else // Normal message.
		{
			sender.channelSend(new ChatResult(CHAT_TYPE_NORMAL, sender.getName(), message));
			for (Player player : WorldManager.getVisiblePlayers(sender))
			{
				player.channelSend(new ChatResult(CHAT_TYPE_NORMAL, sender.getName(), message));
			}
		}
	}
}
