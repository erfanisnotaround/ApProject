package org.example.phaze2;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PositionStatus;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("BLUE PRINT HELL");
//        primaryStage.setMaximized(true);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.switchScreen(PositionStatus.MENU);


        primaryStage.show();

        Constants.getInstance().setPrimaryStage(primaryStage);
        Constants.getInstance().setSceneManager(sceneManager);
        
    }
}
