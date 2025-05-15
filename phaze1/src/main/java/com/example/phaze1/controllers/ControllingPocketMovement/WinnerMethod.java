package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.Constants.constants;
import com.example.phaze1.controllers.sceneControllers.gameOverSceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class WinnerMethod {
    private gameOverSceneManager gameOverSceneManager;
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
        System.out.println(numberOfPockets);
        System.out.println("Half Of Pockets: " + halfOfPockets);
         winnerTimeline = new Timeline(new KeyFrame(Duration.millis(1),event -> {
            if (winnerPockets.size() > halfOfPockets) {
                try {
                    winnerTimeline.stop();
                    afterWinning();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        winnerTimeline.setCycleCount(Timeline.INDEFINITE);
        winnerTimeline.play();
    }
    public void afterWinning() throws IOException {
        System.out.println("this is game ");
        int badPockets = gettingBadPockets();
        gameOverSceneManager = new gameOverSceneManager(badPockets,constants.getNumberOfPockets() - badPockets , true);
        gameOverSceneManager.goToAfterGame();
    }
    public int gettingBadPockets(){
        int badPockets = 0;
        for (Pocket p : pockets) {
            if (p.isIsLost()) {
                badPockets++;
            }
        }
        return badPockets;
    }
}
