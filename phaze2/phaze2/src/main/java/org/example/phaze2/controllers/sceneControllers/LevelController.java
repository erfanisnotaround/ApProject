package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.Maker;
import org.example.phaze2.model.sceneModel.LevelsModel;

public class LevelController implements Maker, ControlledScreen {
    private SceneManager sceneManager;
    private LevelsModel levelsModel = new LevelsModel();
    @FXML
    private Button BackButton;
    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        levelsModel.setSceneManager(sceneManager);


    }

    @Override
    public void MakeFirst() {
        BackButton.setOnAction((event) -> {
            BackButtonOnAction();
        });
    }

    @Override
    public void PassContext(AppContext appContext) {

    }

    void BackButtonOnAction() {
        levelsModel.BackButtonOnAction();
    }
}
