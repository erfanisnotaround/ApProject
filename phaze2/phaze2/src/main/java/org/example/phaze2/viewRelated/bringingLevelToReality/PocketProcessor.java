package org.example.phaze2.viewRelated.bringingLevelToReality;

import org.example.phaze2.model.jsonRefrencesAndLOadings.PocketLoading;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;

public class PocketProcessor implements Runnable {
    private List<PocketMain> pockets = new ArrayList<>();
    private List<PocketLoading> pocketLoadings;

    public PocketProcessor(List<PocketLoading> pocketLoadings) {
        this.pocketLoadings = pocketLoadings;
    }
    @Override
    public void run() {
        for (PocketLoading pocketLoading : pocketLoadings) {
            pockets.add(processPocket(pocketLoading));
        }
    }
    private PocketMain processPocket(PocketLoading pocketLoading) {
        PocketMain pocket = new PocketMain(pocketLoading.getType());
        pocket.setDelay(pocketLoading.getDelay());


        pocket.setFirstPocketType(pocket.getType());
        pocket.setTypeBeforeChange(pocketLoading.getType());
        return pocket;
    }

    public List<PocketMain> getPockets() {
        return pockets;
    }
}
