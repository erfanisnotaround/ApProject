package org.example.phaze2.model;

import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.jsonRefrencesAndLOadings.LevelManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoingToGamaInformation {
    private LevelManager levelManager;
    private int ChosenLevel;
    private List<Level> levels;
    private Level level;
    public GoingToGamaInformation(int ChosenLevel) {
        this.ChosenLevel = ChosenLevel;


        try {
            levelManager = new LevelManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (levelManager != null) {
            levels = levelManager.getLevels();
        }



        if (!levels.isEmpty()) {
            if (ChosenLevel != -1){
                this.level = levels.get(ChosenLevel);
                return;
            }
            level = getFirstUnAvaialbleLevel();
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


    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
}
