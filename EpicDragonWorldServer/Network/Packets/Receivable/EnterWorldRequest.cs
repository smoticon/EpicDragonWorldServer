/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class EnterWorldRequest
{
    public EnterWorldRequest(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        string characterName = packet.ReadString();

        // Create a new PlayerInstance.
        Player player = new Player(client, characterName);
        // Add object to the world.
        WorldManager.AddObject(player);
        // Assign this player to client.
        client.SetActiveChar(player);
        // Send active player information to client.
        client.ChannelSend(new EnterWorldInformation(player));
        // Send and receive visible object information.
        PlayerInformation playerInfo = new PlayerInformation(player);
        foreach (Player nearby in WorldManager.GetVisiblePlayers(player))
        {
            // Send the information to the current player.
            client.ChannelSend(new PlayerInformation(nearby));
            // Send information to the other player as well.
            nearby.ChannelSend(playerInfo);
        }
    }
}
