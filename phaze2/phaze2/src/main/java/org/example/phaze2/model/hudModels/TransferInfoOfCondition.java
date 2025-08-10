package org.example.phaze2.model.hudModels;

import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

public interface TransferInfoOfCondition {
    int FirstFactor(GameOverType type);
    int SecondFactor(GameOverType type);
}
