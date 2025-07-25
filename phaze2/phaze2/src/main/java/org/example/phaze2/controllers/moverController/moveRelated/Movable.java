package org.example.phaze2.controllers.moverController.moveRelated;

import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface Movable {
    void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket);
}
