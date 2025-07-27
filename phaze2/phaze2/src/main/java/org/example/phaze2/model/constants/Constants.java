package org.example.phaze2.model.constants;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    private static final Constants INSTANCE = new Constants();

    public Pane container;
    private Stage primaryStage;
    private SceneManager sceneManager;
    private WireManager wireManager;
    private List<SystemView> systemViews = new ArrayList<>();
    private List<PocketMain> pockets = new ArrayList<>();
    private Map<Port, Connection> exitConnections = new HashMap<>();
    private List<Connection> connections = new ArrayList<>();
    private Map<String , PocketMain> pocketMainMap = new HashMap<>();
    private Map<String , SystemView> systemViewMap = new HashMap<>();

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


    public List<PocketMain> getPockets() {
        return pockets;
    }



    public Map<Port, Connection> getExitConnections() {
        return exitConnections;
    }




    public List<Connection> getConnections() {
        return connections;
    }

    public Map<String, PocketMain> getPocketMainMap() {
        return pocketMainMap;
    }

    public void setPocketMainMap(Map<String, PocketMain> pocketMainMap) {
        this.pocketMainMap = pocketMainMap;
    }

    public Map<String, SystemView> getSystemViewMap() {
        return systemViewMap;
    }

    public void setSystemViewMap(Map<String, SystemView> systemViewMap) {
        this.systemViewMap = systemViewMap;
    }
}
