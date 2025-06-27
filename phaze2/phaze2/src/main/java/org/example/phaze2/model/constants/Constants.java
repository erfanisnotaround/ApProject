package org.example.phaze2.model.constants;

import javafx.stage.Stage;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class Constants {
    private static final Constants INSTANCE = new Constants();

    private  Stage primaryStage;
    private SceneManager sceneManager;
    private Constants() {}
    public static Constants getInstance() {
        return INSTANCE;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
