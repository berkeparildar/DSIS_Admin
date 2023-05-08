package dsis.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class AddCourseScene {
    private final SceneManager sceneManager;
    private final HashMap<String, String> courseData;

    private final GridPane gridPane;
    
    public AddCourseScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.courseData = new HashMap<>();
        gridPane = new GridPane();
    }
    
    public Scene getScene() {
        Label headerLabel = new Label("Add Course");

        Label schoolIdLabel = new Label("School ID:");
        TextField schoolIdTextField = new TextField();

        Label termIndexLabel = new Label("Term Index:");
        TextField termIndexTextField = new TextField();

        Label courseNameLabel = new Label("Name:");
        TextField courseNameTextField = new TextField();

        Label courseIDLabel = new Label("Course ID:");
        TextField courseIDTextField = new TextField();

        Label courseCodeLabel = new Label("Course code:");
        TextField courseCodeTextField = new TextField();

        Label instructorLabel = new Label("Instructor:");
        TextField instructorTextField = new TextField();

        Label courseCreditLabel = new Label("ID:");
        TextField courseCreditTextField = new TextField();

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

        gridPane.add(termIndexLabel, 0, 1);
        gridPane.add(termIndexTextField, 1, 1);

        gridPane.add(courseNameLabel, 0, 2);
        gridPane.add(courseNameTextField, 1, 2);

        gridPane.add(courseIDLabel, 0, 3);
        gridPane.add(courseIDTextField, 1, 3);

        gridPane.add(courseCodeLabel, 0, 4);
        gridPane.add(courseCodeTextField, 1, 4);

        gridPane.add(instructorLabel, 0, 5);
        gridPane.add(instructorTextField, 1, 5);

        gridPane.add(courseCreditLabel, 0, 6);
        gridPane.add(courseCreditTextField, 1, 6);

        gridPane.add(evalCountLabel, 0, 7);
        gridPane.add(evalCountTextField, 1, 7);

        gridPane.add(weightsLabel, 0, 8);
        gridPane.add(weightsTextField, 1, 8);

        gridPane.add(namesLabel, 0, 9);
        gridPane.add(namesTextField, 1, 9);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            courseData.put("schoolId", schoolIdTextField.getText());
            courseData.put("termIndex", termIndexTextField.getText());
            courseData.put("courseName", courseNameTextField.getText());
            courseData.put("courseID", courseIDTextField.getText());
            courseData.put("courseCode", courseCodeTextField.getText());
            courseData.put("instructor", instructorTextField.getText());
            courseData.put("credit", courseCreditTextField.getText());
            courseData.put("evalCount", evalCountTextField.getText());
            courseData.put("evalWeights", weightsTextField.getText());
            courseData.put("evalNames", namesTextField.getText());
            try {
                CloudConnect.callFunction("add-course", courseData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sceneManager.setScene(new Confirmation(sceneManager, "Course created successfully!").getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            sceneManager.showHomeScene();
        });
        
        HBox hBox = new HBox(backButton, submitButton);
        gridPane.add(hBox, 1, 10);
        hBox.setSpacing(10);


        Scene scene = new Scene(gridPane, 1200, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    public HashMap<String, String> getCourseData() {
        return courseData;
    }
}