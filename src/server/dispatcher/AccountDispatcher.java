package server.dispatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Profile;
import entities.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AccountDispatcher
{
    private Gson gson;

    public AccountDispatcher()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * this method is called to create a user by adding user inputed to the Json File
     * @param username - the username as a String
     */
    public String createUser(String username)
    {
        List<User> users = null;
        Type userType = new TypeToken<List<User>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/users.json");
            users = gson.fromJson(reader, userType);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (users == null) {
            users = new ArrayList<>();
        }

        users.add(new User(Integer.toString(username.hashCode())));
        try {
            FileWriter writer  = new FileWriter("src/server/data/users.json");
            gson.toJson(users, writer);
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

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

        profiles.add(new Profile(Integer.toString(username.hashCode())));
        try {
            FileWriter writer  = new FileWriter("src/server/data/profiles.json");
            gson.toJson(profiles, writer);
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "true";
    }

    public String login(String username)
    {
        List<User> users = null;
        Type userType = new TypeToken<List<User>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/server/data/users.json");
            users = gson.fromJson(reader, userType);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (users == null) {
            return "";
        }

        for(User u : users) {
            if(u.getUsername().equals(Integer.toString(username.hashCode()))) {
                return Integer.toString(username.hashCode());
            }
        }

        return "";
    }
}
