package com.example.phaze1.model;

import java.util.ArrayList;

public class LevelLoader {
    static ArrayList<level> levels = LevelsManager.getLevels();
    private int level = 0;
    private level currentLevel;
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    public level loadingCurrentLevel() {
        currentLevel = levels.get(level);
        return currentLevel;
    }


}
