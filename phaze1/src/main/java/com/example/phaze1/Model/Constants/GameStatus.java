package com.example.phaze1.Model.Constants;

import com.example.phaze1.Model.JSonManager.JsonManager;
import com.example.phaze1.Model.levelLoadingStuff.level;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum GameStatus {
    START_GAME,
    SETTINGS ,
    LEVELS,
    MENU,
    ;

    public static class LevelsManager {
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
}
