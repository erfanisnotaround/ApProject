package org.example.phaze2.model.jsonRefrencesAndLOadings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private double MaxWire;
    private int numberOfSystems;
    private boolean levelPassed;
    @JsonProperty("systems")
    private List<System> systems = new ArrayList<>();
    @JsonProperty("pockets")
    private ArrayList<PocketLoading> pockets = new ArrayList<>();
    public int getNumberOfSystems() { return numberOfSystems; }
    public void setNumberOfSystems(int n) { this.numberOfSystems = n; }
    public boolean isLevelPassed() { return levelPassed; }
    public void setLevelPassed(boolean p) { this.levelPassed = p; }
    public List<System> getSystems() { return systems; }
    public void setSystems(List<System> list) { this.systems = list; }
    public double getMaxWire() {return MaxWire;}
    public void setMaxWire(double maxWire) {MaxWire = maxWire;}
    public ArrayList<PocketLoading> getPockets() {return pockets;}
    public void setPockets(ArrayList<PocketLoading> pockets) {this.pockets = pockets;}
}
