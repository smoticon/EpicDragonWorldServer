/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class LocationUpdate : SendablePacket
{
    public LocationUpdate(WorldObject obj, float heading)
    {
        // Send the data.
        WriteShort(9); // Packet id.
        WriteLong(obj.GetObjectId());
        WriteFloat(obj.GetLocation().GetX());
        WriteFloat(obj.GetLocation().GetY());
        WriteFloat(obj.GetLocation().GetZ());
        WriteFloat(obj.GetLocation().GetHeading());
    }
}
