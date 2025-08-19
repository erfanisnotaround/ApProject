package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import org.example.phaze2.controllers.moverController.moveRelated.MovementListenerRegistry;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.MovementService;

public final class WholeMovementService implements MovementService {
    private final WholeMovement movement;
    public WholeMovementService(WholeMovement movement) { this.movement = movement; }
    @Override public void start(double speed, double available) { movement.StartSending(speed, available); }



    @Override public void reset() { }
}