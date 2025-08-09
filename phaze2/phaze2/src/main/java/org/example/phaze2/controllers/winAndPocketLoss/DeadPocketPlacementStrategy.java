package org.example.phaze2.controllers.winAndPocketLoss;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface DeadPocketPlacementStrategy {
    void place(PocketMain pocket);
}
