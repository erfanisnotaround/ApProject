package org.example.phaze2.model.winAndPocketLossModel;

public class WinCondition implements GameCondition {
    private final GameDataProvider data;
    public WinCondition(GameDataProvider data) { this.data = data; }

    @Override public boolean check() {
        int started   = data.getStartedCount();
        int delivered = data.getDeliveredCount();
        return started > 0 && delivered * 2 >= started;  // delivered ≥ 50% of started
    }

    @Override public String reason() {
        return "Delivered ≥ 50% of started (" +
                data.getDeliveredCount() + "/" + data.getStartedCount() + ")";
    }

    @Override
    public void reset() {

    }
}
