package entities;
/**
 * This Music streaming applicaiton is a standalone application that streams music.
 *
 * @author  Arin Babayan, Ken Nakama, Howard Chen
 * @version 1.0
 * @date   2015/09/2019
 */
import java.util.ArrayList;
import java.util.List;

public class Playlist
{
    private String name;
    private List<Song> songs;

    /**
     * constructor of a PLaylist
     * @param name- the name of the playlist as a string
     */
    public Playlist(String name)
    {
        this.name = name;
        songs = new ArrayList<>();
    }

    /**
     * constructor overloaded with name of playlist and list of songs
     * @param name - the name to set the Playlist to
     * @param songs - the list of intial songs in Playlist
     */
    public Playlist(String name, List<Song> songs)
    {
        this.name = name;
        this.songs = songs;
    }

    /**
     * getter for the name of the playlist
     * @return - the name of the playlist as a  string
     */
    public String getName()
    {
        return name;
    }

    /**
     * setter for the name of the playlist
     * @param name- name to set the playlist as a string
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * getter for the list of songs
     * @return - the list of songs
     */
    public List<Song> getSongs()
    {
        return songs;
    }

    /**
     * setter for Songs in the playlist
     * @param songs - the list of songs
     */
    public void setSongs(List<Song> songs)
    {
        this.songs = songs;
    }
}
