package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.geometry.Point2D;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PocketLossInWin {

    private final Map<String , Set<PocketMain>>  pocketGroups;
    private final GameState gameState;
    List<Integer> check = new ArrayList<>();


    public PocketLossInWin(GameState gameState) {
        this.gameState = gameState;
        pocketGroups = gameState.getResources().getPocketGroupIdGroups();
    }

    public double detectOutLeftOvers(){
        for (String pocketGroupId : pocketGroups.keySet()) {
            Set<PocketMain> pocketGroup = pocketGroups.get(pocketGroupId);

            boolean access = detectAccess(pocketGroup);
            if (access){

                break;
            }
        }
    }

    private synchronized Point2D WinAndLoss(Set<PocketMain> pocketGroup){
        check.clear();
        double win = 0;
        double loss = 0;
        double whole = 0;

        if (pocketGroup.isEmpty()){return Point2D.ZERO;}

        for (PocketMain pocket : pocketGroup) {
            check.add(pocket.getMaxHp());
            whole += pocket.getMaxHp();
        }

        win = evaluateWin(check , pocketGroup.size());
        loss = evaluateLoss(win , whole);

        return new Point2D(win, loss);

    }
    private double evaluateWin(List<Integer> values , double numberOFThem){

        double win = 0;
        for (Integer value : values) {
            win *= value;
        }

        double beforeJoseSahih = numberOFThem * Math.pow(win, 1/numberOFThem);

        return Math.floor(beforeJoseSahih);
    }
    private double evaluateLoss(double win , double whole){
        return whole - win;
    }

    private boolean detectAccess(Set<PocketMain> pocketGroup){
        for (PocketMain pocket : pocketGroup) {
            if (pocket.isDone()) return false;
        }
        return true;
    }
}
