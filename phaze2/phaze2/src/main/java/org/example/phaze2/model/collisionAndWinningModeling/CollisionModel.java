package org.example.phaze2.model.collisionAndWinningModeling;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;

public class CollisionModel {
    public Point2D GetHitPoint(Point2D firstPocketCord , Point2D secondPocketCord) {




        double CenterX = (firstPocketCord.getX() + secondPocketCord.getX()) / 2 ;
        double CenterY = (firstPocketCord.getY() + secondPocketCord.getY()) / 2 ;

        return new Point2D(CenterX, CenterY);

    }
    public static boolean checkCollision(CollisionPolygon polyA , CollisionPolygon polyB) {
        List<Point2D> axes = new ArrayList<>();
        axes.addAll(polyA.getAxes());
        axes.addAll(polyB.getAxes());

        for (Point2D axis : axes) {
            double[] p1 = polyA.project(axis);
            double[] p2 = polyB.project(axis);

            if (p1[1] < p2[0] || p2[1] < p1[0]) {
                return false;
            }
        }
        return true; // No gap found, they are colliding
    }
}
