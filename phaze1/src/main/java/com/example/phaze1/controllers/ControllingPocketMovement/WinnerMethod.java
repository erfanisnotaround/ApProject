package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.Constants.constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class WinnerMethod {
    private Timeline winnerTimeline;
    private ArrayList<Pocket> pockets = constants.getPockets();
    private ArrayList<Pocket> winnerPockets = new ArrayList<>();
    private int numberOfPockets = constants.getNumberOfPockets();
    public void ListeningToWinningPockets() {
        for (Pocket p : pockets) {
            p.isWinningProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue && !p.isIsLost()) {
                    winnerPockets.add(p);
                }
            });
        }
        listenForWin();

    }
    public void listenForWin(){
        int halfOfPockets = numberOfPockets / 2;
         winnerTimeline = new Timeline(new KeyFrame(Duration.millis(1),event -> {
            if (winnerPockets.size() > halfOfPockets) {
                afterWinning();
                winnerTimeline.stop();

            }
        }));
        winnerTimeline.setCycleCount(Timeline.INDEFINITE);
        winnerTimeline.play();
    }
    public void afterWinning() {
        System.out.println("You Win");
    }
}
