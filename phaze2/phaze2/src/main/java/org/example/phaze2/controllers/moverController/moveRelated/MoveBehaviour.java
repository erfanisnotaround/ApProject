package org.example.phaze2.controllers.moverController.moveRelated;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface MoveBehaviour {
    /** called once when this behaviour becomes active */
    void start(PocketMain pocket, PathMover mover);

    /** called once per JavaFX frame (PathMover.handle()) */
    void tick(double dt);

    /** called once when the behaviour is detached / pocket stops */
    void stop();
}
