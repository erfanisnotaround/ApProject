package org.example.phaze2.controllers.moverController;

import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface MovingPlanMaker {

    void movingStrategy(PocketMain pocketMain , Curve curve);
    void StopStrategy(Pocket LastPocket , PocketMain pocketMain);
}
