package org.example.phaze2.model.sceneModel;

import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.sceneModel.dataPassers.GoingToGamaInformation;

public class LevelsModel {

    private SceneManager sceneManager;
    private AppContext appContext;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setAppContext(AppContext appContext) {
        this.appContext = appContext;
    }

    public void BackButtonOnAction() {
        sceneManager.switchScreen(PositionStatus.MENU);
    }


    public void onLevelSelected(int level) {

        sceneManager.switchScreen(PositionStatus.GAME , new GoingToGamaInformation(level , appContext.getLevels()));
    }
}
