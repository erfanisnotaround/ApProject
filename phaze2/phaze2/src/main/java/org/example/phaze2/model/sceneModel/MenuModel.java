package org.example.phaze2.model.sceneModel;

import javafx.stage.Stage;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class MenuModel {
    private SceneManager sceneManager;

    public void startButtonClicked() {
        GoingToGamaInformation information = new GoingToGamaInformation(-1);
        sceneManager.switchScreen(PositionStatus.GAME , information);
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
