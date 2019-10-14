package server.dispatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Artist;
import entities.Song;
import entities.Track;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchDispatcher
{
    private Gson gson;

    public SearchDispatcher()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * this method searches for a specific song/artist
     * @param text - the string of text.
     * @param option - Artist/Song type to search for as a string
     */
    public String search(String text, String option)
    {
        List<Track> tracks = null;
        Type type = new TypeToken<List<Track>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/music.json");
            tracks = gson.fromJson(reader, type);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Song song;
        Artist artist;
        List<Song> songs = new ArrayList<>();
        for(Track t : tracks){
            if(option.equals("Song")){
                song = t.getSong();
                if(song.getTitle().equals(text)){
                    songs.add(song);
                    break;
                }
            }
            else if(option.equals("Artist")){
                artist = t.getArtist();
                if(artist.getName().equals(text)){
                    songs.add(t.getSong());
                }
            }
            else{
                artist = t.getArtist();
                if(artist.getTerms().equals(text)){
                    songs.add(t.getSong());
                }
            }
        }

        return gson.toJson(songs);
    }
}
