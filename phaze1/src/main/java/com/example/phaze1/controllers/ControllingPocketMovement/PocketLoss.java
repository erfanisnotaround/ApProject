package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.Constants.constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class PocketLoss {
    private Timeline losingTimeline;
    private Pane LineContainer;
    private ArrayList<Pocket> pockets = constants.getPockets();
    private ArrayList<Pocket> pocketLost = new ArrayList<>();
    private IntegerProperty pocketLoss = new SimpleIntegerProperty(0);
    public PocketLoss(Pane LineContainer) {
        this.LineContainer = LineContainer;
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
                System.out.println("you lose");
                losingTimeline.stop();
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
