package org.example.phaze2.model.sceneModel;

import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.sceneModel.dataPassers.GameOverInfoRequirements;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

public class GameOverModel {
    private GameOverInfoRequirements gameOverInfoRequirements;
    private final int PocketLoss;
    private final int WHolePockets;
    private final GameOverType gameOverType;
    private final SceneManager sceneManager;
    public GameOverModel (GameOverInfoRequirements gameOverInfoRequirements , SceneManager sceneManager) {
        this.gameOverInfoRequirements = gameOverInfoRequirements;
        this.PocketLoss = gameOverInfoRequirements.getPocketLoss();
        this.WHolePockets = gameOverInfoRequirements.getWHolePockets();
        this.gameOverType = gameOverInfoRequirements.getGameOverType();
        this.sceneManager = sceneManager;
    }

    public void menuButtonOnAction() {
        sceneManager.switchScreen(PositionStatus.MENU);
    }
    public int getPocketLoss() {
        return PocketLoss;
    }

    public int getWHolePockets() {
        return WHolePockets;
    }

    public GameOverType getGameOverType() {
        return gameOverType;
    }

    public GameOverInfoRequirements getGameOverInfoRequirements() {
        return gameOverInfoRequirements;
    }

    public void setGameOverInfoRequirements(GameOverInfoRequirements gameOverInfoRequirements) {
        this.gameOverInfoRequirements = gameOverInfoRequirements;
    }
}
