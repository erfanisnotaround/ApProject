package org.example.phaze2.model.constants;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    private static final Constants INSTANCE = new Constants();

    private Stage primaryStage;
    private SceneManager sceneManager;
    private WireManager wireManager;
    private List<SystemView> systemViews = new ArrayList<>();
    private List<Pocket> pockets = new ArrayList<>();
    private Map<PortInfo, Connection> exitConnections = new HashMap<>();
    private Map<Node, PortInfo> portInfo = new HashMap<>();
    private List<Connection> connections = new ArrayList<>();
    private List<Pocket> constantsPockets = new ArrayList<>();
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


    public List<Pocket> getPockets() {
        return pockets;
    }



    public Map<PortInfo, Connection> getExitConnections() {
        return exitConnections;
    }


    public Map<Node, PortInfo> getPortInfo() {
        return portInfo;
    }



    public List<Connection> getConnections() {
        return connections;
    }

    public List<Pocket> getConnectedPockets() {
        return constantsPockets;
    }

    public void setConnectedPockets(List<Pocket> connectedPockets) {
        this.constantsPockets = connectedPockets;
    }
}
