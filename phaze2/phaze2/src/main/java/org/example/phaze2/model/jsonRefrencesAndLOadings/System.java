package org.example.phaze2.model.jsonRefrencesAndLOadings;

import java.util.ArrayList;

public class System {
    private String systemName;
    private double x;
    private double y;
    private boolean isItStartSystem;
    private int numberOfSubSystems;
    private boolean isTheLightOn;
    private ArrayList<SubSystem> SubSystems = new ArrayList<>();

    public ArrayList<SubSystem> getSubSystems() {
        return SubSystems;
    }

    public void setSubSystems(ArrayList<SubSystem> subSystems) {
        SubSystems = subSystems;
    }
    public void add(SubSystem subSystem) {
        SubSystems.add(subSystem);
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

    public int getNumberOfSubSystems() {
        return numberOfSubSystems;
    }

    public void setNumberOfSubSystems(int numberOfSubSystems) {
        this.numberOfSubSystems = numberOfSubSystems;
    }

    public boolean isTheLightOn() {
        return isTheLightOn;
    }

    public void setTheLightOn(boolean theLightOn) {
        isTheLightOn = theLightOn;
    }

    public boolean isItStartSystem() {
        return isItStartSystem;
    }

    public void setItStartSystem(boolean itStartSystem) {
        isItStartSystem = itStartSystem;
    }


    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
