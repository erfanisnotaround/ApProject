package com.example.phaze1.model;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Stack;

public class constants {
    private static List<connection> connections;
    private static Stage PrimaryStage;
    private static GateConnectorManager GateConnectorManager;
    private static WireManager wireManager = new WireManager(700);
    public static WireManager getWireManager() {
        return wireManager;
    }
    public static void setWireManager(WireManager wireManager) {
        constants.wireManager = wireManager;
    }
    public static  void setPrimaryStage(Stage primarystage) {
        PrimaryStage = primarystage;
    }
    public static Stage getPrimaryStage() {
        return PrimaryStage;
    }

    public static GateConnectorManager getGateConnectorManager() {
        return GateConnectorManager;
    }

    public static void setGateConnectorManager(GateConnectorManager gateConnectorManager) {
        GateConnectorManager = gateConnectorManager;
    }

    public static List<connection> getConnections() {
        return connections;
    }

    public static void setConnections(List<connection> connectionsInput) {
        connections = connectionsInput;
    }
}
