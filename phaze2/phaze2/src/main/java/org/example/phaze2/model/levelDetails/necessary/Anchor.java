package org.example.phaze2.model.levelDetails.necessary;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anchor extends Circle {
    private Point2D center;
    private Point2D latestCord;

    public Anchor(Point2D center) {
        super(center.getX() , center.getY() , 6);
        this.center = center;

        this.setFill(Color.RED);
    }

    public Point2D getCenter() {
        return center;
    }
    public void setCenter(Point2D center) {
        setCenterX( center.getX());
        setCenterY( center.getY());
        this.center = center;
    }

    public Point2D getLatestCord() {
        return latestCord;
    }
    public void commit(){
        latestCord = center;
    }
}