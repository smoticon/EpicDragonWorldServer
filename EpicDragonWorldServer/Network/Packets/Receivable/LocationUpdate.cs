/**
 * @author Pantelis Andrianakis
 */
public class LocationUpdate
{
    public LocationUpdate(GameClient client, ReceivablePacket packet)
    {
        // Read data.
        float posX = (float)packet.ReadDouble(); // TODO: Client WriteFloat
        float posY = (float)packet.ReadDouble(); // TODO: Client WriteFloat
        float posZ = (float)packet.ReadDouble(); // TODO: Client WriteFloat
        float heading = (float)packet.ReadDouble(); // TODO: Client WriteFloat
        int animState = packet.ReadShort();
        int waterState = packet.ReadByte();

        // Update player location.
        Player player = client.GetActiveChar();
        LocationHolder location = player.GetLocation();
        location.SetX(posX);
        location.SetY(posY);
        location.SetZ(posZ);
        location.SetHeading(heading);

        // Broadcast movement.
        foreach (Player nearby in WorldManager.GetVisiblePlayers(player))
        {
            nearby.ChannelSend(new MoveToLocation(player, heading, animState, waterState));
        }
    }
}
