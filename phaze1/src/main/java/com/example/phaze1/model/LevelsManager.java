package com.example.phaze1.model;

import java.util.ArrayList;

public class LevelsManager {
    private static ArrayList<level> levels = new ArrayList<>();

    public static ArrayList<level> getLevels() {
        return levels;
    }

    public static void setLevels(ArrayList<level> levels) {
        LevelsManager.levels = levels;
    }
    public static void addLevel(level level) {
        levels.add(level);
    }
}
