package org.example.phaze2.model.constants;

import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Resources {

    private final List<SystemView> systemViews = new ArrayList<>();
    private final List<PocketMain> pockets = new CopyOnWriteArrayList<>();
    private final Map<Port, Connection> exitConnections = new HashMap<>();
    private final List<Connection> connections = new ArrayList<>();
    private final Map<String , PocketMain> pocketMainMap = new HashMap<>();
    private final Map<String , SystemView> systemViewMap = new HashMap<>();
    private List<PocketMain> baselinePocketSeeds = new ArrayList<>();
    private final Map<String , Set<PocketMain>> pocketGroupIdGroups = new HashMap<>();

    private WireManager wireManager ;

    public Map<String, SystemView> getSystemViewMap() {
        return systemViewMap;
    }


    public Map<String, PocketMain> getPocketMainMap() {
        return pocketMainMap;
    }


    public List<Connection> getConnections() {
        return connections;
    }


    public Map<Port, Connection> getExitConnections() {
        return exitConnections;
    }


    public List<PocketMain> getPockets() {
        return pockets;
    }

    public List<SystemView> getSystemViews() {
        return systemViews;
    }


    public List<PocketMain> getBaselinePocketSeeds() { return baselinePocketSeeds; }
    public void setBaselinePocketSeeds(List<PocketMain> seeds) { this.baselinePocketSeeds = seeds; }

    public WireManager getWireManager() {
        return wireManager;
    }

    public void setWireManager(WireManager wireManager) {
        this.wireManager = wireManager;
    }

    public Map<String, Set<PocketMain>> getPocketGroupIdGroups() {
        return pocketGroupIdGroups;
    }
    public void putPocketGroupIdGroup(String pocketGroupId , PocketMain pocketMain) {
        if (pocketGroupIdGroups.containsKey(pocketGroupId)) pocketGroupIdGroups.get(pocketGroupId).add(pocketMain);
        else {
            Set<PocketMain> pocketMainSet = new HashSet<>();
            pocketMainSet.add(pocketMain);
            pocketGroupIdGroups.put(pocketGroupId , pocketMainSet);
        }
    }
    public void removePocketGroupIdGroup(String pocketGroupId , PocketMain pocketMain) {
        pocketGroupIdGroups.get(pocketGroupId).remove(pocketMain);
    }
}
