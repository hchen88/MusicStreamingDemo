package entities;
/**
 * This Music streaming applicaiton is a standalone application that streams music.
 *
 * @author  Arin Babayan, Ken Nakama, Howard Chen
 * @version 1.0
 * @date   2015/09/2019
 */
public class Song
{
    private String id;
    private String title;


    public Song(String id) {
        this.id = id;
    }
    /**
     * constructor for a song
     * @param id - the id nunber of the song as a string
     * @param title - the title of the song as a string
     */
    public Song(String id, String title)
    {
        this.id = id;
        this.title = title;
    }

    /**
     * getter method for the song id
     * @return- the song id as a string
     */
    public String getId()
    {
        return id;
    }

    /**
     * the setter method for the id
     * @param id - the id  as a string
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * getter method for the title
     * @return - the title as a string
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * setter method for the title
     * @param title - the title as a string
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
}
