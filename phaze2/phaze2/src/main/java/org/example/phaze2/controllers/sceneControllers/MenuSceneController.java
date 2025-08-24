package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.controllersInterfaces.Maker;
import org.example.phaze2.model.sceneModel.MenuModel;

public class MenuSceneController implements Maker, ControlledScreen {
    private SceneManager sceneManager;
    private MenuModel menuModel = new MenuModel();

    @FXML
    private Button StartButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Button SettingsButton;
    @FXML
    private Button LevelsButton;


    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        menuModel.setSceneManager(sceneManager);
    }

    @Override
    public void MakeFirst() {
        StartButton.setOnAction(event -> {
            startButtonClicked();
        });
        LevelsButton.setOnAction(event -> {
            levelsButtonClicked();
        });
        SettingsButton.setOnAction(event -> {
            settingsButtonClicked();
        });
        ExitButton.setOnAction(event -> {
            exitButtonClicked();
        });
    }

    @Override
    public void PassContext(AppContext appContext) {
        menuModel.setAppContext(appContext);
    }

    void startButtonClicked() {
        menuModel.startButtonClicked();
    }
    void levelsButtonClicked() {
        menuModel.levelsButtonClicked();
    }
    void settingsButtonClicked() {
        menuModel.settingsButtonClicked();
    }
    void exitButtonClicked() {
        menuModel.exitButtonClicked(Constants.getInstance().getPrimaryStage());
    }

}
