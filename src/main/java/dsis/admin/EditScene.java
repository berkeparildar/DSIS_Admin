package dsis.admin;

import dsis.admin.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.HashMap;

public class EditScene {

    private SceneManager sceneManager;
    private HashMap<String, String> schoolData;

    public EditScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        schoolData = new HashMap<>();
        schoolData.put("schoolId", "1234"); 
    }

    public Scene getScene() {
        Label headingLabel = new Label("Edit School Information");
        headingLabel.setFont(Font.font("Arial", 20));

        Label schoolIdLabel = new Label("School ID:");
        TextField schoolIdTextField = new TextField(schoolData.get("schoolId"));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            schoolData.put("schoolId", schoolIdTextField.getText());
            sceneManager.setScene(new Confirmation(sceneManager, "School information updated!").getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> sceneManager.showHomeScene());

        VBox layout = new VBox(20, headingLabel, schoolIdLabel, schoolIdTextField, submitButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add("styles.css");
        // Set the scene and return it
        return scene;
    }

}