/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class ObjectInfoRequest
{
    public ObjectInfoRequest(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        long objectId = packet.ReadLong();

        // Get the acting player.
        Player player = client.GetActiveChar();
        // Send the information.
        foreach (WorldObject obj in WorldManager.GetVisibleObjects(player))
        {
            if (obj.GetObjectId() == objectId)
            {
                if (obj.IsPlayer())
                {
                    client.ChannelSend(new PlayerInformation(obj.AsPlayer()));
                }
                // TODO: Other objects - NpcInformation?
                break;
            }
        }
    }
}
