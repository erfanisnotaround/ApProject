package org.example.phaze2.model.hudModels;

import org.example.phaze2.controllers.winAndPocketLoss.GameOverEvaluator;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

public class BasicTransfer implements TransferInfoOfCondition {

    private GameState gameState;
    private GameOverEvaluator gameOverEvaluator;

    public BasicTransfer(GameState gameState){
        this.gameState = gameState;
        this.gameOverEvaluator = gameState.getPocketWinAndLoss().getEvaluator();
    }


    @Override
    public int FirstFactor(GameOverType type) {
        if (gameOverEvaluator == null)return 0;
        return gameOverEvaluator.getCondition(type).statusAtFiring()[0];
    }

    @Override
    public int SecondFactor(GameOverType type) {
        if (gameOverEvaluator == null)return 0;

        return gameOverEvaluator.getCondition(type).statusAtFiring()[1];

    }
}
