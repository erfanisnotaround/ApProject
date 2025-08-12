package org.example.phaze2.model.saversOfGame;

import org.example.phaze2.model.agentsAndManagers.JsonManager;
import org.example.phaze2.model.levelSavesAndTheirPojo.LevelPojo;

import java.util.List;

public class SaveMaker extends Thread {
    private JsonManager jsonManager;
    private final SaveHandler saveHandler;
    private volatile boolean running = true;
    private final long saveIntervalMillis = 5000;
    private int currentLevel;
    private List<LevelPojo> ReallevelPojoList;

    public SaveMaker(int currentLevel , List<LevelPojo> RealLevelsLoaded , LoadAndSaveCompleterNecessaries loadAndSaveCompleterNecessaries) {
        this.ReallevelPojoList = RealLevelsLoaded;
        this.saveHandler = new SaveHandler(loadAndSaveCompleterNecessaries);
        this.currentLevel = currentLevel;
        setDaemon(true);
        jsonManager = new JsonManager("D:\\programming\\project of Ap\\faz 1\\Phazes\\phaze2\\phaze2\\src\\main\\resources\\org\\example\\phaze2\\jsonFiles\\levelSaves.json");
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

    }



    public void stopSaving() {
        running = false;
        this.interrupt();


    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
