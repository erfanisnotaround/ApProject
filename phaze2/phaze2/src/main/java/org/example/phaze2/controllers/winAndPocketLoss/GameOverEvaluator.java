package org.example.phaze2.controllers.winAndPocketLoss;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.winAndPocketLossModel.GameCondition;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameOverEvaluator {
    private final Map<GameCondition, GameOverType> conditions = new LinkedHashMap<>();
    private final Map<GameOverType , GameCondition> accessTypes = new LinkedHashMap<>();
    private final GameOverHandler handler;
    private boolean fired = false;
    private String lastReason = "";
    private PocketLossInWin lossInWin;
    private GameState gameState;

    public GameOverEvaluator(GameOverHandler handler , GameState gameState) {
        this.handler = handler;
        this.gameState = gameState;
        lossInWin = new PocketLossInWin(gameState);
    }

    public GameOverEvaluator add(GameCondition condition, GameOverType type) {
        conditions.put(condition, type);accessTypes.put(type , condition); return this;
    }

    public GameOverType evaluateOnce() {

        int[] chackLoss = lossInWin.detectOutLeftOvers();

        int i = 1;
        for (var e : conditions.entrySet()) {
            if (e.getKey().check()) {
//                fired = true;
                lastReason = e.getKey().reason();
                return e.getValue();
            }
//            System.out.println(chackLoss[i] + "  " + e.getValue()   );
            e.getKey().inject(chackLoss[i]);
            i--;
        }
        return null;
    }
    public GameCondition getCondition(GameOverType type) {
        return accessTypes.get(type);
    }
    public String lastReason() { return lastReason; }
    public void reset(){

//        lossInWin.reset();
        for (var e : conditions.entrySet()) {
            e.getKey().reset();
        }
    }
}

