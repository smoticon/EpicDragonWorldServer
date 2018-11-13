/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class CharacterDeletionResult : SendablePacket
{
    public CharacterDeletionResult()
    {
        // Send the data.
        WriteShort(4); // Packet id.
    }
}
