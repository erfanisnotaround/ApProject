package org.example.phaze2.model.levelSavesAndTheirPojo;

import javafx.geometry.Point2D;

public class PathMoverPojo {
    private PortPojo StarterPort;
    private double multiplier;
    private double s;
    private double v;
    private double a;
    private double latestLineDistanceX , latestLineDistanceY;
    private double currentLineDistanceX , currentLineDistanceY;


    private double latestLineDistanceForWholeMoveX , latestLineDistanceForWholeMoveY;
    private double currentLineDistanceForWholeMoveX , currentLineDistanceForWholeMoveY;

    private double lineDistancePerMoveXForWhole;
    private double lineDistancePerMoveYForWhole;



    private double lineDistancePerMoveX;
    private double lineDistancePerMoveY;
    private double STEPS;
    private double AngleNeeded;
    private boolean rotate;

    public PortPojo getStarterPort() {
        return StarterPort;
    }

    public void setStarterPort(PortPojo starterPort) {
        StarterPort = starterPort;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }



    public double getLatestLineDistanceX() {
        return latestLineDistanceX;
    }

    public void setLatestLineDistanceX(double latestLineDistanceX) {
        this.latestLineDistanceX = latestLineDistanceX;
    }

    public double getLatestLineDistanceY() {
        return latestLineDistanceY;
    }

    public void setLatestLineDistanceY(double latestLineDistanceY) {
        this.latestLineDistanceY = latestLineDistanceY;
    }

    public double getCurrentLineDistanceX() {
        return currentLineDistanceX;
    }

    public void setCurrentLineDistanceX(double currentLineDistanceX) {
        this.currentLineDistanceX = currentLineDistanceX;
    }

    public double getCurrentLineDistanceY() {
        return currentLineDistanceY;
    }

    public void setCurrentLineDistanceY(double currentLineDistanceY) {
        this.currentLineDistanceY = currentLineDistanceY;
    }

    public double getLatestLineDistanceForWholeMoveX() {
        return latestLineDistanceForWholeMoveX;
    }

    public void setLatestLineDistanceForWholeMoveX(double latestLineDistanceForWholeMoveX) {
        this.latestLineDistanceForWholeMoveX = latestLineDistanceForWholeMoveX;
    }

    public double getLatestLineDistanceForWholeMoveY() {
        return latestLineDistanceForWholeMoveY;
    }

    public void setLatestLineDistanceForWholeMoveY(double latestLineDistanceForWholeMoveY) {
        this.latestLineDistanceForWholeMoveY = latestLineDistanceForWholeMoveY;
    }

    public double getCurrentLineDistanceForWholeMoveX() {
        return currentLineDistanceForWholeMoveX;
    }

    public void setCurrentLineDistanceForWholeMoveX(double currentLineDistanceForWholeMoveX) {
        this.currentLineDistanceForWholeMoveX = currentLineDistanceForWholeMoveX;
    }

    public double getCurrentLineDistanceForWholeMoveY() {
        return currentLineDistanceForWholeMoveY;
    }

    public void setCurrentLineDistanceForWholeMoveY(double currentLineDistanceForWholeMoveY) {
        this.currentLineDistanceForWholeMoveY = currentLineDistanceForWholeMoveY;
    }

    public double getLineDistancePerMoveXForWhole() {
        return lineDistancePerMoveXForWhole;
    }

    public void setLineDistancePerMoveXForWhole(double lineDistancePerMoveXForWhole) {
        this.lineDistancePerMoveXForWhole = lineDistancePerMoveXForWhole;
    }

    public double getLineDistancePerMoveYForWhole() {
        return lineDistancePerMoveYForWhole;
    }

    public void setLineDistancePerMoveYForWhole(double lineDistancePerMoveYForWhole) {
        this.lineDistancePerMoveYForWhole = lineDistancePerMoveYForWhole;
    }

    public double getLineDistancePerMoveX() {
        return lineDistancePerMoveX;
    }

    public void setLineDistancePerMoveX(double lineDistancePerMoveX) {
        this.lineDistancePerMoveX = lineDistancePerMoveX;
    }

    public double getLineDistancePerMoveY() {
        return lineDistancePerMoveY;
    }

    public void setLineDistancePerMoveY(double lineDistancePerMoveY) {
        this.lineDistancePerMoveY = lineDistancePerMoveY;
    }

    public double getSTEPS() {
        return STEPS;
    }

    public void setSTEPS(double STEPS) {
        this.STEPS = STEPS;
    }

    public double getAngleNeeded() {
        return AngleNeeded;
    }

    public void setAngleNeeded(double angleNeeded) {
        AngleNeeded = angleNeeded;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }
}
