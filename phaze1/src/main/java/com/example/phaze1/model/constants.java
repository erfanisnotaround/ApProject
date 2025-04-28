package com.example.phaze1.model;

import com.example.phaze1.model.FormerVersionOSystems.Gateee;
import com.example.phaze1.model.FormerVersionOSystems.connection;
import com.example.phaze1.model.SystemsInfo.Connection;
import com.example.phaze1.model.SystemsInfo.GatePortInfo;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class constants {
    private static Map<Node, GatePortInfo> portInfo;
    private static List<Connection> connections;
    private static Stage PrimaryStage;
    private static Gateee GateConnectorManager;
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

    public static Gateee getGateConnectorManager() {
        return GateConnectorManager;
    }

    public static void setGateConnectorManager(Gateee gateConnectorManager) {
        GateConnectorManager = gateConnectorManager;
    }

    public static List<Connection> getConnections() {
        return connections;
    }

    public static void setConnections(List<Connection> connectionsInput) {
        connections = connectionsInput;
    }

    public static Map<Node, GatePortInfo> getPortInfo() {
        return portInfo;
    }

    public static void setPortInfo(Map<Node, GatePortInfo> Info) {
        portInfo = Info;
    }
}
