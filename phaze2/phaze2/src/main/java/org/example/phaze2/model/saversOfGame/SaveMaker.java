package org.example.phaze2.model.saversOfGame;

import org.example.phaze2.model.agentsAndManagers.JsonManager;
import org.example.phaze2.model.levelSavesAndTheirPojo.LevelPojo;

import java.io.IOException;
import java.util.List;

public class SaveMaker extends Thread {
    private JsonManager jsonManager;
    private final SaveHandler saveHandler;
    private volatile boolean running = true;
    private final long saveIntervalMillis = 5000;
    private int currentLevel;
    private List<LevelPojo> ReallevelPojoList;

    public SaveMaker(int currentLevel , List<LevelPojo> RealLevelsLoaded , LoadAndSaveCompleterNecessaries loadAndSaveCompleterNecessaries , JsonManager jsonManager) {
        this.ReallevelPojoList = RealLevelsLoaded;
        this.saveHandler = new SaveHandler(loadAndSaveCompleterNecessaries);
        this.currentLevel = currentLevel;
        setDaemon(true);
        this.jsonManager = jsonManager;
    }

    @Override
    public void run() {
        while (running) {
            try {

                SaveMaking();

                Thread.sleep(saveIntervalMillis);
            } catch (InterruptedException e) {
                System.out.println("SaveMaker interrupted. Exiting save loop.");
                running = false;
            } catch (Exception e) {
                System.err.println("SaveMaker encountered an error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    private void SaveMaking(){
        LevelPojo SavedLevelPojo = saveHandler.StartSave();
        ReallevelPojoList.set(currentLevel, SavedLevelPojo);
        writeLevelsToDisk(ReallevelPojoList);

    }

    public void writeLevelsToDisk(List<LevelPojo> LevelPojoList) {
        try {
            jsonManager.writeArray(LevelPojoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopSaving() {
        running = false;
        this.interrupt();


    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
