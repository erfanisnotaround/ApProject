package com.example.phaze1.model;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelsManager {
    private List<level> levels;
    public LevelsManager() throws IOException {
        JsonManager jsonManager = new JsonManager("D:\\programming\\project of Ap\\faz 1\\Phazes\\phaze1\\src\\main\\resources\\com\\example\\phaze1\\JsonFiles\\levels.json");
        levels = jsonManager.readArray(new TypeReference<List<level>>() {});
    }
    public List<level> getLevels() {
        return levels;
    }

    public  void setLevels(ArrayList<level> levels) {
        this.levels = levels;
    }
    public  void addLevel(level level) {
        levels.add(level);
    }
}
