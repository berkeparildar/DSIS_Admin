package dsis.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashMap;

public class SetEvaluationGrade {

    private SceneManager sceneManager;
    private HashMap<String, String> termData;

    public SetEvaluationGrade(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        termData = new HashMap<>();
    }

    public Scene getScene() {
        Label headingLabel = new Label("Set a course's evaluation grade");
        headingLabel.setFont(Font.font("Arial", 20));

        Label schoolIdLabel = new Label("School ID:");
        TextField schoolIdTextField = new TextField();
        Label termIndexLabel = new Label("Term Index:");
        TextField termIndexTextField = new TextField();
        Label courseIDLabel = new Label("Course ID:");
        TextField courseIDTextField = new TextField();
        Label evaluationIndexLabel = new Label("Evaluation Index:");
        TextField evaluationIndexTextField = new TextField();
        Label evaluationGradeLabel = new Label("Grade:");
        TextField evaluationGradeTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            termData.put("studentId", schoolIdTextField.getText());
            termData.put("termIndex", termIndexTextField.getText());
            termData.put("courseID", courseIDTextField.getText());
            termData.put("evalIndex", evaluationIndexTextField.getText());
            termData.put("evalGrade", evaluationGradeTextField.getText());
            try {
                System.out.println(CloudConnect.callFunction("course-eval-grade", termData));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sceneManager.setScene(new Confirmation(sceneManager, "Set the course grade!").getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> sceneManager.showHomeScene());

        VBox layout = new VBox(20, headingLabel, schoolIdLabel, schoolIdTextField, termIndexLabel, termIndexTextField,
                courseIDLabel, courseIDTextField, evaluationIndexLabel, evaluationIndexTextField,
                evaluationGradeLabel, evaluationGradeTextField, submitButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 1200, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

}
