package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.WinLossService;
import org.example.phaze2.controllers.winAndPocketLoss.BackgroundConditionScheduler;
import org.example.phaze2.controllers.winAndPocketLoss.GameOverEvaluator;

public final class WinLossServiceImpl implements WinLossService {
    private final BackgroundConditionScheduler scheduler;
    private final GameOverEvaluator evaluator;
    public WinLossServiceImpl(BackgroundConditionScheduler s, GameOverEvaluator e) {
        this.scheduler = s; this.evaluator = e;
    }
    @Override public void start() { /* scheduler already started elsewhere; noop or ensure running */ }
    @Override public void reset() { scheduler.reset(); evaluator.reset(); }
    @Override public void stop()  { scheduler.stop(); }
}