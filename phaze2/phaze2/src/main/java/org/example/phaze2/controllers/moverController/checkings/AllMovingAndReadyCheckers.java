package org.example.phaze2.controllers.moverController.checkings;

import org.example.phaze2.model.constants.GameState;

public class AllMovingAndReadyCheckers {

    private SystemsConnectionChecker checker ;
    private GameState gameState ;

    public AllMovingAndReadyCheckers(GameState gameState) {
        this.gameState = gameState;
        checker = new SystemsConnectionChecker(gameState);
    }

    public void check() {
        checker.start();
    }
    public void Stop(){
        checker.stop();
    }
}
