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

public class Profile
{
    private String username;
    private List<Playlist> playlists;

    /**
     * constructor that sets  Username
     * @param username - the  username as a string
     */
    public Profile(String username)
    {
        this.username = username;
        playlists = new ArrayList<>();
    }

    /**
     * constructor that overloads usernamme and playlist
     * @param username - the username as a string
     * @param playlists - the playlist as a list
     */
    public Profile(String username, List<Playlist> playlists)
    {
        this.username = username;
        this.playlists = playlists;
    }

    /**
     * getter for the Username
     * @return - username as a string
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * setter for the Username
     * @param username - username as a string
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * getter for the playlist
     * @return - the playlist as a list
     */
    public List<Playlist> getPlaylists()
    {
        return playlists;
    }

    /**
     * setter for the playlist
     * @param playlists - the playlist as a list
     */
    public void setPlaylists(List<Playlist> playlists)
    {
        this.playlists = playlists;
    }
}
