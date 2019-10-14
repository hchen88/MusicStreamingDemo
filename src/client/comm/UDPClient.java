package client.comm;


import java.net.*;
import java.io.*;

public class UDPClient
{
    public String send(String req, String semantic)
    {
        DatagramSocket aSocket = null;
        int count = 0;

        try {
            aSocket = new DatagramSocket();
            aSocket.setSoTimeout(3000);
            byte[] m = req.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            while(count < 3)
            {
                count++;
                try
                {
                    DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
//            System.out.println("Client Request = " + new String(request.getData()));
                    aSocket.send(request);

                    byte[] buffer = new byte[15000];
                    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(reply);

                    String rep = new String(reply.getData());
//            System.out.println("Client Reply = " + rep);
                    return rep;
                }
                catch (SocketTimeoutException e) {
                    System.out.println(e.getMessage());
                    if(!semantic.equals("at least one"))
                        break;
                }
            }
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally
        {
            if (aSocket != null) aSocket.close();
        }

        return "{\"ret\":\"Error\"}";
    }
}

