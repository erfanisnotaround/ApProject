package org.example.phaze2.model.winAndPocketLossModel;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;
import java.util.Map;

public class PauseManager {
    private List<PocketMain> pocketMains;
    private GameState gameState;



    public PauseManager(GameState gameState) {
        this.pocketMains = gameState.getResources().getPockets();
        this.gameState = gameState;

    }
    public void Pause() {
        for (PocketMain pocketMain : pocketMains) {
            pocketMain.getPathMover().pause();
        }
    }
    public void Resume() {
        for (PocketMain pocketMain : pocketMains) {
            pocketMain.getPathMover().resume();
        }
    }
}
