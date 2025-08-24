package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.beans.value.ChangeListener;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.HashMap;
import java.util.Map;

public final class MovementListenerRegistry {
    private final Map<Connection, ChangeListener<Boolean>> waitingSendListeners = new HashMap<>();
    private final Map<PocketMain, ChangeListener<Boolean>>  pocketListeners     = new HashMap<>();
    private final Map<SystemView, ChangeListener<Boolean>>  systemListeners     = new HashMap<>();

    public void registerWaiting(Connection c, ChangeListener<Boolean> l) { waitingSendListeners.put(c, l); }
    public void registerPocket(PocketMain p, ChangeListener<Boolean> l)  { pocketListeners.put(p, l); }
    public void registerSystem(SystemView s, ChangeListener<Boolean> l)  { systemListeners.put(s, l); }
    public void removePocketListener(PocketMain p, ChangeListener<Boolean> l) { pocketListeners.remove(p); }
    public boolean PocketListenerContains(PocketMain p) {

        return pocketListeners.containsKey(p);
    }

    public boolean isSystemRegistered(SystemView s) { return systemListeners.containsKey(s); }

    public Map<Connection, ChangeListener<Boolean>> waitingSend() { return waitingSendListeners; }
    public Map<PocketMain, ChangeListener<Boolean>>  pockets()     { return pocketListeners; }
    public Map<SystemView, ChangeListener<Boolean>>  systems()     { return systemListeners; }

    public void clear() {
        waitingSendListeners.clear();
        pocketListeners.clear();
        systemListeners.clear();
    }
}