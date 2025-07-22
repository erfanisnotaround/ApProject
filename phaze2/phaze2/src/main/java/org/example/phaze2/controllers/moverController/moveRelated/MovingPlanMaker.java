package org.example.phaze2.controllers.moverController.moveRelated;

import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface MovingPlanMaker {

    void movingStrategy(PocketMain pocketMain , Curve curve);
    void StopStrategy(Pocket LastPocket , PocketMain pocketMain);
}
