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

public class AddTermScene {

    private SceneManager sceneManager;
    private HashMap<String, String> termData;

    public AddTermScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        termData = new HashMap<>();
    }

    public Scene getScene() {
        Label headingLabel = new Label("Add new term");
        headingLabel.setFont(Font.font("Arial", 20));

        Label schoolIdLabel = new Label("School ID:");
        TextField schoolIdTextField = new TextField();
        Label yearLabel = new Label("Year:");
        TextField yearTextField = new TextField();
        Label seasonLabel = new Label("Season:");
        TextField seasonTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            termData.put("studentId", schoolIdTextField.getText());
            termData.put("year", yearTextField.getText());
            termData.put("season", seasonTextField.getText());
            try {
                CloudConnect.callFunction("add-term", termData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sceneManager.setScene(new Confirmation(sceneManager, "User created successfully!").getScene());
            sceneManager.setScene(new Confirmation(sceneManager, "Added a new term!").getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> sceneManager.showHomeScene());

        VBox layout = new VBox(20, headingLabel, schoolIdLabel, schoolIdTextField, yearLabel, yearTextField,
                seasonLabel, seasonTextField, submitButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 1200, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

}