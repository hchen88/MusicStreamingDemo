package client.ui;

import client.controllers.LoginController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 */
public class Login
{
    private Login() {}

    /**
     *
     * @return
     */
    public static GridPane pane()
    {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setPadding(new Insets(80, 40, 80, 40));

        ColumnConstraints labels = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        ColumnConstraints fields = new ColumnConstraints(400, 400, Double.MAX_VALUE);

        pane.getColumnConstraints().addAll(labels, fields);

        return pane;

    }

    public static void form(GridPane pane, LoginController controller)
    {
        Label nameLabel = new Label("Username");
        nameLabel.setFont(new Font("Arial", 24));
        pane.add(nameLabel, 0,1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        pane.add(nameField, 1,1);

        Button login = new Button("Login");
        login.setPrefHeight(40);
        login.setPrefWidth(150);
        login.setDefaultButton(true);
        pane.add(login, 1, 2);
        GridPane.setHalignment(login, HPos.CENTER);
        GridPane.setMargin(login, new Insets(10, 0,10,0));
        login.setOnAction(actionEvent -> controller.validate(nameField.getText()));

        Button create = new Button("Create Account");
        create.setPrefHeight(40);
        create.setPrefWidth(150);
        create.setDefaultButton(true);
        pane.add(create, 1, 3);
        GridPane.setHalignment(create, HPos.CENTER);
        GridPane.setMargin(create, new Insets(10, 0,10,0));
        create.setOnAction(actionEvent -> controller.createUser(nameField.getText()));

    }

}
