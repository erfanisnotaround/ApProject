package com.example.phaze1.Model.Constants;

import com.example.phaze1.Model.FormerVersionOSystems.Gateee;
import com.example.phaze1.Model.SystemsInfoAndManagers.Connection;
import com.example.phaze1.Model.SystemsInfoAndManagers.GatePortInfo;
import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.SystemsInfoAndManagers.WireManager;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class constants {
    private static ArrayList<Pocket> Pockets;
    private static Map<Node, GatePortInfo> portInfo;
    private static List<Connection> connections;
    private static Stage PrimaryStage;
    private static Gateee GateConnectorManager;
    private static Map<GatePortInfo, Connection> exitConnections;
    private static WireManager wireManager ;
    private static double availableTime = 120;
    private Timeline reducingAvailableTime;
    public static WireManager getWireManager() {
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

    public static ArrayList<Pocket> getPockets() {
        return Pockets;
    }

    public static void setPockets(ArrayList<Pocket> pockets) {
        Pockets = pockets;
    }

    public static double getAvailableTime() {
        return availableTime;
    }

    public static void setAvailableTime(double availableTime) {
        constants.availableTime = availableTime;
    }

    public Timeline getReducingAvailableTime() {
        return reducingAvailableTime;
    }

    public void setReducingAvailableTime(Timeline reducingAvailableTime) {
        this.reducingAvailableTime = reducingAvailableTime;
    }
}
