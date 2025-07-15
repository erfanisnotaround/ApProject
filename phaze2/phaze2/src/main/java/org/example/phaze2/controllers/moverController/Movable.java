package org.example.phaze2.controllers.moverController;

import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface Movable {
    void move(Curve curve, double speed, double acceleration , PocketMain pocket);
}
