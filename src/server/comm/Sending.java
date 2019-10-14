package server.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Sending implements Runnable
{
    DatagramSocket socket;
    BlockingQueue<DatagramPacket> queue;
    Thread worker;

    public Sending(DatagramSocket socket)
    {
        this.socket = socket;
        queue = new ArrayBlockingQueue<>(1000);
    }

    public void start()
    {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                DatagramPacket reply = queue.take();
                socket.send(reply);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(DatagramPacket reply)
    {
        queue.add(reply);
    }

}
