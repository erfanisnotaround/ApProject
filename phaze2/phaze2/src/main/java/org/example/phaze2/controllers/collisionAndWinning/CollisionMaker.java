package org.example.phaze2.controllers.collisionAndWinning;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class CollisionMaker {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> taskHandle;
    private final AtomicBoolean paused = new AtomicBoolean(false);
    private CollisionController collisionController = new CollisionController();

    private final long lengthOfCheck = 10;
    public void Start() {
        taskHandle = scheduler.scheduleAtFixedRate( () -> {
            if (paused.get()) return;
            collisionController.Collision();
        } , 0 , lengthOfCheck , TimeUnit.MILLISECONDS );
    }

    public void pause() {
        paused.set(true);
    }

    public void resume() {
        paused.set(false);
    }

    public void stop() {
        if (taskHandle != null) taskHandle.cancel(false);
        scheduler.shutdown();
    }

}
