package com.example.phaze1.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    LevelsManager levelsManager;
    static List<level> levels;
    private int level = 0;
    private level currentLevel;
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    public level loadingCurrentLevel() throws IOException {
        levelsManager = new LevelsManager();
        levels = levelsManager.getLevels();
        currentLevel = levels.get(level);
        WireManager wireManager = new WireManager(currentLevel.getMaxWire());
        constants.setWireManager(wireManager);
        return currentLevel;
    }


}
