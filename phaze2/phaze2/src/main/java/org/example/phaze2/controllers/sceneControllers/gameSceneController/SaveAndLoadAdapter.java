package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.PersistencePort;
import org.example.phaze2.model.saversOfGame.SaveAndLoadController;

public final class SaveAndLoadAdapter implements PersistencePort {
    private final SaveAndLoadController slc;
    public SaveAndLoadAdapter(SaveAndLoadController slc) { this.slc = slc; }
    @Override public void startAutoSave() { slc.startAutoSave(); }
    @Override public void saveNow() { slc.writeLevelsToDisk(); }
}
