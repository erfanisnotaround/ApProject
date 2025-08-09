package org.example.phaze2.model.winAndPocketLossModel;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultGameDataProvider implements GameDataProvider {
    private final GameState gameState;
    private final AtomicInteger started = new AtomicInteger();
    private final AtomicInteger delivered = new AtomicInteger();

    public DefaultGameDataProvider(GameState gameState) {
        this.gameState = gameState;
    }

    @Override public List<PocketMain> getAllPockets() {
        return gameState.getResources().getPockets();
    }
    @Override public int getStartedCount()     { return started.get(); }
    @Override public int getDeliveredCount()   { return delivered.get(); }


    public void incStarted()   { started.incrementAndGet(); }
    public void incDelivered() { delivered.incrementAndGet(); }
}
