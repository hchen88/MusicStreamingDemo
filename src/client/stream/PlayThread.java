package client.stream;

import client.proxy.Proxy;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;

public class PlayThread implements Runnable
{
    private volatile boolean running = true;
    private Thread worker;
    private Player mp3player;
    private Long fileName;
    private Proxy proxy;

    public PlayThread(Long fileName, Proxy proxy)
    {
        this.fileName = fileName;
        this.proxy = proxy;
    }

    public void start()
    {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run()
    {
        while(running){
            try {
                InputStream is = new CECS327InputStream(fileName, proxy);
                mp3player = new Player(is);
                mp3player.play();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop()
    {
        worker.stop();
    }
}
