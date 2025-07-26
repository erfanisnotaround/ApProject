package org.example.phaze2.model.jsonRefrencesAndLOadings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Level {
    @JsonProperty("AvailableTime")
    private double AvailableTime;
    @JsonProperty("MaxWire")
    private double MaxWire;
    @JsonProperty("numberOfSystems")
    private int numberOfSystems;
    @JsonProperty("levelPassed")
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
    public void setMaxWire(double MaxWire) {this.MaxWire = MaxWire;}
    public ArrayList<PocketLoading> getPockets() {return pockets;}
    public void setPockets(ArrayList<PocketLoading> pockets) {this.pockets = pockets;}

    public double getAvailableTime() {
        return AvailableTime;
    }

    public void setAvailableTime(double availableTime) {
        AvailableTime = availableTime;
    }
}
