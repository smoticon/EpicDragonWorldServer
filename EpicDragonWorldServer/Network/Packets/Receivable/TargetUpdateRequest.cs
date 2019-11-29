/**
 * Author: Pantelis Andrianakis
 * Date: November 29th 2019
 */
public class TargetUpdateRequest
{
    public TargetUpdateRequest(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        long targetObjectId = packet.ReadLong();

        // Get player.
        Player player = client.GetActiveChar();

        // Remove target.
        if (targetObjectId < 0)
        {
            player.SetTarget(null);
            return;
        }

        // Find target WorldObject.
        foreach (WorldObject obj in WorldManager.GetVisibleObjects(player))
        {
            if (obj != null && obj.GetObjectId() == targetObjectId)
            {
                player.SetTarget(obj);
                return;
            }
        }
    }
}
