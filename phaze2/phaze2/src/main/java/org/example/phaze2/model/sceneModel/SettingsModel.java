package org.example.phaze2.model.sceneModel;

import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.settingModel.FileSettingsRepository;

public class SettingsModel {
    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager ) {
        this.sceneManager = sceneManager;

    }
    public void BackButtonClicked() {

        sceneManager.switchScreen(PositionStatus.MENU);
    }
}
