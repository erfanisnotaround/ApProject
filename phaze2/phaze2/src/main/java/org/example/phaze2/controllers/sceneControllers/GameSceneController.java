package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.sceneModel.GameModel;
import org.example.phaze2.model.controllersInterfaces.Initializer;

public class GameSceneController implements Initializer, ControlledScreen  {
    private SceneManager sceneManager;
    private GameModel gameModel = new GameModel();


    @FXML
    private Button MenuButton;
    @FXML
    private Button StartButton;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameModel.setSceneManager(sceneManager);
    }


    @Override
    public void initialize() {
        MenuButton.setOnAction(event -> {
            menuButtonClicked();
        });
        StartButton.setOnAction(event -> {
            startButtonClicked();
        });
    }
    void menuButtonClicked() {
        gameModel.MenuButtonClicked();
    }
    void startButtonClicked() {
        gameModel.StartButtonClicked();
    }
}
