package server.comm;

import server.dispatcher.Dispatcher;

import java.net.DatagramPacket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Receiving implements Runnable
{
    Dispatcher dispatcher;
    BlockingQueue<DatagramPacket> queue;
    Thread worker;
    Sending sending;

    public Receiving(Sending sending, Dispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
        queue = new ArrayBlockingQueue<>(1000);
        this.sending = sending;
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
                DatagramPacket request = queue.take();
//                System.out.println("Receiving Request = " + new String(request.getData()));
                String ret = dispatcher.dispatch(new String(request.getData()));
                byte[] r = ret.getBytes();
                DatagramPacket reply = new DatagramPacket(r,
                        r.length, request.getAddress(), request.getPort());

//                System.out.println("Receiving Reply = " + new String(reply.getData()));

                sending.add(reply);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void add(DatagramPacket request)
    {
        queue.add(request);
    }
}
