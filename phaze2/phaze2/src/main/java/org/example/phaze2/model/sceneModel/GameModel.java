package org.example.phaze2.model.sceneModel;

import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class GameModel {
    private SceneManager sceneManager;
    private GoingToGamaInformation levelInformation;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    public void MenuButtonClicked() {
        sceneManager.switchScreen(PositionStatus.MENU);
    }
    public void StartButtonClicked() {

    }

    public GoingToGamaInformation getLevelInformation() {
        return levelInformation;
    }

    public void setLevelInformation(GoingToGamaInformation levelInformation) {
        this.levelInformation = levelInformation;
    }
}
