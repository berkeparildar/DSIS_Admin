package dsis.admin;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Confirmation {
    private final SceneManager sceneManager;
    private final String message;

    public Confirmation(SceneManager sceneManager, String message) {
        this.sceneManager = sceneManager;
        this.message = message;
    }

    public Scene getScene() {
        Label confirmationLabel = new Label(message);
        confirmationLabel.setFont(new Font("Arial", 18));

        Button homeButton = new Button("Back to Home");
        homeButton.setOnAction(event -> sceneManager.showHomeScene());

        VBox layout = new VBox(20, confirmationLabel, homeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add("styles.css");
        // Set the scene and return it
        return scene;
    }
}