package org.example.phaze2.model.saversOfGame;

import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.levelSavesAndTheirPojo.LevelPojo;

import java.util.List;

public class LoadMaker implements Runnable {
    private int ChosenLevel;
    private LoadHandler loadHandler;
    private ConnectionUI connectionUI;
    private List<LevelPojo> readableLevelList;


    public LoadMaker(int chosenLevel , ConnectionUI connectionUI , List<LevelPojo> readableLevelList) {
        this.ChosenLevel = chosenLevel;
        this.connectionUI = connectionUI;
        loadHandler = new LoadHandler(connectionUI);
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
