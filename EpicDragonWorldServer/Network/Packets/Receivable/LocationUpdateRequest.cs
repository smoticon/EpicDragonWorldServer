/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class LocationUpdateRequest
{
    public LocationUpdateRequest(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        float posX = packet.ReadFloat();
        float posY = packet.ReadFloat();
        float posZ = packet.ReadFloat();
        float heading = packet.ReadFloat();

        // Get player.
        Player player = client.GetActiveChar();

        // Check if player is outside of world bounds.
        if (posX < Config.WORLD_MINIMUM_X || posX > Config.WORLD_MAXIMUM_X || posY < Config.WORLD_MINIMUM_Y || posY > Config.WORLD_MAXIMUM_Y || posZ < Config.WORLD_MINIMUM_Z || posZ > Config.WORLD_MAXIMUM_Z)
        {
            client.ChannelSend(new Logout());
            return;
        }

        // Update player location.
        player.SetLocation(new LocationHolder(posX, posY, posZ, heading));

        // Broadcast movement.
        LocationUpdate locationUpdate = new LocationUpdate(player, heading);
        foreach (Player nearby in WorldManager.GetVisiblePlayers(player))
        {
            nearby.ChannelSend(locationUpdate);
        }
    }
}
