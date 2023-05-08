package dsis.admin;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private final Stage primaryStage;
    private Scene homeScene;
    private Scene signUpScene;
    private Scene addTermScene;
    private Scene addCourseScene;
    
    private Scene setEvalScene;
    
    private Scene confirmationScene;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createScenes();
    }

    private void createScenes() {
        HomeScene homeScene = new HomeScene(this);
        SignUpScene signUpScene = new SignUpScene(this);
        Confirmation confirmationScene = new Confirmation(this, "Please Confirm..");
        AddTermScene addTermScene = new AddTermScene(this);
        AddCourseScene addCourseScene = new AddCourseScene(this);
        SetEvaluationGrade setEvaluationGrade = new SetEvaluationGrade(this);

        this.homeScene = homeScene.getScene();
        this.confirmationScene = confirmationScene.getScene();
        this.signUpScene = signUpScene.getScene();
        this.addTermScene = addTermScene.getScene();
        this.addCourseScene = addCourseScene.getScene();
        this.setEvalScene = setEvaluationGrade.getScene();
    }

    public void showHomeScene() {
        primaryStage.setScene(homeScene);
    }

    public void showSignUpScene() {
        primaryStage.setScene(signUpScene);
    }

    public void showAddTermScene() {
        primaryStage.setScene(addTermScene);
    }

    public void showAddCourseScene() {
        primaryStage.setScene(addCourseScene);
    }

    public void showSetEvalGradeScene() {
        primaryStage.setScene(setEvalScene);
    }

    public void showConfirmationScene() {
        primaryStage.setScene(confirmationScene);
    }

    public void setScene(Scene scene) {
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}