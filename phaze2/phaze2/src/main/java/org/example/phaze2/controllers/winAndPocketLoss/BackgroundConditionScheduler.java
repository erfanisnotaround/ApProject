package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundConditionScheduler {
    private final ScheduledExecutorService ses;
    private final PocketReaper reaper;
    private final GameOverEvaluator evaluator;
    private final GameOverHandler handler;

    public BackgroundConditionScheduler(PocketReaper reaper,
                                        GameOverEvaluator evaluator,
                                        GameOverHandler handler) {
        this.reaper = reaper;
        this.evaluator = evaluator;
        this.handler = handler;
        this.ses = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "ConditionScheduler");
            t.setDaemon(true);
            return t;
        });

    }

    public void start() {
        ses.scheduleAtFixedRate(() -> {

            var dead = reaper.detectDead();
            if (!dead.isEmpty()) {
                reaper.applyOnFx(dead);
            }
            var type = evaluator.evaluateOnce();

            if (type != null) {
                String reason = evaluator.lastReason();
                Platform.runLater(() -> handler.onGameOver(type, reason));
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void stop() { ses.shutdownNow(); }
    public void reset(){
        reaper.reset();
        evaluator.reset();
    }
}
