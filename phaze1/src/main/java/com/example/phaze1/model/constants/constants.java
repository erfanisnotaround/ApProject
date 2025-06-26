package com.example.phaze1.model.constants;
import com.example.phaze1.model.levelLoadingStuff.levelsManager;
import com.example.phaze1.model.levelLoadingStuff.level;
import com.example.phaze1.model.formerVersionOSystems.Gateee;
import com.example.phaze1.model.systemsInfoAndManagers.Connection;
import com.example.phaze1.model.systemsInfoAndManagers.GatePortInfo;
import com.example.phaze1.model.systemsInfoAndManagers.Pocket;
import com.example.phaze1.model.systemsInfoAndManagers.WireManager;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class constants {
    private static int level = 0;
    private static ArrayList<Timeline> stopTimelines = new ArrayList<>();
    private static BooleanProperty stopped = new SimpleBooleanProperty(false);
    static {
        stopped.addListener((obs, wasStopped, isStopped) -> {
            for (Timeline tl : stopTimelines) {
                if (isStopped)   tl.pause();
                else             tl.play();
            }
        });
    }
    private static boolean cancellingWaveForTenSeconds = false;
    private static boolean cancellingCollision = false;
    private static BooleanProperty makeEveryPocketNoiseZero = new SimpleBooleanProperty(false);
    private static  int numberOfPockets;
    private static levelsManager levelManagers;
    static {
        try {
            levelManagers = new levelsManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static BooleanProperty CouldWeUseGameOver = new SimpleBooleanProperty(false);
    private static ArrayList<level> levels = (ArrayList<level>) levelManagers.getLevels();
    private static ArrayList<Timeline> timeLines = new ArrayList<>();
    private static ArrayList<Pocket> Pockets;
    private static Map<Node, GatePortInfo> portInfo;
    private static List<Connection> connections;
    private static Stage PrimaryStage;
    private static Gateee GateConnectorManager;
    private static Map<GatePortInfo, Connection> exitConnections;
    private static WireManager wireManager;
    private static double availableTime = 5000;

    public constants() throws IOException {
    }

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

    public static ArrayList<Timeline> getTimeLines() {
        return timeLines;
    }
    public static void addTimeLine(Timeline timeline) {
        timeLines.add(timeline);
    }
    public static void ClearTimeLines() {
        timeLines.clear();
    }
    public static void setTimeLines(ArrayList<Timeline> timeLines) {
        constants.timeLines = timeLines;
    }

    public static ArrayList<level> getLevels() {
        return levels;
    }

    public static int getNumberOfPockets() {
        return numberOfPockets;
    }

    public static void setNumberOfPockets(int numberOfPockets) {
        constants.numberOfPockets = numberOfPockets;
    }

    public static boolean isCancellingWaveForTenSeconds() {
        return cancellingWaveForTenSeconds;
    }

    public static void setCancellingWaveForTenSeconds(boolean cancellingWaveForTenSeconds) {
        constants.cancellingWaveForTenSeconds = cancellingWaveForTenSeconds;
    }

    public static boolean isCancellingCollision() {
        return cancellingCollision;
    }

    public static void setCancellingCollision(boolean cancellingCollision) {
        constants.cancellingCollision = cancellingCollision;
    }

    public static boolean isMakeEveryPocketNoiseZero() {
        return makeEveryPocketNoiseZero.get();
    }

    public static BooleanProperty makeEveryPocketNoiseZeroProperty() {
        return makeEveryPocketNoiseZero;
    }

    public static void setMakeEveryPocketNoiseZero(boolean b) {
        makeEveryPocketNoiseZero.set(b);
    }

    public static boolean isStopped() {
        return stopped.get();
    }

    public static BooleanProperty stoppedProperty() {
        return stopped;
    }

    public static void setStopped(boolean stop) {
        stopped.set(stop);
    }

    public static ArrayList<Timeline> getStopTimelines() {
        return stopTimelines;
    }

    public static void setStopTimelines(ArrayList<Timeline> stopTimelines) {
        constants.stopTimelines = stopTimelines;
    }
    public static void addStopTimelines(Timeline timeline) {
        constants.stopTimelines.add(timeline);
        if (stopped.get()){
            timeline.pause();
        }
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        constants.level = level;
    }

    public void setLevels(ArrayList<level> level) {
        levels = level;
    }

    public static boolean isCouldWeUseGameOver() {
        return CouldWeUseGameOver.get();
    }

    public static BooleanProperty couldWeUseGameOverProperty() {
        return CouldWeUseGameOver;
    }

    public static void setCouldWeUseGameOver(boolean couldWeUseGameOver) {
        CouldWeUseGameOver.set(couldWeUseGameOver);
    }
    public static void removeStopTimelines(Timeline timeline) {
        stopTimelines.remove(timeline);
    }
}