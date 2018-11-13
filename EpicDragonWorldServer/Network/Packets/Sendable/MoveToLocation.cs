/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class MoveToLocation : SendablePacket
{
    public MoveToLocation(WorldObject obj, float heading, int animState, int waterState)
    {
        // Send the data.
        WriteShort(9); // Packet id.
        WriteLong(obj.GetObjectId());
        WriteFloat(obj.GetLocation().GetX());
        WriteFloat(obj.GetLocation().GetY());
        WriteFloat(obj.GetLocation().GetZ());
        WriteFloat(heading);
        WriteShort(animState);
        WriteByte(waterState);
    }
}
