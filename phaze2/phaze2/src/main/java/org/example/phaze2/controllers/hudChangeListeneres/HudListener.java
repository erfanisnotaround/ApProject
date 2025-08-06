package org.example.phaze2.controllers.hudChangeListeneres;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.hudModels.AbilityAliveManager;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.hudModels.HUDLabelsIndex;
import org.example.phaze2.model.hudModels.NumberOfPocketLossManager;
import org.example.phaze2.viewRelated.hudView.AbilityLabels;
import org.example.phaze2.viewRelated.hudView.MakeHUD;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class HudListener extends AnimationTimer {

    private Map<Integer, AbilityLabels> AbilityLAbelsMap;
    private Map<Integer , Label> normalLabelsMap;
    private MakeHUD makeHUD;
    private CoinsManager coinsManager;
    private NumberOfPocketLossManager numberOfPocketLossManager;
    private WireManager wireManager;
    private GameState gameState;
    private AbilityAliveManager abilityAliveManager;

    public HudListener(MakeHUD makeHUD , GameState gameState) {
        this.makeHUD = makeHUD;
        AbilityLAbelsMap = makeHUD.getAbilityLabelsMap();
        normalLabelsMap  = makeHUD.getNormalLabelsMap();

        coinsManager = Constants.getInstance().getCoinsManager();
        numberOfPocketLossManager = Constants.getInstance().getNumberOfPocketLossManager();
        wireManager = Constants.getInstance().getWireManager();
        this.gameState = gameState;
    }

    @Override
    public void handle(long l) {
        abilityAliveManager = gameState.getResources().getAbilityAliveManager();
        for (Map.Entry<Integer, AbilityLabels> entry : AbilityLAbelsMap.entrySet()) {
            Integer key = entry.getKey();
            AbilityLabels abilityLabels = entry.getValue();
            AbilityTypes abilityType = abilityLabels.getAbilityType();
            if (abilityAliveManager.isAlive(abilityType)) {
                AbilityLabels abilityLabels1 = AbilityLAbelsMap.get(key);

                abilityLabels1.setText("active");


            }
            else {
                AbilityLabels abilityLabels1 = AbilityLAbelsMap.get(key);
                abilityLabels1.setText("inactive");
            }
        }

        normalLabelsMap.get(HUDLabelsIndex.Coins.getNumberToAccessLabel()).setText("Coins : " + coinsManager.getNumberOfCoins());
        normalLabelsMap.get(HUDLabelsIndex.WireLeft.getNumberToAccessLabel()).setText("Wire Left : " + wireManager.remaining());
//        normalLabelsMap.get(HUDLabelsIndex.PocketLoss.getNumberToAccessLabel()).setText("PocketLoss : " + numberOfPocketLossManager.getPocketLoss());




    }

    public void Stop(){
        stop();
    }
    public void Start(){
        start();
    }

    public void setAbilityLabelsMap(Map<Integer, AbilityLabels> abilityLabelsMap) {
        this.AbilityLAbelsMap = abilityLabelsMap;
    }
}
