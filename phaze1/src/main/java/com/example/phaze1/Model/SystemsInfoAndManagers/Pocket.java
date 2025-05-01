package com.example.phaze1.Model.SystemsInfoAndManagers;
import javafx.scene.shape.*;
public class Pocket extends Polygon{
    private GateType type;
    private double delay;
    private int whichSubSystem;
    private double distanceFromTheLine = 0;
    private static double MaxDistanceFromTheLine = 3;

    public Pocket(double v, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        super(v, v1, v2, v3, v4, v5, v6, v7);
    }

    public Pocket(double v, double v1, int v2, int v3, double v4, int v5) {
        super(v, v1, v2, v3, v4, v5);
    }

    public static double getMaxDistanceFromTheLine() {
        return MaxDistanceFromTheLine;
    }

    public static void setMaxDistanceFromTheLine(double maxDistanceFromTheLine) {
        MaxDistanceFromTheLine = maxDistanceFromTheLine;
    }

    public double getDistanceFromTheLine() {
        return distanceFromTheLine;
    }

    public void setDistanceFromTheLine(double distanceFromTheLine) {
        this.distanceFromTheLine = distanceFromTheLine;
    }

    public GateType getType() {
        return type;
    }

    public void setType(GateType type) {
        this.type = type;
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public int getWhichSubSystem() {
        return whichSubSystem;
    }

    public void setWhichSubSystem(int whichSubSystem) {
        this.whichSubSystem = whichSubSystem;
    }
}
