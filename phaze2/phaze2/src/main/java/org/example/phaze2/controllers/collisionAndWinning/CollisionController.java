package org.example.phaze2.controllers.collisionAndWinning;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import org.example.phaze2.model.collisionAndWinningModeling.CollisionModel;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionController {
    Constants constants = Constants.getInstance();
    List<PocketMain> pockets;
    Set<CollisionPair> previousCollisionPairs = new HashSet<CollisionPair>();
    CollisionModel collisionModel = new CollisionModel();
    CollisionHandler collisionHandler = new CollisionHandler();


    private final double RadiusOfCollision = 60;
    public CollisionController() {
        pockets = constants.getPockets();
    }

    public void Collision() {
        Set<CollisionPair> currentCollisionPairs = new HashSet<>();
        for (PocketMain FirstPocket : pockets) {
            if (!FirstPocket.isIsItMoved()) continue;
            double X1 = FirstPocket.getPlaceOfX();
            double Y1 = FirstPocket.getPlaceOfY();

            for (PocketMain SecondPocket : pockets) {
                if (!SecondPocket.isIsItMoved()||FirstPocket.equals(SecondPocket)) continue;
                double X2 = SecondPocket.getPlaceOfX();
                double Y2 = SecondPocket.getPlaceOfY();

                double distance = Math.hypot(X1 - X2, Y1 - Y2);
                if (distance < RadiusOfCollision) {
                    Point2D hitPoint = GetHitPoint(FirstPocket, SecondPocket);
                    if (hitPoint != null) {
                        currentCollisionPairs.add(new CollisionPair(FirstPocket, SecondPocket , hitPoint));
                    }
                }
            }
        }

        for (CollisionPair pair : currentCollisionPairs) {
            if(!previousCollisionPairs.contains(pair)) {
                collisionHandler.SpreadImpact(pair);
            }
//            System.out.println(" Collision detected ");
        }
        for (CollisionPair pair : previousCollisionPairs) {
//            System.out.println(" Collision detected ");
        }

        previousCollisionPairs.clear();
        previousCollisionPairs.addAll(currentCollisionPairs);
        currentCollisionPairs.clear();
    }
    public void reset() {
        previousCollisionPairs.clear();

    }
    public Point2D GetHitPoint(PocketMain firstPocket, PocketMain secondPocket) {
        Shape intersection = Shape.intersect(firstPocket.getHitBox() , secondPocket.getHitBox());
        Bounds bounds = intersection.getBoundsInLocal();

        return collisionModel.GetHitPoint(bounds , new Point2D(firstPocket.getPlaceOfX() , firstPocket.getPlaceOfY()) ,
                new Point2D(secondPocket.getPlaceOfX() , secondPocket.getPlaceOfY()));

    }
}
