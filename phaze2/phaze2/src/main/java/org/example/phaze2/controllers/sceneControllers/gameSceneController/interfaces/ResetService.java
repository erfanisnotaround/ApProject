package org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces;

import org.example.phaze2.controllers.moverController.moveRelated.MovementListenerRegistry;

public interface ResetService {
    void fullResetToSeeds();
    void resetMovementListeners(MovementListenerRegistry registry);
    void resetConnectionsAndSystems();
}
