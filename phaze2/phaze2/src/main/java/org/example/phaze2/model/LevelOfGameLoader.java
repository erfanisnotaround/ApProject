package org.example.phaze2.model;

import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.jsonRefrencesAndLOadings.LevelManager;

import java.io.IOException;
import java.util.List;

public class LevelOfGameLoader {
    private LevelManager levelManager;

    public LevelOfGameLoader() {
        try {
            levelManager = new LevelManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeItAgain(List<Level> levels) {
        try {
            levelManager.WriteLevelsAgain(levels);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Level> getLevels() {
        return levelManager.getLevels();
    }
}
