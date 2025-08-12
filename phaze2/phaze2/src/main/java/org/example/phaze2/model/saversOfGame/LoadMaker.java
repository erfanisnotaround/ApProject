package org.example.phaze2.model.saversOfGame;

import org.example.phaze2.model.levelSavesAndTheirPojo.LevelPojo;

import java.util.List;

public class LoadMaker implements Runnable {
    private int ChosenLevel;
    private LoadHandler loadHandler;
    private List<LevelPojo> readableLevelList;


    public LoadMaker(int chosenLevel ,List<LevelPojo> readableLevelList , LoadAndSaveCompleterNecessaries loadCompleterNecessaries) {
        this.ChosenLevel = chosenLevel;
        loadHandler = new LoadHandler(loadCompleterNecessaries);
        this.readableLevelList = readableLevelList;
    }

    @Override
    public void run() {
        MakeChanges();
    }

    private void MakeChanges() {
        LevelPojo levelPojo = readableLevelList.get(ChosenLevel);
        loadHandler.StartSettingInfoOfLevel(levelPojo);

    }
}
