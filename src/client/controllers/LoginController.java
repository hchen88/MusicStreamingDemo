package client.controllers;

import com.google.gson.JsonObject;
import client.proxy.Proxy;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import client.ui.Login;

/**
 * This class handles all the Login client.comm.ui control methods.
 */
public class LoginController
{
    private Stage stage;
    private Proxy proxy;

    /**
     * this constructor for LoginController with a stage.
     * @param stage - the Stage to set as an object
     */
    public LoginController(Stage stage)
    {
        this.stage = stage;
        this.proxy = new Proxy();
    }

    /**
     * this method creates the Pane for the scene
     */
    public void createPane()
    {
        GridPane pane = Login.pane();
        Login.form(pane, this);
        stage.setScene(new Scene(pane, 800, 600));
        stage.show();
    }

    /**
     * this method validates username by checking the JSON of Users
     * @param username- the username as a String to validate
     */
    public void validate(String username)
    {
        new Thread(() -> {
            JsonObject reply =
                    proxy.synchExecution("login", new String[]{username});

            String ticket = reply.get("ret").toString().replaceAll("\"", "");
//        System.out.println("Ticket = " + ticket);

            Platform.runLater(() -> {
                if(!ticket.equals("Error")) {
                    PlayerController player = new PlayerController(stage, ticket);
                    player.createPane();
                }
                else
                    System.out.println("Error: Network unavailable, try again later");
            });

        }).start();

    }

    /**
     * this method is called to create a user by adding user inputed to the Json File
     * @param username - the username as a String
     */
    public void createUser(String username)
    {
        new Thread(() -> {
            JsonObject reply =
                    proxy.synchExecution("createUser", new String[]{username});
        }).start();
    }
}
