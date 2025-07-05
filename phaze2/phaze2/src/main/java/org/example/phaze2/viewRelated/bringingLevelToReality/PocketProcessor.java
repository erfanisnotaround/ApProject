package org.example.phaze2.viewRelated.bringingLevelToReality;

import org.example.phaze2.model.jsonRefrencesAndLOadings.PocketLoading;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;

import java.util.ArrayList;
import java.util.List;

public class PocketProcessor implements Runnable {
    private List<Pocket> pockets = new ArrayList<>();
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
    private Pocket processPocket(PocketLoading pocketLoading) {
        Pocket pocket = PocketMoveFactory.giveType(pocketLoading.getType());
        pocket.setDelay(pocketLoading.getDelay());
        pocket.setLayoutY(-400);
        pocket.setLayoutX(-400);

        return pocket;
    }

    public List<Pocket> getPockets() {
        return pockets;
    }
}
