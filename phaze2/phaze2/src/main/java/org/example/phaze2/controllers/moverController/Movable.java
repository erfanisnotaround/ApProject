package org.example.phaze2.controllers.moverController;

import org.example.phaze2.model.levelDetails.Curve;

public interface Movable {
    void move(Curve curve, double speed, double acceleration);
}
