package org.example.phaze2.model.constants;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    private static final Constants INSTANCE = new Constants();

    private Stage primaryStage;
    private SceneManager sceneManager;
    private WireManager wireManager;
    private List<SystemView> systemViews;
    private List<Pocket> pockets;
    private Map<PortInfo, Connection> exitConnections;
    private Map<Node, PortInfo> portInfo;
    private List<Connection> connections;
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

    public Map<PortInfo, Connection> getExitConnections() {
        return exitConnections;
    }

    public void setExitConnections(Map<PortInfo, Connection> exitConnections) {
        this.exitConnections = exitConnections;
    }

    public Map<Node, PortInfo> getPortInfo() {
        return portInfo;
    }

    public void setPortInfo(Map<Node, PortInfo> portInfo) {
        this.portInfo = portInfo;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
