package dsis.admin;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        SceneManager sceneManager = new SceneManager(primaryStage);
        HomeScene homeScene = new HomeScene(sceneManager);
        primaryStage.setScene(homeScene.getScene());
        primaryStage.setTitle("DSIS Admin Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}