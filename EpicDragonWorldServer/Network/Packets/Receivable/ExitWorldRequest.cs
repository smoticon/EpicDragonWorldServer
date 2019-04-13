/**
 * Author: Pantelis Andrianakis
 * Date: February 3rd 2019
 */
class ExitWorldRequest
{
    public ExitWorldRequest(GameClient client, ReceivablePacket packet)
    {
        WorldManager.RemoveObject(client.GetActiveChar());
    }
}
