package org.example.phaze2.viewRelated.bringingLevelToReality;

import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.jsonRefrencesAndLOadings.PocketLoading;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PocketProcessor implements Runnable {
    private List<PocketMain> pockets = new ArrayList<>();
    private List<PocketLoading> pocketLoadings;
    private final Map<String , PocketMain> pocketMainMap;
    private GameState gameState;
    public PocketProcessor(List<PocketLoading> pocketLoadings , GameState gameState) {
        this.pocketLoadings = pocketLoadings;
        this.gameState = gameState;
        pocketMainMap = gameState.getResources().getPocketMainMap();
    }
    @Override
    public void run() {
        pocketMainMap.clear();
        for (PocketLoading pocketLoading : pocketLoadings) {
            pockets.add(processPocket(pocketLoading));
        }
    }
    private PocketMain processPocket(PocketLoading pocketLoading) {
        PocketMain pocket = new PocketMain(pocketLoading.getType() , gameState);
        pocket.setDelay(pocketLoading.getDelay());

        pocket.setPocketId(pocketLoading.getPocketName());
        pocket.setFirstPocketType(pocket.getType());
        pocket.setTypeBeforeChange(pocketLoading.getType());

        pocketMainMap.put(pocketLoading.getPocketName(), pocket);
        return pocket;
    }

    public List<PocketMain> getPockets() {
        return pockets;
    }
}
