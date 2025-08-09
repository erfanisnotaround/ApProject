// src/main/java/org/example/phaze2/model/winAndPocketLossModel/PocketLossCondition.java
package org.example.phaze2.model.winAndPocketLossModel;

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
        if (totalMaxHp < 0) {
            totalMaxHp = pockets.stream().mapToInt(PocketMain::getMaxHp).sum();
        }

        for (PocketMain p : pockets) {
                System.out.println(p.getHP() + " " + p.getMaxHp() + " " + p.getType());
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
    public void reset() {
        
    }
}
