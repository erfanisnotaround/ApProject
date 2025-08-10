// src/main/java/org/example/phaze2/model/winAndPocketLossModel/PocketLossCondition.java
package org.example.phaze2.model.winAndPocketLossModel;

import javafx.geometry.Point2D;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class PocketLossCondition implements GameCondition {
    private final GameDataProvider data;
    private int totalMaxHp = -1;                 // computed once
    private int lostSum = 0;                     // sum of MaxHp of pockets counted as dead

    public PocketLossCondition(GameDataProvider data) { this.data = data;}

    @Override public boolean check() {
        List<PocketMain> pockets = data.getAllPockets();
        lostSum = 0;
        if (totalMaxHp < 0) {
            totalMaxHp = pockets.stream().mapToInt(PocketMain::getMaxHp).sum();
        }

        for (PocketMain p : pockets) {
            if (p.getHP() <= 0) {

                lostSum += p.getMaxHp();

            }
        }
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
    public void reset() {
        lostSum = 0;
        totalMaxHp = -1;
    }
}
