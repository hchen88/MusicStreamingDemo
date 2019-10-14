package entities;
/**
 * This Music streaming applicaiton is a standalone application that streams music.
 *
 * @author  Arin Babayan, Ken Nakama, Howard Chen
 * @version 1.0
 * @date   2015/09/2019
 */
public class User
{
    private String username;

    /**
     * constructor for User
     * @param username - username as a string
     */
    public User(String username)
    {
        this.username = username;
    }

    /**
     * getter method for Username
     * @return the username as a String
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * setter method for the userName
     * @param username - the username as a string
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
}
