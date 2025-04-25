package com.example.phaze1.model;

import java.util.ArrayList;
import java.util.List;

public class levelsManager {
    private int numberOfSystems;
    private boolean levelPassed;
    @JsonProperty
    private List<Systems> systems = new ArrayList<>();

    public int getNumberOfSystems() { return numberOfSystems; }
    public void setNumberOfSystems(int n) { this.numberOfSystems = n; }

    public boolean isLevelPassed() { return levelPassed; }
    public void setLevelPassed(boolean p) { this.levelPassed = p; }

    public List<Systems> getSystems() { return systems; }
    public void setSystems(List<Systems> list) { this.systems = list; }
}
