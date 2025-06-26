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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class Constants {

    private static final Constants INSTANCE;

    static {
        try {
            INSTANCE = new Constants();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  int level = 0;
    private  List<Timeline> stopTimelines = new ArrayList<>();
    private  BooleanProperty stopped = new SimpleBooleanProperty(false);
    private  boolean cancellingWaveForTenSeconds = false;
    private  boolean cancellingCollision = false;
    private  BooleanProperty makeEveryPocketNoiseZero = new SimpleBooleanProperty(false);
    private   int numberOfPockets;
    private  levelsManager levelManagers = new levelsManager();
    private  BooleanProperty CouldWeUseGameOver = new SimpleBooleanProperty(false);
    private  List<level> levels = (ArrayList<level>) levelManagers.getLevels();
    private  List<Timeline> timeLines = new ArrayList<>();
    private  List<Pocket> pockets;
    private  Map<Node, GatePortInfo> portInfo;
    private  List<Connection> connections;
    private  Stage primaryStage;
    private  Gateee gateConnectorManager;
    private  Map<GatePortInfo, Connection> exitConnections;
    private  WireManager wireManager;
    private  double availableTime = 5000;


    private Constants() throws IOException {
        stopped.addListener((obs, wasStopped, isStopped) -> {
            synchronized (stopTimelines) {
                for (Timeline tl : stopTimelines) {
                    if (isStopped) tl.pause();
                    else tl.play();
                }
            }
        });


        try {
            this.levelManagers = new levelsManager();
            this.levels = (ArrayList<level>) levelManagers.getLevels();
        } catch (IOException e) {

            throw new RuntimeException("Failed to initialize levelsManager", e);
        }
    }

    public static Constants getInstance() {
        return INSTANCE;
    }



    public WireManager getWireManager() {
        return wireManager;
    }

    public void setWireManager(WireManager wireManage) {
        this.wireManager = wireManage;
    }

    public void setPrimaryStage(Stage primarystage) {
        this.primaryStage = primarystage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Gateee getGateConnectorManager() {
        return gateConnectorManager;
    }

    public void setGateConnectorManager(Gateee gateConnectorManager) {
        this.gateConnectorManager = gateConnectorManager;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connectionsInput) {
        this.connections = connectionsInput;
    }

    public Map<Node, GatePortInfo> getPortInfo() {
        return portInfo;
    }

    public void setPortInfo(Map<Node, GatePortInfo> Info) {
        this.portInfo = Info;
    }

    public Map<GatePortInfo, Connection> getExitConnections() {
        return exitConnections;
    }

    public void setExitConnections(Map<GatePortInfo, Connection> exitConnections) {
        this.exitConnections = exitConnections;
    }

    public List<Pocket> getPockets() {
        return pockets;
    }

    public void setPockets(ArrayList<Pocket> pockets) {
        this.pockets = pockets;
    }

    public double getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(double availableTime) {
        this.availableTime = availableTime;
    }

    public List<Timeline> getTimeLines() {
        return timeLines;
    }

    public void addTimeLine(Timeline timeline) {
        timeLines.add(timeline);
    }

    public void clearTimeLines() {
        timeLines.clear();
    }

    public void setTimeLines(ArrayList<Timeline> timeLines) {
        this.timeLines.clear();
        this.timeLines.addAll(timeLines);
    }

    public List<level> getLevels() {
        return levels;
    }

    public int getNumberOfPockets() {
        return numberOfPockets;
    }

    public void setNumberOfPockets(int numberOfPockets) {
        this.numberOfPockets = numberOfPockets;
    }

    public boolean isCancellingWaveForTenSeconds() {
        return cancellingWaveForTenSeconds;
    }

    public void setCancellingWaveForTenSeconds(boolean cancellingWaveForTenSeconds) {
        this.cancellingWaveForTenSeconds = cancellingWaveForTenSeconds;
    }

    public boolean isCancellingCollision() {
        return cancellingCollision;
    }

    public void setCancellingCollision(boolean cancellingCollision) {
        this.cancellingCollision = cancellingCollision;
    }

    public boolean isMakeEveryPocketNoiseZero() {
        return makeEveryPocketNoiseZero.get();
    }

    public BooleanProperty makeEveryPocketNoiseZeroProperty() {
        return makeEveryPocketNoiseZero;
    }

    public void setMakeEveryPocketNoiseZero(boolean b) {
        makeEveryPocketNoiseZero.set(b);
    }

    public boolean isStopped() {
        return stopped.get();
    }

    public BooleanProperty stoppedProperty() {
        return stopped;
    }

    public void setStopped(boolean stop) {
        stopped.set(stop);
    }

    public List<Timeline> getStopTimelines() {
        return stopTimelines;
    }

    public void addStopTimelines(Timeline timeline) {
        this.stopTimelines.add(timeline);
        if (stopped.get()) {
            timeline.pause();
        }
    }

    public void removeStopTimelines(Timeline timeline) {
        stopTimelines.remove(timeline);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLevels(ArrayList<level> levels) {
        this.levels = levels;
    }

    public boolean isCouldWeUseGameOver() {
        return CouldWeUseGameOver.get();
    }

    public BooleanProperty couldWeUseGameOverProperty() {
        return CouldWeUseGameOver;
    }

    public void setCouldWeUseGameOver(boolean couldWeUseGameOver) {
        CouldWeUseGameOver.set(couldWeUseGameOver);
    }
}