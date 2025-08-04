package org.example.phaze2.model.abilities.mechanics.followers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class ZeroAccelerationMaker extends Follower {


    private int eachTimeCheckMilliSeconds = 500;
    private int PeriodOFTimeNeededSeconds = 20;
    private int Cycles = 1000 * PeriodOFTimeNeededSeconds / eachTimeCheckMilliSeconds;
    private double AccelarationWanted = 0.0;


    public ZeroAccelerationMaker(FollowerType followerType) {
        super(followerType);
    }

    @Override
    void execute(PocketMain pocket) {


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(eachTimeCheckMilliSeconds), event -> {
            pocket.getPathMover().setA(AccelarationWanted);
        }));
        timeline.setCycleCount(Cycles);
        timeline.setOnFinished(event -> {
            pocket.getPathMover().setA(pocket.getPathMover().getCommitedAcceleration());
        });

        timeline.play();
    }
}
