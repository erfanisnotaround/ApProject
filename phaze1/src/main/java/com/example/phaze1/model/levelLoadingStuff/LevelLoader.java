package com.example.phaze1.model.levelLoadingStuff;

import com.example.phaze1.model.systemsInfoAndManagers.WireManager;
import com.example.phaze1.model.constants.constants;

import java.io.IOException;
import java.util.List;

public class LevelLoader {
    levelsManager levelsManager;
    static List<level> levels;
    private int level = 0;
    private level currentLevel;
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    public level loadingCurrentLevel() throws IOException {
        levelsManager = new levelsManager();
        levels = levelsManager.getLevels();
        currentLevel = levels.get(level);
        WireManager wireManager = new WireManager(currentLevel.getMaxWire());
        constants.setWireManager(wireManager);
        return currentLevel;
    }


}
