// src/main/java/org/example/phaze2/controllers/moverController/moveRelated/WholeMovement.java
package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.*;
import org.example.phaze2.controllers.moverController.checkings.StartAvailableChecker;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.ResetService;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SubSystemView;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.*;

public class WholeMovement {
    private final Constants constants = Constants.getInstance();
    private final CoinsManager coinsManager;
    private final GameState gameState;
    private final List<PocketMain> originalSeeds = new ArrayList<>();
    private final MovementListenerRegistry listeners = new MovementListenerRegistry();

    // resources (volatile refs to lists in GameState resources)
    private volatile List<Connection> connections;
    private volatile Map<Port, Connection> exitConnections;
    private volatile List<PocketMain> pockets;
    private volatile List<SystemView> systemViews;

    private double speedMultiplier = 1;
    private final StartAvailableChecker startAvailableChecker;
    private SystemView startingSystemView;

    // NEW: injected resetter
    private ResetService resetter;

    public WholeMovement(CoinsManager coinsManager, GameState gameState) {
        this.coinsManager = coinsManager;
        this.gameState = gameState;

        this.connections     = gameState.getResources().getConnections();
        this.exitConnections = gameState.getResources().getExitConnections();
        this.pockets         = gameState.getResources().getPockets();
        this.systemViews     = gameState.getResources().getSystemViews();

        this.startAvailableChecker = new StartAvailableChecker(systemViews, connections);
        getStartSystemView();
    }

    public void setResetter(ResetService resetter) { this.resetter = resetter; }

    public void StartSending(double speedMultiplier, double availableTime) {
        // if (!startAvailableChecker.canWeStartConnections()) return; // keep if you want gating
        this.speedMultiplier = speedMultiplier;

        // ✨ delegate resets to ResetService
        if (resetter == null) throw new IllegalStateException("ResetService not set");
        resetter.resetMovementListeners(listeners);
        resetter.resetConnectionsAndSystems();

        getStartSystemView();

        for (PocketMain pocket : pockets) {
            pocket.setAvailableTime(availableTime);
            pocket.setMovementManager(this);
            SendingPockets(startingSystemView, pocket, -1);
        }

        PutListenersForSystems();
    }

    public void PutListenersForSystems() {
        for (SystemView systemView : systemViews) {
            MakeSystemConnectionsWaiting(systemView);
        }
    }

    public void SendingPockets(SystemView systemView, PocketMain pocket, int fromSystem) {
        Connection exitConnection = pocket.ReleaseAct(pocket, systemView, exitConnections, speedMultiplier, null);

        if (exitConnection != null) {
            if (fromSystem != -1) systemView.getCapacity()[fromSystem] = null;
            resumeMovement(pocket, exitConnection);
            return;
        }
        AddToWaitingSystemCapacity(systemView, pocket);
    }

    public void MakeSystemConnectionsWaiting(SystemView systemView) {
        for (SubSystemView subSystemView : systemView.getSubSystems()) {
            if (!subSystemView.DoesItHavaExitGate()) continue;

            Connection exitConnection = exitConnections.get(subSystemView.getExitPort());
            if (exitConnection == null) continue;

            ChangeListener<Boolean> listener = (obs, oldV, newV) -> {
                if (!CanWeSendPocketOnThisConnection(exitConnection)) {
                    AddToWaitingSend(systemView, exitConnection);
                }
            };

            listeners.registerWaiting(exitConnection, listener);
            exitConnection.getCurve().isItUsedProperty().addListener(listener);

            if (!listeners.isSystemRegistered(systemView)) {
                listeners.registerSystem(systemView, listener);
                systemView.isItDownProperty().addListener(listener);
            }
        }
    }

    private boolean CanWeSendPocketOnThisConnection(Connection connection) {
        return connection.getCurve().isIsItUsed() && connection.getFromPort().getPortInfo().getSystem().isItDown();
    }

    public void AddToWaitingSend(SystemView systemView, Connection connection) {
        for (int i = 0; i < systemView.getCapacity().length; i++) {
            if (systemView.getCapacity()[i] != null) {
                PocketMain pocket = systemView.getCapacity()[i];
                SendingPockets(systemView, pocket, i);
            }
        }
    }

    private boolean itThere(PocketMain pocket, SystemView systemView) {
        for (PocketMain pocket1 : systemView.getCapacity()) {
            if (pocket1 == null) continue;
            if (pocket1.equals(pocket)) return true;
        }
        return false;
    }

    public void resumeMovement(PocketMain pocket, Connection connection) {
        if (IsItBig(pocket)) {
            connection.ChangePorts(PortTypes.TRIANGLE);
            connection.getCurve().setHP(connection.getCurve().getHP() - 1);
        }

        SystemView targetSystem = connection.getToPort().getPortInfo().getSystem();
        if (targetSystem.equals(startingSystemView)) pocket.setLastRound(true);

        ChangeListener<Boolean> l = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
                pocket.EnterAct(targetSystem);
                obs.removeListener(this);

                coinsManager.Increment(pocket.getCoinsPerEntry());
                if (pocket.isLastRound()) {
                    pocket.setDone(true);
                    return;
                }

                targetSystem.EnterBehave(pocket, speedMultiplier);

                if (pocket.isPocketIsLostByDisterbute() || pocket.isCapturedByMerger()) {
                    AddToWaitingSend(targetSystem, connection);
                } else {
                    SendingPockets(targetSystem, pocket, -1);
                }
            }
        };
        listeners.registerPocket(pocket, l);
        pocket.isItMovedProperty().addListener(l);
    }

    public void AddToWaitingSystemCapacity(SystemView systemView, PocketMain pocket) {
        if (itThere(pocket, systemView)) return;

        for (int i = 0; i < systemView.getCapacity().length; i++) {
            if (systemView.getCapacity()[i] == null) {
                systemView.getCapacity()[i] = pocket;
                return;
            }
        }
        pocket.setHP(0);
    }

    private boolean IsItBig(PocketMain pocket) {
        return pocket.getType() == PocketTypes.BIG_1 || pocket.getType() == PocketTypes.BIG_2;
    }

    public void getStartSystemView() {
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem()) startingSystemView = systemView;
        }
    }

    public void captureOriginalSnapshotFromSeeds(List<PocketMain> seeds) {
        originalSeeds.clear();
        originalSeeds.addAll(new ArrayList<>(seeds));
    }
    public MovementListenerRegistry getMovementListeners() {
        return listeners;
    }
}
