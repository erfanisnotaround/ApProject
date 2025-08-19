package org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces;

public interface MovementService {
    void start(double speedMultiplier, double availableTime);
    void reset();
}

