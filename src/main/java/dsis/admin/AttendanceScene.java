package dsis.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.HashMap;

public class AttendanceScene {
    private SceneManager sceneManager;
    private HashMap<String, String> studentIdMap;

    public AttendanceScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public Scene getScene() {
        Label headingLabel = new Label("Increase a student's attendance");
        headingLabel.setFont(Font.font("Arial", 20));

        Label schoolIdLabel = new Label("School ID:");
        TextField schoolIdTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            studentIdMap.put("studentId", schoolIdTextField.getText());
            try {
                System.out.println(CloudConnect.callFunction("attendance", studentIdMap));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sceneManager.setScene(new Confirmation(sceneManager, "Set the course grade!").getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> sceneManager.showHomeScene());

        VBox layout = new VBox(20, headingLabel, schoolIdLabel, schoolIdTextField, submitButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 1200, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}
