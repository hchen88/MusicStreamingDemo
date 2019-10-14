package server.dispatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Playlist;
import entities.Profile;
import entities.Song;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaylistDispatcher
{
    private Gson gson;

    public PlaylistDispatcher()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String createPlaylist(String username, String playlistName)
    {
        List<Profile> profiles = null;
        Type type = new TypeToken<List<Profile>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/profiles.json");
            profiles = gson.fromJson(reader, type);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (profiles == null) {
            profiles = new ArrayList<>();
        }

        List<Playlist> playlists = null;
        for(Profile p : profiles){
            if(p.getUsername().equals(username)){
                playlists = p.getPlaylists();
            }
        }

        for(Playlist p : playlists){
            if(p.getName().equals(playlistName)){
                return "";
            }
        }
        playlists.add(new Playlist(playlistName));

        try {
            FileWriter writer  = new FileWriter("src/server/data/profiles.json");
            gson.toJson(profiles, writer);
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String deletePlaylist(String username, String playlistName) {
        List<Profile> profiles = null;
        Type type = new TypeToken<List<Profile>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/profiles.json");
            profiles = gson.fromJson(reader, type);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (profiles == null) {
            profiles = new ArrayList<>();
        }

        List<Playlist> playlists = null;
        for(Profile p : profiles){
            if(p.getUsername().equals(username)){
                playlists = p.getPlaylists();
            }
        }

        Playlist p;
        if(playlists != null){
            Iterator<Playlist> iterator = playlists.iterator();
            while (iterator.hasNext()) {
                p = iterator.next();
                if(p.getName().equals(playlistName))
                    iterator.remove();
            }
        }

        try {
            FileWriter writer  = new FileWriter("src/server/data/profiles.json");
            gson.toJson(profiles, writer);
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String addSongToPlaylist(
            String username, String playistName, Long songId, String songTitle) {

        List<Profile> profiles = null;
        Type type = new TypeToken<List<Profile>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/profiles.json");
            profiles = gson.fromJson(reader, type);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (profiles == null) {
            profiles = new ArrayList<>();
        }

        List<Playlist> playlists = null;
        for(Profile p : profiles){
            if(p.getUsername().equals(username)){
                playlists = p.getPlaylists();
            }
        }

        Playlist correctPlaylist = null;
        if(playlists != null){
            for(Playlist p : playlists){
                if(p.getName().equals(playistName)){
                     correctPlaylist = p;
                }
            }
            List<Song> songList =  correctPlaylist.getSongs();
            for(Song s : songList ) {
                if(s.getId().equals(songId.toString())){
                    return "false";
                }
            }
            correctPlaylist.getSongs().add(new Song(songId.toString()));
        }

        try {
            FileWriter writer  = new FileWriter("src/server/data/profiles.json");
            gson.toJson(profiles, writer);
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String loadPlaylists(String username)
    {
        List<Profile> profiles = null;
        Type type = new TypeToken<List<Profile>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/profiles.json");
            profiles = gson.fromJson(reader, type);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (profiles == null) {
            profiles = new ArrayList<>();
        }

        List<Playlist> playlists = null;
        for(Profile p : profiles){
            if(p.getUsername().equals(username)){
                playlists = p.getPlaylists();
                return gson.toJson(playlists);
            }
        }

        return "";
    }

}
