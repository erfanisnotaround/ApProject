// src/main/java/org/example/phaze2/model/winAndPocketLossModel/PocketLossCondition.java
package org.example.phaze2.model.winAndPocketLossModel;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;

public class PocketLossCondition implements GameCondition {
    private final GameDataProvider data;
    private int totalMaxHp = -1;                 // computed once
    private int lostSum = 0;// sum of MaxHp of pockets counted as dead

    private int injected = 0;
    public PocketLossCondition(GameDataProvider data) {
        this.data = data;

        List<PocketMain> seeds = data.getAllPockets();
        totalMaxHp = seeds.stream().mapToInt(PocketMain::getMaxHp).sum();

    }

    @Override public boolean check() {
        List<PocketMain> pockets = data.getAllPockets();
        lostSum = 0;

        for (PocketMain p : pockets) {
            if (p.getHP() <= 0) {

                lostSum += p.getMaxHp();

            }
        }
        lostSum += injected;
        return totalMaxHp > 0 && lostSum * 2 >= totalMaxHp;
    }

    @Override public String reason() {
        return "PocketLoss ≥ 50% of total MaxHP (" + lostSum + "/" + totalMaxHp + ")";
    }

    @Override
    public int[] statusAtFiring() {
        int[] status = new int[2];
        status[0] = lostSum;
        status[1] = totalMaxHp;
        return status;
    }

    @Override
    public void inject(int injection) {
        injected += injection;
    }

    @Override
    public void reset() {
        lostSum = 0;

        injected = 0;
    }
}
