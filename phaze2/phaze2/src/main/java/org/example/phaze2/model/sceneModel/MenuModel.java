package org.example.phaze2.model.sceneModel;

import javafx.stage.Stage;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class MenuModel {
    private SceneManager sceneManager;

    public void startButtonClicked() {
        sceneManager.switchScreen(PositionStatus.GAME);
    }
    public void levelsButtonClicked() {
        sceneManager.switchScreen(PositionStatus.LEVELS);
    }
    public void settingsButtonClicked() {
        sceneManager.switchScreen(PositionStatus.SETTINGS);
    }
    public void exitButtonClicked(Stage stage) {
        stage.close();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
