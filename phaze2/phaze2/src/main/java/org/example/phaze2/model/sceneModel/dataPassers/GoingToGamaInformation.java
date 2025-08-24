package org.example.phaze2.model.sceneModel.dataPassers;

import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.jsonRefrencesAndLOadings.LevelManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoingToGamaInformation {
    private WireManager wireManager;

    private int ChosenLevel;
    private List<Level> levels;
    private Level level;
    private double availableTime;
    public GoingToGamaInformation(int ChosenLevel , List<Level> levels) {
        this.ChosenLevel = ChosenLevel;
        this.levels = levels;








        if (!levels.isEmpty()) {
            if (ChosenLevel != -1){
                this.level = levels.get(ChosenLevel);
            }
            else {
                level = getFirstUnAvaialbleLevel();
            }
            this.wireManager = new WireManager(level.getMaxWire());
            availableTime = level.getAvailableTime();


        }


    }
    public Level getFirstUnAvaialbleLevel() {
        for (Level level : levels) {
            if (!level.isLevelPassed()){
                return level;
            }
        }
        return levels.getLast();
    }
    public void makeCurrentLevelPassed() {
        int index = levels.indexOf(level);
        levels.get(index).setLevelPassed(true);
    }


    public WireManager getWireManager() {
        return wireManager;
    }
    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
    public Level getLevel() {
        return level;
    }
    public int getChosenLevel() {
        return levels.indexOf(level);
    }
    public double getAvailableTime() {
        return availableTime;
    }
}
