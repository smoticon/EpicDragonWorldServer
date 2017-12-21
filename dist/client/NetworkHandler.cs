using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using UnityEngine;

public class NetworkHandler : MonoBehaviour
{
    //For Socket Send
    Socket sender;
    string ip_addy = "127.0.0.1";
    int port = 5055;
    ThreadStart ts;
    Thread socketThread;
    bool socketThreadStarted = false;
    bool socketThreadFinishLoading = false;
    bool socketThreadError = false;
    int connectionTimeOut = 5000;

    //For Socket Receive connection
    ThreadStart ts2;
    Thread receiveThread;
    bool receiveThreadStarted = false;
    bool receiveThreadFinishLoading = false;
    string messageReceived = "";
    string messageReceived2 = "";
    int zeroByteCount = 0;
    List<string> receiveMsgList = new List<string>();
    byte[] bytes = new byte[1024];

    void OnApplicationQuit()
    {
        if (sender != null)
        {
            if (sender.Connected)
            {
                sender.Close();
                receiveThreadStarted = false;
            }
            Debug.Log("Application ending after " + Time.time + " seconds");
        }
    }

    void Awake()
    {
        DontDestroyOnLoad(transform.gameObject);
    }

    // Use this for initialization
    void Start()
    {
        if (!socketThreadStarted)
        {
            Debug.Log("Going to connect to Server IP : " + ip_addy + " on Port : " + port);
            socketThreadStarted = true;
            ts = new ThreadStart(openSocket);
            socketThread = new Thread(ts);
            socketThread.Start();
        }
    }

    // Update is called once per frame
    void Update()
    {
    }

    void openSocket()
    {
        try
        {
            socketThreadError = false;
            sender = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            Debug.Log("Trying to connect");
            System.IAsyncResult result = sender.BeginConnect(ip_addy, port, null, null);
            bool success = result.AsyncWaitHandle.WaitOne(connectionTimeOut, true);

            if (!success)
            {
                socketThreadError = true;
                // NOTE, MUST CLOSE THE SOCKET
                Debug.Log("FAILED TO CONNECT");
                sender.Close();
                // throw new ApplicationException("Failed to connect server.");
            }
            else
            {
                if (sender.Connected)
                {
                    Debug.Log("Connected");

                    //Start Receive thread
                    receiveThreadStarted = true;
                    ts2 = new ThreadStart(receiveHandler);
                    receiveThread = new Thread(ts2);
                    receiveThread.Start();
                }
                else
                {
                    Debug.Log("Failed to connect");
                    sender.Close();
                }
            }
        }
        catch (SocketException se)
        {
            Debug.Log("SocketException :" + se.SocketErrorCode);
        }
        socketThreadFinishLoading = true;
    }


    void receiveHandler()
    {
        int bytesRec = 0;
        while (receiveThreadStarted)
        {
            if (bytesRec == 0)
            {
                zeroByteCount++;
                if (zeroByteCount > 20)
                {
                    //Connection closed
                    receiveThreadStarted = false;
                }
            }
            bytesRec = sender.Receive(bytes);

            messageReceived = Encoding.ASCII.GetString(bytes, 0, bytesRec);

            if (messageReceived.Contains("\n"))
            {
                string[] split = messageReceived.Split(new string[] { "\n" }, System.StringSplitOptions.None);
                for (int i = 0; i < split.Length - 1; i++)
                {
                    receiveMsgList.Add(split[i]);
                    Debug.Log("Added " + split[i] + " into List");
                }
            }
        }
    }
}