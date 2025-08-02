package org.example.phaze2.model.hudModels;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberOfPocketLossManager {
    private AtomicInteger PocketLoss = new AtomicInteger(0);

    public void incrementPocketLoss() {
        PocketLoss.incrementAndGet();
    }
    public void decrementPocketLoss() {
        PocketLoss.decrementAndGet();
    }
    public int getPocketLoss() {
        return PocketLoss.get();
    }
    public void setPocketLoss(int pocketLoss) {
        PocketLoss.set(pocketLoss);
    }
}
