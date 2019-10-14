package client.controllers;

import client.proxy.Proxy;
import client.stream.CECS327InputStream;
import client.stream.PlayThread;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entities.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import client.stream.PlayThread;
import client.ui.MusicPlayer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/**
 * This class handles all the Login client.comm.ui control methods.
 */
public class PlayerController
{
    //instance variables/objects
    private Stage stage;
    private String username;
    private Gson gson;
    private PlayThread playThread;
    private Proxy proxy;
    private Player player;

    /**
     * Default constructor for PlayerController
     * @param stage - the stage object
     * @param username - (logged in)username as a string
     */
    public PlayerController(Stage stage, String username)
    {
        this.stage = stage;
        this.username = username;
        gson = new GsonBuilder().setPrettyPrinting().create();
        proxy = new Proxy();
    }

    /**
     * method that creates the BorderPane for the music player
     */
    public void createPane()
    {
        BorderPane pane = MusicPlayer.pane(this);
        MusicPlayer.form(pane);
        loadPlaylists();
        stage.setScene(new Scene(pane, 800, 600));
        stage.show();
    }

    /**
     * method to load all the playlists from the Json File.
     */
    public void loadPlaylists()
    {
        new Thread(() -> {
            JsonObject reply =
                    proxy.synchExecution("loadPlaylists", new String[]{username});

            List<Playlist> playlists;
            Type type = new TypeToken<List<Playlist>>() {}.getType();

            String s = reply.get("ret").getAsString();
            JsonReader reader = new JsonReader(new StringReader(s));
            reader.setLenient(true);
            playlists = gson.fromJson(reader, type);

            Platform.runLater(() -> MusicPlayer.displayPlaylists(playlists));
        }).start();
    }

    /**
     * this method searches for a specific song/artist
     * @param text - the string of text.
     * @param option - Artist/Song type to search for as a string
     */
    public void search(String text, String option)
    {
        new Thread(() -> {
            JsonObject reply =
                    proxy.synchExecution("search", new String[]{text, option});

            List<Song> songs;
            Type type = new TypeToken<List<Song>>() {}.getType();

            String s = reply.get("ret").getAsString();
            JsonReader reader = new JsonReader(new StringReader(s));
            reader.setLenient(true);
            songs = gson.fromJson(reader, type);

            Platform.runLater(() -> MusicPlayer.searchResults(songs));
        }).start();
    }

    /**
     * this method creates a new playlist on current user acount
     * @param playlist - the PLaylist name as a string
     */
    public void createPlaylist(String playlist)
    {
        new Thread(() -> {
            JsonObject reply =
                    proxy.synchExecution("createPlaylist", new String[]{username, playlist});

            Platform.runLater(() -> loadPlaylists());
        }).start();
    }

    /**
     * this method adds a song to selected playlist
     * @param playlistName - the selected playlist to add song to as a string
     * @param songId - the song id to add to playlist as a string
     * @param songTitle - the song title as a string
     */
    public void addToPlaylist(String playlistName, String songId, String songTitle)
    {
        new Thread(() -> {
            JsonObject reply =
                    proxy.synchExecution("addSongToPlaylist",
                            new String[]{username, playlistName, songId, songTitle});
        }).start();
        loadPlaylists();
    }

    /**
     * this method deletes the playlist selected
     * @param playlist- the playlistname to delete as a string
     */
    public void deletePlaylist(String playlist)
    {

        new Thread(() -> { JsonObject reply =
                proxy.synchExecution("deletePlaylist", new String[]{username, playlist});
            
        }).start();
        loadPlaylists();

    }

    /**
     * this method plays the mp3 after the button corresponding the song is clicked
     */
    public void mp3play()
    {
        playThread = new PlayThread(490183L, proxy);
        playThread.start();
    }

    /**
     * this method stops playing the mp3.
     */
    public void mp3stop()
    {
        playThread.stop();
    }

}
