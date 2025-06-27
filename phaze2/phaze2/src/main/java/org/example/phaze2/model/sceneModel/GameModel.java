package org.example.phaze2.model.sceneModel;

import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class GameModel {
    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    public void MenuButtonClicked() {
        sceneManager.switchScreen(PositionStatus.MENU);
    }
    public void StartButtonClicked() {

    }
}
