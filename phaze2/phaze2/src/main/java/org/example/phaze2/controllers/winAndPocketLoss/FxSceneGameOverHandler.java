package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.application.Platform;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

public class FxSceneGameOverHandler implements GameOverHandler {
    private final SceneManager sceneManager;

    public FxSceneGameOverHandler(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override public void onGameOver(GameOverType type, String reason) {
        System.out.println("GameOver: " + type + " — " + reason);
//        Platform.runLater(() -> sceneManager.switchScreen(PositionStatus.AFTER_GAME));
    }
}
