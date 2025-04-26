package com.example.phaze1.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class level {
    private double MaxWire;
    private int numberOfSystems;
    private boolean levelPassed;
    @JsonProperty("systems")
    private List<Systems> systems = new ArrayList<>();
    public int getNumberOfSystems() { return numberOfSystems; }
    public void setNumberOfSystems(int n) { this.numberOfSystems = n; }

    public boolean isLevelPassed() { return levelPassed; }
    public void setLevelPassed(boolean p) { this.levelPassed = p; }

    public List<Systems> getSystems() { return systems; }
    public void setSystems(List<Systems> list) { this.systems = list; }
    public double getMaxWire() {return MaxWire;}

    public void setMaxWire(double maxWire) {MaxWire = maxWire;}
}
