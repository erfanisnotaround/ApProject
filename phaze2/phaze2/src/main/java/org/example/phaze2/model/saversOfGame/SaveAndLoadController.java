package org.example.phaze2.model.saversOfGame;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.phaze2.model.agentsAndManagers.JsonManager;
import org.example.phaze2.model.levelSavesAndTheirPojo.LevelPojo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SaveAndLoadController {

    private final JsonManager jsonManager;
    private SaveMaker saveMaker;
    private List<LevelPojo> realLevelList;
    private final String path = "D:\\programming\\project of Ap\\faz 1\\Phazes\\phaze2\\phaze2\\src\\main\\resources\\org\\example\\phaze2\\jsonFiles\\levelSaves.json";

    public SaveAndLoadController() {
        this.jsonManager = new JsonManager(path);
    }

    public void startAutoSave(int currentLevelIndex) {

        try {
            this.realLevelList = jsonManager.readArray(new TypeReference<List<LevelPojo>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.saveMaker = new SaveMaker(currentLevelIndex, realLevelList);
        this.saveMaker.start();
    }

    public void changeCurrentLevel(int newLevelIndex) {
        if (saveMaker != null) {
            saveMaker.setCurrentLevel(newLevelIndex);
        }
    }

    public void stopAutoSave() {
        if (saveMaker != null) {
            saveMaker.stopSaving();
        }
    }

    public void writeLevelsToDisk() {
        try {
            jsonManager.writeArray(realLevelList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LevelPojo> getRealLevelList() {
        return realLevelList;
    }

    public LevelPojo getLevel(int index) {
        return realLevelList != null && index < realLevelList.size()
                ? realLevelList.get(index)
                : null;
    }
}
