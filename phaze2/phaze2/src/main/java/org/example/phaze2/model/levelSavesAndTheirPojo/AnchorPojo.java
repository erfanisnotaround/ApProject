package org.example.phaze2.model.levelSavesAndTheirPojo;

import javafx.geometry.Point2D;

public class AnchorPojo {

    private double centerX ,  centerY;

    private double latestCordX , latestCordY;


    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getLatestCordX() {
        return latestCordX;
    }

    public void setLatestCordX(double latestCordX) {
        this.latestCordX = latestCordX;
    }

    public double getLatestCordY() {
        return latestCordY;
    }

    public void setLatestCordY(double latestCordY) {
        this.latestCordY = latestCordY;
    }
}
