package com.example.phaze1.controllers.controllingPocketMovement;

import com.example.phaze1.model.systemsInfoAndManagers.Connection;
import com.example.phaze1.model.systemsInfoAndManagers.GatePortInfo;
import com.example.phaze1.model.systemsInfoAndManagers.Pocket;
import com.example.phaze1.model.constants.constants;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class TimeLineAnimator {
    public static void animateAlong(Polyline poly,
                                    Pocket node,
                                    GatePortInfo portInfo,
                                    Connection connection)
    {
        ObservableList<Double> pts = poly.getPoints();
        int N = pts.size()/2;
        if (N < 2) return;

        double[] segLen = new double[N-1];
        double totalLen = 0;
        for(int i=0; i<N-1; i++){
            double x0 = pts.get(2*i),   y0 = pts.get(2*i+1);
            double x1 = pts.get(2*(i+1)), y1 = pts.get(2*(i+1)+1);
            segLen[i] = Math.hypot(x1-x0, y1-y0);
            totalLen += segLen[i];
        }

        DoubleProperty t = new SimpleDoubleProperty(0);
        double finalTotalLen = totalLen;
        t.addListener((obs, oldV, frac) -> {
            double target = frac.doubleValue() * finalTotalLen;
            double acc = 0;
            int idx = 0;
            while (idx < segLen.length && acc + segLen[idx] < target) {
                acc += segLen[idx++];
            }
            if (idx >= segLen.length) {
                idx = segLen.length - 1;
                acc = finalTotalLen - segLen[idx];
            }
            double localT = (target - acc)/segLen[idx];
            double x0 = pts.get(2*idx),     y0 = pts.get(2*idx+1);
            double x1 = pts.get(2*(idx+1)), y1 = pts.get(2*(idx+1)+1);

            node.setTranslateX(
                    x0 - (node.getDistanceFromTheLine()-4)
                            + (x1-x0)*localT
            );
            node.setTranslateY(
                    y0 - (node.getDistanceFromTheLine()+3)
                            + (y1-y0)*localT
            );
        });

        Duration movementDuration = Duration.seconds(totalLen / node.getSpeed());
        Timeline mover = new Timeline(
                new KeyFrame(Duration.ZERO,       new KeyValue(t, 0)),
                new KeyFrame(movementDuration,    new KeyValue(t, 1))
        );
        mover.setCycleCount(1);

        Timeline clock = new Timeline(
                new KeyFrame(Duration.millis(1), e ->
                        node.setAvailableTime(
                                node.getAvailableTime() - (0.001 * node.getSpeed())
                        )
                )
        );
        clock.setCycleCount(Animation.INDEFINITE);

        node.availableTimeProperty().addListener((obs, oldV, newV) -> {
            if (newV.doubleValue() <= 0) {
                mover.stop();
                clock.stop();
            }
        });

        constants.addStopTimelines(mover);
        constants.addStopTimelines(clock);
        mover.setOnFinished(ev -> {
            constants.removeStopTimelines(mover);
            constants.removeStopTimelines(clock);
            if (node.isLastRound()){
                System.out.println("we dont do drugs");
            }
            if (!node.isIsLost()) {
                node.setCoins(node.getCoins() + 1);
            }
            clock.stop();
            connection.curve.isItUsed.set(false);
            node.setAvailableTime(node.getAvailableTime() - 5);
            if (node.isLastRound()) {
                node.setIsWinning(true);
            }
        });



        mover.play();
        clock.play();
    }


}
