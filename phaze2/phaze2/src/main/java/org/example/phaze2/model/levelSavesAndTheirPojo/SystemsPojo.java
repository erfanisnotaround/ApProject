package org.example.phaze2.model.levelSavesAndTheirPojo;

import org.example.phaze2.model.constants.SystemTypes;

public class SystemsPojo {
    private boolean isItDown;
    private SystemTypes systemType;
    private double systemHeight;
    private double systemWidth;
    private boolean lightBoolean;
    private double x;
    private double y;
    private String systemID;
    private int numberOfSubSystems;
    private String[] capacity;


    public boolean isItDown() {
        return isItDown;
    }

    public void setItDown(boolean itDown) {
        isItDown = itDown;
    }

    public SystemTypes getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemTypes systemType) {
        this.systemType = systemType;
    }

    public double getSystemHeight() {
        return systemHeight;
    }

    public void setSystemHeight(double systemHeight) {
        this.systemHeight = systemHeight;
    }

    public double getSystemWidth() {
        return systemWidth;
    }

    public void setSystemWidth(double systemWidth) {
        this.systemWidth = systemWidth;
    }

    public boolean isLightBoolean() {
        return lightBoolean;
    }

    public void setLightBoolean(boolean lightBoolean) {
        this.lightBoolean = lightBoolean;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public int getNumberOfSubSystems() {
        return numberOfSubSystems;
    }

    public void setNumberOfSubSystems(int numberOfSubSystems) {
        this.numberOfSubSystems = numberOfSubSystems;
    }

    public String[] getCapacity() {
        return capacity;
    }

    public void setCapacity(String[] capacity) {
        this.capacity = capacity;
    }
}
