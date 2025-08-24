package org.example.phaze2.model.sceneModel.dataPassers;

import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;
import org.example.phaze2.viewRelated.hudView.MakeHUD;

public class GameOverInfoRequirements {
    private final int PocketLoss;
    private final int WHolePockets;
    private final GameOverType gameOverType;
    private final GoingToGamaInformation goingToGamaInformation;
    private final AppContext appContext;

    public GameOverInfoRequirements(int PocketLoss , int WHolePockets , GameOverType gameOverType, GoingToGamaInformation goingToGamaInformation , AppContext appContext) {
        this.PocketLoss = PocketLoss;
        this.WHolePockets = WHolePockets;
        this.gameOverType = gameOverType;
        this.goingToGamaInformation = goingToGamaInformation;
        this.appContext = appContext;

        if (gameOverType.equals(GameOverType.WIN)) makeTHisLevelPassed();
    }
    public void makeTHisLevelPassed() {
        goingToGamaInformation.makeCurrentLevelPassed();
        appContext.writeItAgain();
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
    public GoingToGamaInformation getGoingToGamaInformation() {
        return goingToGamaInformation;
    }

    public AppContext getAppContext() {
        return appContext;
    }
}
