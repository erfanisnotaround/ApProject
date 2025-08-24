package org.example.phaze2.model.jsonRefrencesAndLOadings;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.phaze2.model.agentsAndManagers.JsonManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private List<Level> levels;
    private JsonManager jsonManager;
    public LevelManager() throws IOException {
        jsonManager = new JsonManager("D:\\programming\\project of Ap\\faz 1\\Phazes\\phaze2\\phaze2\\src\\main\\resources\\org\\example\\phaze2\\jsonFiles\\SystemInformation.json");
        levels = jsonManager.readArray(new TypeReference<List<Level>>() {});
    }
    public void WriteLevelsAgain(List<Level> levels) throws IOException {
        jsonManager.writeArray(levels);
    }
    public List<Level> getLevels() {
        return levels;
    }

    public  void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
    public  void addLevel(Level level) {
        levels.add(level);
    }
}
