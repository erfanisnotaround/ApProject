package org.example.phaze2.model.constants;

import org.example.phaze2.controllers.winAndPocketLoss.*;
import org.example.phaze2.model.winAndPocketLossModel.DefaultGameDataProvider;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;
import org.example.phaze2.model.winAndPocketLossModel.PocketLossCondition;
import org.example.phaze2.model.winAndPocketLossModel.WinCondition;

public final class PocketWinAndLoss {
    private DeadPocketPlacementStrategy lossBin;

    private PocketReaper reaper;

    private DefaultGameDataProvider dataProvider;
    private GameOverEvaluator evaluator;
    private BackgroundConditionScheduler bgScheduler;

    public DeadPocketPlacementStrategy getLossBin() {
        return lossBin;
    }

    public void setLossBin(DeadPocketPlacementStrategy lossBin) {
        this.lossBin = lossBin;
    }

    public PocketReaper getReaper() {
        return reaper;
    }

    public void setReaper(PocketReaper reaper) {
        this.reaper = reaper;
    }

    public DefaultGameDataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DefaultGameDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public GameOverEvaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(GameOverEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public BackgroundConditionScheduler getBgScheduler() {
        return bgScheduler;
    }

    public void setBgScheduler(BackgroundConditionScheduler bgScheduler) {
        this.bgScheduler = bgScheduler;
    }
}
