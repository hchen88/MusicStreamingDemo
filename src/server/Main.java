package server;

import server.comm.UDPServer;

public class Main
{
    public static void main(String[] args)
    {
        UDPServer server = new UDPServer();
        server.start();
    }
}
