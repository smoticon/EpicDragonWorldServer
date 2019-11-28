/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class NpcInformation : SendablePacket
{
    public NpcInformation(Npc npc)
    {
        // Packet id.
        WriteShort(7);
        // Npc information.
        WriteLong(npc.GetObjectId());
        WriteInt(npc.GetNpcHolder().GetNpcId());
        WriteFloat(npc.GetLocation().GetX());
        WriteFloat(npc.GetLocation().GetY());
        WriteFloat(npc.GetLocation().GetZ());
        WriteFloat(npc.GetLocation().GetHeading());
        WriteLong(npc.GetCurrentHp());
    }
}
