package org.example.phaze2.model.constants;

import org.example.phaze2.model.hudModels.AbilityAliveManager;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Resources {

    private List<SystemView> systemViews = new ArrayList<>();
    private final List<PocketMain> pockets = new CopyOnWriteArrayList<>();
    private Map<Port, Connection> exitConnections = new HashMap<>();
    private List<Connection> connections = new ArrayList<>();
    private Map<String , PocketMain> pocketMainMap = new HashMap<>();
    private Map<String , SystemView> systemViewMap = new HashMap<>();
    private List<PocketMain> FirstOriginalPockets;

    public Map<String, SystemView> getSystemViewMap() {
        return systemViewMap;
    }

    public void setSystemViewMap(Map<String, SystemView> systemViewMap) {
        this.systemViewMap = systemViewMap;
    }

    public Map<String, PocketMain> getPocketMainMap() {
        return pocketMainMap;
    }

    public void setPocketMainMap(Map<String, PocketMain> pocketMainMap) {
        this.pocketMainMap = pocketMainMap;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public Map<Port, Connection> getExitConnections() {
        return exitConnections;
    }

    public void setExitConnections(Map<Port, Connection> exitConnections) {
        this.exitConnections = exitConnections;
    }

    public List<PocketMain> getPockets() {
        return pockets;
    }

    public List<SystemView> getSystemViews() {
        return systemViews;
    }

    public void setSystemViews(List<SystemView> systemViews) {
        this.systemViews = systemViews;
    }

    public List<PocketMain> getFirstOriginalPockets() {
        return FirstOriginalPockets;
    }

    public void setFirstOriginalPockets(List<PocketMain> firstOriginalPockets) {
        FirstOriginalPockets = firstOriginalPockets;
    }
}
