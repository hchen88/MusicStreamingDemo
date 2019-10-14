package entities;
/**
 * This Music streaming applicaiton is a standalone application that streams music.
 *
 * @author  Arin Babayan, Ken Nakama, Howard Chen
 * @version 1.0
 * @date   2015/09/2019
 */
public class Release
{
    private int id;
    private String name;

    /**
     * constructor for a Release
     * @param id - the id number as an int
     * @param name - the name as a string
     */
    public Release(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * getter for release id
     * @return - the release id as a integer
     */
    public int getId()
    {
        return id;
    }

    /**
     * setter for the id number
     * @param id - the id number to be set to as an integer
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * getter for the name
     * @return - the name as a string
     */
    public String getName()
    {
        return name;
    }

    /**
     * setter method for the name
     * @param name - the name as a string
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
