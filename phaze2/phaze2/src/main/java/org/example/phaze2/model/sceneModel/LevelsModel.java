package org.example.phaze2.model.sceneModel;

import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class LevelsModel {
    private SceneManager sceneManager;
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    public void BackButtonOnAction() {
        sceneManager.switchScreen(PositionStatus.MENU);
    }
}
