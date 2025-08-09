package org.example.phaze2.model.winAndPocketLossModel;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;

public interface GameDataProvider {
    List<PocketMain> getAllPockets();   // live list from GameState
    int getStartedCount();
    int getDeliveredCount();
}
