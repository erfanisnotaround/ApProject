package org.example.phaze2.model.collisionAndWinningModeling;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class CollisionModel {
    public Point2D GetHitPoint(Bounds intersection , Point2D firstPocketCord , Point2D secondPocketCord) {

        double width = intersection.getWidth();
        double height = intersection.getHeight();

        if (width == 0 || height == 0) return null;


        double CenterX = (firstPocketCord.getX() + secondPocketCord.getX()) / 2 ;
        double CenterY = (firstPocketCord.getY() + secondPocketCord.getY()) / 2 ;

        return new Point2D(CenterX, CenterY);

    }
}
