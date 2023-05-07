package dsis.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class AddScene {
    private final SceneManager sceneManager;
    private final HashMap<String, String> courseData;

    private final GridPane gridPane;


    public AddScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.courseData = new HashMap<>();
        gridPane = new GridPane();
    }

   

    public Scene getScene() {
        Label headerLabel = new Label("Add Course");

        Label schoolIdLabel = new Label("School ID:");
        TextField schoolIdTextField = new TextField();

        Label numberLabel = new Label("Number:");
        TextField numberTextField = new TextField();

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();

        Label creditLabel = new Label("Credit:");
        TextField creditTextField = new TextField();

        Label hoursLabel = new Label("Hours:");
        TextField hoursTextField = new TextField();

        Label codeLabel = new Label("Code:");
        TextField codeTextField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idTextField = new TextField();

        Label evalCountLabel = new Label("Evaluation Count:");
        TextField evalCountTextField = new TextField();

        Label weightsLabel = new Label("Weights:");
        TextField weightsTextField = new TextField();

        Label namesLabel = new Label("Names:");
        TextField namesTextField = new TextField();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));

        gridPane.add(schoolIdLabel, 0, 0);
        gridPane.add(schoolIdTextField, 1, 0);

        gridPane.add(numberLabel, 0, 1);
        gridPane.add(numberTextField, 1, 1);

        gridPane.add(nameLabel, 0, 2);
        gridPane.add(nameTextField, 1, 2);

        gridPane.add(creditLabel, 0, 3);
        gridPane.add(creditTextField, 1, 3);

        gridPane.add(hoursLabel, 0, 4);
        gridPane.add(hoursTextField, 1, 4);

        gridPane.add(codeLabel, 0, 5);
        gridPane.add(codeTextField, 1, 5);

        gridPane.add(idLabel, 0, 6);
        gridPane.add(idTextField, 1, 6);

        gridPane.add(evalCountLabel, 0, 7);
        gridPane.add(evalCountTextField, 1, 7);

        gridPane.add(weightsLabel, 0, 8);
        gridPane.add(weightsTextField, 1, 8);

        gridPane.add(namesLabel, 0, 9);
        gridPane.add(namesTextField, 1, 9);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            // Store data in the courseData hashmap
            courseData.put("schoolId", schoolIdTextField.getText());
            courseData.put("number", numberTextField.getText());
            courseData.put("name", nameTextField.getText());
            courseData.put("credit", creditTextField.getText());
            courseData.put("hours", hoursTextField.getText());
            courseData.put("code", codeTextField.getText());
            courseData.put("id", idTextField.getText());
            courseData.put("evalCount", evalCountTextField.getText());
            courseData.put("weightsArray", weightsTextField.getText());
            courseData.put("namesArray", namesTextField.getText());

            // Switch to the confirmation scene
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            // Switch to the home scene
            sceneManager.showHomeScene();
        });
        
        HBox hBox = new HBox(backButton, submitButton);
        gridPane.add(hBox, 1, 10);
        hBox.setSpacing(10);


        Scene scene = new Scene(gridPane, 400, 600);
        scene.getStylesheets().add("styles.css");
        // Set the scene and return it
        return scene;
    }

    public HashMap<String, String> getCourseData() {
        return courseData;
    }
}