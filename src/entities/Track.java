package entities;
/**
 * This Music streaming applicaiton is a standalone application that streams music.
 *
 * @author  Arin Babayan, Ken Nakama, Howard Chen
 * @version 1.0
 * @date   2015/09/2019
 */
public class Track
{
    private Release release;
    private Artist artist;
    private Song song;

    /**
     * constructor for the Track
     * @param release - the release as a Release object
     * @param artist - the artist as an Artist object
     * @param song - the song as a Song Object
     */
    public Track(Release release, Artist artist, Song song)
    {
        this.release = release;
        this.artist = artist;
        this.song = song;
    }

    /**
     * getter method for the release
     * @return - the release as a Release Object
     */
    public Release getRelease()
    {
        return release;
    }

    /**
     * setter method for the release
     * @param release - the release as a Release Object
     */
    public void setRelease(Release release)
    {
        this.release = release;
    }

    /**
     * the getter method for an Artist
     * @return - the artist as an Artist Object
     */
    public Artist getArtist()
    {
        return artist;
    }

    /**
     * the setter method for the Artist
     * @param artist - the artist as an Artist object
     */
    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }

    /**
     * getter method for the Song
     * @return - the song as a Song object
     */
    public Song getSong()
    {
        return song;
    }

    /**
     * setter method for the song
     * @param song - the song as a Song object
     */
    public void setSong(Song song)
    {
        this.song = song;
    }
}
