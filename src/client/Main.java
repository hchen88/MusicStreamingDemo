package client; /**
 * This Music streaming applicaiton is a standalone application that streams music.
 *
 * @author  Arin Babayan, Ken Nakama, Howard Chen
 * @version 1.0
 * @date   2015/09/2019
 */

import client.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * this is the main class that extends Application from the JAVAFX library
 */
public class Main extends Application {

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Music Streaming"); //sets title to Music Streaming
        LoginController login = new LoginController(stage); // creates LoginController Object with a stage passed in
        login.createPane(); // creates the Pane for the login screen.
    }

    /**
     * main method that calls Launch method to start JavaFx gui
     * @param args
     */
    public static void main(String[] args) {
        launch(args); //launches gui with command line args if necessary

    }

}
