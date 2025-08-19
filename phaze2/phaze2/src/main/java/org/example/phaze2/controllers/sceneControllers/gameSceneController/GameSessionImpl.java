package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.GameSession;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.MovementService;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.ResetService;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.WinLossService;

import org.example.phaze2.controllers.winAndPocketLoss.BackgroundConditionScheduler;
import org.example.phaze2.controllers.winAndPocketLoss.GameOverEvaluator;

public final class GameSessionImpl implements GameSession {
    private final MovementService movement;
    private final ResetService resetter;
    private final BackgroundConditionScheduler scheduler;
    private final GameOverEvaluator evaluator;

    public GameSessionImpl(MovementService movement,
                           ResetService resetter,
                           BackgroundConditionScheduler scheduler,
                           GameOverEvaluator evaluator) {
        this.movement  = movement;
        this.resetter  = resetter;
        this.scheduler = scheduler;
        this.evaluator = evaluator;
    }

    @Override public void startFresh(double speed, double time) {
        scheduler.reset();        // you had this before every start
        evaluator.reset();
        resetter.fullResetToSeeds();           // full clean slate
        movement.start(speed, time);
    }

    @Override public void startFromCurrent(double speed, double time) {
        scheduler.reset();        // your StartTempo did this
        evaluator.reset();
        resetter.fullResetToSeeds();
        movement.start(speed, time);           // NO rebuild here
    }

    @Override public void reset() {
        scheduler.reset();
        evaluator.reset();
        resetter.fullResetToSeeds();
    }

    @Override public void stop() {
        scheduler.stop();
    }
}