package org.example.phaze2.model.sceneModel;

import javafx.stage.Stage;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.sceneModel.dataPassers.GoingToGamaInformation;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

import java.util.List;

public class MenuModel {
    private SceneManager sceneManager;
    private List<Level> levels;
    private AppContext appContext;

    public void startButtonClicked() {
        GoingToGamaInformation information = new GoingToGamaInformation(-1 , levels);
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


    public void setAppContext(AppContext appContext) {
        this.appContext = appContext;
        this.levels = appContext.getLevels();
    }
}
