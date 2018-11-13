/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class EnterWorldInformation : SendablePacket
{
    public EnterWorldInformation(Player player)
    {
        // Packet id.
        WriteShort(5);
        WriteLong(player.GetObjectId());
        // TODO: Send more player information.
    }
}
