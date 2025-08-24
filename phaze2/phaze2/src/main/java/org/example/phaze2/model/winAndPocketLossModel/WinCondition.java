package org.example.phaze2.model.winAndPocketLossModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;

public class WinCondition implements GameCondition {
    private final GameDataProvider data;
    public WinCondition(GameDataProvider data) {
        this.data = data;
        data.incStarted(WholeHp(data.getAllPockets()));

    }
    private int WholeHp(List<PocketMain> pocketMains){
        int n = 0;
        for (PocketMain p : pocketMains){
            n += p.getMaxHp();
        }
        return n;
    }

    @Override public boolean check() {
        data.resetStarted();
        data.incStarted(WholeHp(data.getAllPockets()));
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
    public void inject(int injection) {
        data.incDelivered(injection);
    }

    @Override
    public void reset() {
        data.reset();
    }
}
