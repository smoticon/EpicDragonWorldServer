/**
 * @author Pantelis Andrianakis
 */
public class PlayerInformation : SendablePacket
{
    public PlayerInformation(Player player)
    {
        // Packet id.
        WriteShort(6);
        // Player information.
        WriteLong(player.GetObjectId());
        WriteShort(player.GetClassId());
        WriteString(player.GetName());
        WriteFloat(player.GetLocation().GetX());
        WriteFloat(player.GetLocation().GetY());
        WriteFloat(player.GetLocation().GetZ());
        WriteFloat(player.GetLocation().GetHeading());
    }
}
