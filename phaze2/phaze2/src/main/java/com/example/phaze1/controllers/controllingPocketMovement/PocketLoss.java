package com.example.phaze1.controllers.controllingPocketMovement;
import com.example.phaze1.controllers.sceneControllers.gameOverSceneManager;
import com.example.phaze1.model.systemsInfoAndManagers.Pocket;
import com.example.phaze1.model.constants.constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import com.example.phaze1.model.agents.mediaAgent;

import java.io.IOException;
import java.util.ArrayList;

public class PocketLoss {
    private mediaAgent agent;
    private gameOverSceneManager gameOverSceneManager;
    private Timeline losingTimeline;
    private Pane LineContainer;
    private ArrayList<Pocket> pockets = constants.getPockets();
    private ArrayList<Pocket> pocketLost = new ArrayList<>();
    private IntegerProperty pocketLoss = new SimpleIntegerProperty(0);
    public PocketLoss(Pane LineContainer , mediaAgent agent) {
        this.LineContainer = LineContainer;
        this.agent = agent;
    }
    public void removeWastedPockets() {
        for (Pocket p : pockets) {
            p.HPProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() == 0.0){
                    p.setIsLost(true);
                    pockets.remove(p);
                    pocketLost.add(p);
                    LineContainer.getChildren().remove(p);
                    pocketLoss.set(pocketLoss.get() + 1);
                }
            });
        }
        GameOver();
    }
    public void GameOver(){
        int halfOfPockets = constants.getNumberOfPockets()/ 2;
        losingTimeline = new Timeline(new KeyFrame(Duration.millis(1) , event -> {
            if (pocketLost.size() >= halfOfPockets){
                losingTimeline.stop();
                System.out.println("Loseeeee");
                gameOverSceneManager = new gameOverSceneManager(pocketLost.size() , constants.getNumberOfPockets() - pocketLost.size() , false);
                try {
                    agent.Stop();
                    gameOverSceneManager.goToAfterGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        losingTimeline.setCycleCount(Timeline.INDEFINITE);
        constants.couldWeUseGameOverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                losingTimeline.play();
            }
        });
    }
    public void resetPocketLoss() {
        pockets.addAll(pocketLost);
        pocketLoss.set(0);
        for (Pocket p : pockets) {
            p.setIsLost(false);
            if (!LineContainer.getChildren().contains(p)) {
                LineContainer.getChildren().add(p);
            }
        }
    }

    public int getPocketLoss() {
        return pocketLoss.get();
    }

    public IntegerProperty pocketLossProperty() {
        return pocketLoss;
    }

    public void setPocketLoss(int pocketLoss) {
        this.pocketLoss.set(pocketLoss);
    }
}
