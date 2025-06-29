package org.example.phaze2.model.constants;

import javafx.stage.Stage;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;

import java.util.List;

public class Constants {
    private static final Constants INSTANCE = new Constants();

    private  Stage primaryStage;
    private SceneManager sceneManager;
    private WireManager wireManager;
    private List<SystemView> systemViews;
    private List<Pocket> pockets;
    private Constants() {}
    public static Constants getInstance() {
        return INSTANCE;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public WireManager getWireManager() {
        return wireManager;
    }

    public void setWireManager(WireManager wireManager) {
        this.wireManager = wireManager;
    }

    public List<SystemView> getSystemViews() {
        return systemViews;
    }

    public void setSystemViews(List<SystemView> systemViews) {
        this.systemViews = systemViews;
    }

    public List<Pocket> getPockets() {
        return pockets;
    }

    public void setPockets(List<Pocket> pockets) {
        this.pockets = pockets;
    }
}
