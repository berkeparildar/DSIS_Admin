package dsis.admin;

import javafx.event.Event;
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
        Button signUpButton = new Button("Sign Up");
        Button addTermButton = new Button("Add Term");
        Button addCourseButton = new Button("Add Course");
        Button addSetEvalGradeButton = new Button("Set Evaluation Grade");
        

        signUpButton.setOnAction(event -> sceneManager.showSignUpScene());
        addTermButton.setOnAction(event -> sceneManager.showAddTermScene());
        addCourseButton.setOnAction(event -> sceneManager.showAddCourseScene());
        addSetEvalGradeButton.setOnAction(event -> sceneManager.showSetEvalGradeScene());

        VBox layout = new VBox(20, signUpButton, addTermButton, addCourseButton, addSetEvalGradeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 1200, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}