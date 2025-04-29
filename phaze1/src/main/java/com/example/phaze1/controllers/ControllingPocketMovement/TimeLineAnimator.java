package com.example.phaze1.controllers.ControllingPocketMovement;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class TimeLineAnimator {

    public static void animateAlong(Polyline poly, Node node, Duration duration) {
        ObservableList<Double> pts = poly.getPoints();
        int N = pts.size() / 2;
        if (N < 2) return;

        double[] segLen = new double[N - 1];
        double totalLen = 0;
        for (int i = 0; i < N - 1; i++) {
            double x0 = pts.get(2 * i),     y0 = pts.get(2 * i + 1);
            double x1 = pts.get(2 * (i+1)), y1 = pts.get(2 * (i+1) + 1);
            double d  = Math.hypot(x1 - x0, y1 - y0);
            segLen[i] = d;
            totalLen += d;
        }


        DoubleProperty t = new SimpleDoubleProperty(0);

        double finalTotalLen = totalLen;
        t.addListener((obs, old, frac) -> {
            double target = frac.doubleValue() * finalTotalLen;
            double acc = 0;
            int   idx = 0;

            while (idx < segLen.length && acc + segLen[idx] < target) {
                acc += segLen[idx++];
            }
            if (idx >= segLen.length) {
                idx = segLen.length - 1;
                acc = finalTotalLen - segLen[idx];
            }

            double localT = (target - acc) / segLen[idx];
            double x0 = pts.get(2 * idx),     y0 = pts.get(2 * idx + 1);
            double x1 = pts.get(2 * (idx+1)), y1 = pts.get(2 * (idx+1) + 1);

            node.setTranslateX(x0 + (x1 - x0) * localT);
            node.setTranslateY(y0 - 3 + (y1 - y0) * localT);
        });

        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO,   new KeyValue(t, 0)),
                new KeyFrame(duration,        new KeyValue(t, 1))
        );
        tl.setCycleCount(1);
        tl.play();
    }
}
