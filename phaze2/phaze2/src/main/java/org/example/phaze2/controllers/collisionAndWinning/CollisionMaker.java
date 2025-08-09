package org.example.phaze2.controllers.collisionAndWinning;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class CollisionMaker extends Thread {
    private static final long WAIT_MS = 1;
    private final CollisionController controller;
    private volatile boolean running = true;
    private final GameState gameState;
    public CollisionMaker(List<PocketMain> pockets , GameState gameState) {
        super("CollisionThread");
        setDaemon(false);
        setUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        this.gameState = gameState;
        controller = new CollisionController(pockets , gameState);
    }

    @Override public void run() {
        try {
            while (running && !isInterrupted()) {
                controller.Collision();
                Thread.sleep(WAIT_MS);
            }
        } catch (Throwable t) {             // catches Exception *and* Error
            t.printStackTrace();
        }
    }

    public void shutdown() { running = false; interrupt(); }
}
