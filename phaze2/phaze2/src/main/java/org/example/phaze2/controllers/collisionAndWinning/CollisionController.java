package org.example.phaze2.controllers.collisionAndWinning;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import org.example.phaze2.model.collisionAndWinningModeling.CollisionModel;
import org.example.phaze2.model.collisionAndWinningModeling.CollisionPolygon;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionController {
    Constants constants = Constants.getInstance();
    List<PocketMain> pockets;
    Set<CollisionPair> previousCollisionPairs = new HashSet<CollisionPair>();
    CollisionModel collisionModel = new CollisionModel();
    CollisionHandler collisionHandler;


    private final double RadiusOfCollision = 10;
    private final GameState gameState;
    public CollisionController(List<PocketMain> pockets , GameState gameState) {
        this.pockets = pockets;
        this.gameState = gameState;
        collisionHandler = new CollisionHandler(pockets , gameState);
    }


    public void Collision() {
        Set<CollisionPair> currentCollisionPairs = new HashSet<>();
        for (PocketMain FirstPocket : pockets) {
            if (!FirstPocket.isIsItMoved()) continue;

            for (PocketMain SecondPocket : pockets) {
                if (!SecondPocket.isIsItMoved()||FirstPocket.equals(SecondPocket)) continue;


                double distance = FirstPocket.centre().distance(SecondPocket.centre());
                if (distance < RadiusOfCollision) {

                    Point2D hitPoint = GetHitPoint(FirstPocket, SecondPocket);
                    if (hitPoint != null) {
                        currentCollisionPairs.add(new CollisionPair(FirstPocket, SecondPocket , hitPoint));
                    }
                }
            }
        }


        if (gameState.DoesCollideCounts()){
            for (CollisionPair pair : currentCollisionPairs) {
                if(!previousCollisionPairs.contains(pair)) {
                    Platform.runLater(()->{
                        pair.getFirstPocket().setIsItCollided(true);
                        pair.getSecondPocket().setIsItCollided(true);
                    });
                    collisionHandler.SpreadImpact(pair);


                }
            }
            for (CollisionPair pair : previousCollisionPairs) {
            }
        }

        previousCollisionPairs.clear();
        previousCollisionPairs.addAll(currentCollisionPairs);
        currentCollisionPairs.clear();
    }
    public void reset() {
        previousCollisionPairs.clear();


    }


    public Point2D GetHitPoint(PocketMain firstPocket, PocketMain secondPocket) {
        if (!firstPocket.getBoundsInParent().intersects(secondPocket.getBoundsInParent())) return null;

        CollisionPolygon polyA = new CollisionPolygon(firstPocket.getHitBox());
        CollisionPolygon polyB = new CollisionPolygon(secondPocket.getHitBox());

        if (!collisionModel.checkCollision(polyA, polyB)) return null;

        return collisionModel.GetHitPoint(firstPocket.centre() , secondPocket.centre());
    }
}