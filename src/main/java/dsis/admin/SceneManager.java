package dsis.admin;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private final Stage primaryStage;
    private Scene homeScene;
    private Scene signUpScene;
    private Scene editScene;
    private Scene addScene;
    
    private Scene confirmationScene;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createScenes();
    }

    private void createScenes() {
        HomeScene homeScene = new HomeScene(this);
        SignUpScene signUpScene = new SignUpScene(this);
        Confirmation confirmationScene = new Confirmation(this, "Please Confirm..");
        EditScene editScene = new EditScene(this);
        AddScene addScene = new AddScene(this);

        this.homeScene = homeScene.getScene();
        this.confirmationScene = confirmationScene.getScene();
        this.signUpScene = signUpScene.getScene();
        this.editScene = editScene.getScene();
        this.addScene = addScene.getScene();
    }

    public void showHomeScene() {
        primaryStage.setScene(homeScene);
    }

    public void showSignUpScene() {
        primaryStage.setScene(signUpScene);
    }

    public void showEditScene() {
        primaryStage.setScene(editScene);
    }

    public void showAddScene() {
        primaryStage.setScene(addScene);
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