package server.comm;

import server.dispatcher.*;

import java.net.*;
import java.io.*;

public class UDPServer
{
    DatagramSocket aSocket;
    Dispatcher dispatcher;
    Receiving receiving;
    Sending sending;

    public UDPServer()
    {
        dispatcher = new Dispatcher();
        AccountDispatcher account = new AccountDispatcher();
        dispatcher.registerObject(account, "AccountServices");
        SearchDispatcher search = new SearchDispatcher();
        dispatcher.registerObject(search, "SearchServices");
        SongDispatcher song = new SongDispatcher();
        dispatcher.registerObject(song, "SongServices");
        PlaylistDispatcher playlist = new PlaylistDispatcher();
        dispatcher.registerObject(playlist, "PlaylistServices");

        openSocket();

        sending = new Sending(aSocket);
        sending.start();
        receiving = new Receiving(sending, dispatcher);
        receiving.start();

    }

    public void openSocket()
    {
        aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public void start()
    {
        try {
            while (true) {
                byte[] buffer = new byte[8192];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                receiving.add(request);

            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {if(aSocket != null) aSocket.close();}

    }

}
