package org.example.phaze2.controllers.winAndPocketLoss;

import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

public interface GameOverHandler {
    void onGameOver(GameOverType type, String reason);
}
