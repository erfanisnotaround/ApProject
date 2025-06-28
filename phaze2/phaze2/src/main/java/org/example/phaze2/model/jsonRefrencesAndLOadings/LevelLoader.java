package org.example.phaze2.model.jsonRefrencesAndLOadings;

import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.constants.Constants;

import java.io.IOException;
import java.util.List;

public class LevelLoader {
    private final LevelManager levelsManager;
    public LevelLoader(){
        {
            try {
                levelsManager = new LevelManager();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static List<Level> levels;
    private int level = 0;
    private Level currentLevel;
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Level loadingCurrentLevel() throws IOException {
//        levelsManager = new LevelManager();

        levels = levelsManager.getLevels();
        currentLevel = levels.get(level);
        WireManager wireManager = new WireManager(currentLevel.getMaxWire());
        Constants.getInstance().setWireManager(wireManager);
        return currentLevel;

    }
}