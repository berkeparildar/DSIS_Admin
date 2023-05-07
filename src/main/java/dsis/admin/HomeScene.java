package dsis.admin;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HomeScene {
    private final SceneManager sceneManager;

    public HomeScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public Scene getScene() {
        // Create buttons for navigation
        Button signUpButton = new Button("Sign Up");
        Button editButton = new Button("Edit");
        Button addButton = new Button("Add");

        // Add action listeners to buttons
        signUpButton.setOnAction(event -> sceneManager.showSignUpScene());
        editButton.setOnAction(event -> sceneManager.showEditScene());
        addButton.setOnAction(event -> sceneManager.showAddScene());

        // Add buttons to a VBox layout
        VBox layout = new VBox(20, signUpButton, editButton, addButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add("styles.css");
        // Set the scene and return it
        return scene;
    }
}