package com.example.phaze1.controllers.controllingPocketMovement;

import com.example.phaze1.model.systemsInfoAndManagers.Pocket;
import com.example.phaze1.model.constants.constants;

import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionsDetection {
    private CollisionHandler handler ;
    private final List<Pocket> pockets;
    private final double threshold;
    private final Set<CollisionPair> previous = new HashSet<>();

    public CollisionsDetection(List<Pocket> pockets) {
        this.pockets   = pockets;
        this.threshold = pockets.get(0).getRadiusOFDetection();
        handler = new CollisionHandler(pockets);
    }

    public void checkCollisions() {
        Set<CollisionPair> current = new HashSet<>();
        for (int i = 0; i < pockets.size(); i++) {
            Pocket a = pockets.get(i);
            if (!a.isInTheGame()) continue;
            double ax = a.getLayoutX() + a.getTranslateX();
            double ay = a.getLayoutY() + a.getTranslateY();

            for (int j = i+1; j < pockets.size(); j++) {
                Pocket b = pockets.get(j);
                if (!b.isInTheGame()) continue;
                double bx = b.getLayoutX() + b.getTranslateX();
                double by = b.getLayoutY() + b.getTranslateY();

                double dist = Math.hypot(ax - bx, ay - by);
                if (dist < threshold && preciseIntersect(a, b)) {
                    current.add(new CollisionPair(a, b));
                }
            }
        }

        if (constants.isCancellingCollision()){
            return;
        }
        for (CollisionPair pair : previous) {
            if (!current.contains(pair)) {
                handler.SpreadImpact(pair.a, pair.b);
                System.out.println("Collision detected1");
            }
        }
        previous.clear();
        previous.addAll(current);
    }
    public void reset() {
        previous.clear();
        handler.reset();
    }
    private boolean preciseIntersect(Pocket a, Pocket b) {
        Shape overlap = Shape.intersect(a, b);
        return overlap.getBoundsInLocal().getWidth() > 0
                && overlap.getBoundsInLocal().getHeight() > 0;
    }


}
