/**
 * Author: Pantelis Andrianakis
 * Date: February 4th 2019
 */
class AnimatorUpdateRequest
{
    public AnimatorUpdateRequest(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        float velocityX = packet.ReadFloat();
        float velocityZ = packet.ReadFloat();
        bool triggerJump = packet.ReadByte() == 1;
        bool isInWater = packet.ReadByte() == 1;
        bool isGrounded = packet.ReadByte() == 1;

        // Broadcast movement.
        Player player = client.GetActiveChar();
        AnimatorUpdate animatorUpdate = new AnimatorUpdate(player.GetObjectId(), velocityX, velocityZ, triggerJump, isInWater, isGrounded);
        foreach (Player nearby in WorldManager.GetVisiblePlayers(player))
        {
            nearby.ChannelSend(animatorUpdate);
        }
    }
}

