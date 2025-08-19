package org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces;

import org.example.phaze2.controllers.moverController.moveRelated.MovementListenerRegistry;

public interface MovementService {
    void start(double speedMultiplier, double availableTime);
    void reset();
}

