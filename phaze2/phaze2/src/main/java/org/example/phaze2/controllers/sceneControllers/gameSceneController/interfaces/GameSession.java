package org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces;

public interface GameSession {

    void startFresh(double speedMultiplier, double availableTime);

    void startFromCurrent(double speedMultiplier, double availableTime);
    void reset();
    void stop();
}