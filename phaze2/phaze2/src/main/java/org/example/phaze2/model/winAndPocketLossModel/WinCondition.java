package org.example.phaze2.model.winAndPocketLossModel;

import javafx.geometry.Point2D;

public class WinCondition implements GameCondition {
    private final GameDataProvider data;
    public WinCondition(GameDataProvider data) { this.data = data; }

    @Override public boolean check() {
        int started   = data.getStartedCount();
        int delivered = data.getDeliveredCount();
        return started > 0 && delivered * 2 >= started;
    }

    @Override public String reason() {
        return "Delivered ≥ 50% of started (" +
                data.getDeliveredCount() + "/" + data.getStartedCount() + ")";
    }

    @Override
    public int[] statusAtFiring() {
        int[] status = new int[2];
        status[0] = data.getDeliveredCount();
        status[1] = data.getStartedCount();
        return status;
    }

    @Override
    public void reset() {

    }
}
