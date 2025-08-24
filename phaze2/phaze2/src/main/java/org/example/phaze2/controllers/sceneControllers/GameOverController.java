package org.example.phaze2.controllers.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.sceneModel.GameOverModel;
import org.example.phaze2.model.sceneModel.dataPassers.GameOverInfoRequirements;
import org.example.phaze2.model.sceneModel.dataPassers.GoingToGamaInformation;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.controllersInterfaces.Maker;

public class GameOverController implements Maker, ControlledScreen, DataReceivingController<GameOverInfoRequirements> {

    GameOverModel gameOverModel;
    private SceneManager sceneManager;

    @FXML
    private Button menuButton;
    @FXML
    private Label PocketLoss,WholePockets;
    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void initData(GameOverInfoRequirements data) {
        gameOverModel = new GameOverModel(data , sceneManager);
    }

    @Override
    public void MakeFirst() {
        PocketLoss.setText("Pocket Loss : " + gameOverModel.getPocketLoss());
        WholePockets.setText("Pockets : " + gameOverModel.getWHolePockets());

        menuButton.setOnAction(event -> {
            menuButtonOnAction();
        });
    }
    private void menuButtonOnAction() {
        gameOverModel.menuButtonOnAction();
    }

    @Override
    public void PassContext(AppContext appContext) {

    }
}
