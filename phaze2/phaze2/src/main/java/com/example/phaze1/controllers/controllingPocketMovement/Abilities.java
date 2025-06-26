package com.example.phaze1.controllers.controllingPocketMovement;
import com.example.phaze1.model.constants.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public enum Abilities {
    OATar{
        @Override
        public void Benefit(int coins , Label coinsShower) {
            coinsNeeded = 3;
            if (coins - coinsNeeded>=0){
                coinsShower.setText(String.valueOf(coins - coinsNeeded));
                Constants.getInstance().setCancellingWaveForTenSeconds(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10)));
                timeline.setCycleCount(1);
                timeline.play();
                timeline.setOnFinished(e -> {
                    Constants.getInstance().setCancellingWaveForTenSeconds(false);
                });
            }

        }
    },
    OAiryaman{

        @Override
        public void Benefit(int coinsHolding, Label coins) {
            coinsNeeded = 4;
            if (coinsHolding - coinsNeeded>=0){
                coins.setText(String.valueOf(coinsHolding - coinsNeeded));
                Constants.getInstance().setCancellingCollision(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5)));
                timeline.setCycleCount(1);
                timeline.play();
                timeline.setOnFinished(e -> {
                    Constants.getInstance().setCancellingWaveForTenSeconds(false);
                });
            }
        }

    },
    OAnahita{

        @Override
        public void Benefit(int coinsHolding, Label coins) {
            coinsNeeded = 5;
            if (coinsHolding - coinsNeeded>=0){
                coins.setText(String.valueOf(coinsHolding - coinsNeeded));
                Constants.getInstance().setMakeEveryPocketNoiseZero(true);
            }
        }


    };
    int coinsNeeded;
    public void SetCoinsNeeded() {}
    public abstract void Benefit(int coinsHolding  , Label coins);
}
