using System;
using System.Net.Sockets;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class GameClient
{
    readonly NetworkStream networkStream;
    readonly string ip;
    string accountName = "";
    Player activeChar;

    public GameClient(NetworkStream stream, string address)
    {
        networkStream = stream;
        // Clean IP address.
        ip = address.Substring(0, address.LastIndexOf(']'));
        int start = ip.LastIndexOf(':') + 1;
        ip = ip.Substring(start, ip.Length - start);
    }

    public async void ChannelSend(SendablePacket packet)
    {
        try
        {
            await networkStream.WriteAsync(packet.GetSendableBytes());
        }
        catch (Exception)
        {
            // Connection closed from client side.
        }
    }

    public string GetIp()
    {
        return ip;
    }

    public string GetAccountName()
    {
        return accountName;
    }

    public void SetAccountName(string name)
    {
        accountName = name;
    }

    public Player GetActiveChar()
    {
        return activeChar;
    }

    public void SetActiveChar(Player activeChar)
    {
        this.activeChar = activeChar;
    }
}
