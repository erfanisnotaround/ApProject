package com.example.phaze1.Model;

import com.example.phaze1.Model.FormerVersionOSystems.Gateee;
import com.example.phaze1.Model.SystemsInfo.Connection;
import com.example.phaze1.Model.SystemsInfo.GatePortInfo;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class constants {
    private static Map<Node, GatePortInfo> portInfo;
    private static List<Connection> connections;
    private static Stage PrimaryStage;
    private static Gateee GateConnectorManager;
    private static Map<GatePortInfo, Connection> exitConnections;
    private static WireManager wireManager ;
    public static WireManager getWireManager() {
        if (wireManager == null) {
            System.out.println("l=nfva");
        }
        return wireManager;
    }
    public static void setWireManager(WireManager wireManage) {
        wireManager = wireManage;

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

    public static Map<GatePortInfo, Connection> getExitConnections() {
        return exitConnections;
    }

    public static void setExitConnections(Map<GatePortInfo, Connection> exitConnections) {
        constants.exitConnections = exitConnections;
    }
}
