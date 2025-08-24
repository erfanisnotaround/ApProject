package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.application.Platform;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.hudModels.BasicTransfer;
import org.example.phaze2.model.sceneModel.dataPassers.GameOverInfoRequirements;
import org.example.phaze2.model.sceneModel.dataPassers.GoingToGamaInformation;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

public class FxSceneGameOverHandler implements GameOverHandler {
    private final SceneManager sceneManager;
    private final AppContext appContext;
    private final GameState gameState;
    private final GoingToGamaInformation goingToGamaInformation;

    public FxSceneGameOverHandler(SceneManager sceneManager , GameState gameState
            , AppContext appContext , GoingToGamaInformation goingToGamaInformation) {
        this.sceneManager = sceneManager;
        this.appContext = appContext;
        this.gameState = gameState;
        this.goingToGamaInformation = goingToGamaInformation;
    }

    @Override public void onGameOver(GameOverType type, String reason) {

        GameOverInfoRequirements infoRequirements = requirements(gameState.getHudStuffDAta().getBasicTransfer() , type , goingToGamaInformation);
        Platform.runLater(() -> {
            appContext.getMusic().stop();
            sceneManager.switchScreen(PositionStatus.AFTER_GAME , infoRequirements);
        });
    }
    private GameOverInfoRequirements requirements(BasicTransfer basicTransfer , GameOverType gameOverType
            , GoingToGamaInformation goingToGamaInformation) {
        int PocketLost = basicTransfer.FirstFactor(GameOverType.POCKET_LOSS);
        int WholePockets = basicTransfer.SecondFactor(GameOverType.POCKET_LOSS);

        return new GameOverInfoRequirements(PocketLost , WholePockets , gameOverType , goingToGamaInformation , appContext);
    }
}
