package org.example.phaze2.controllers.moverController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SubSystemView;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WholeMovement {
    Constants constants = Constants.getInstance();

    // resources
    private volatile List<Connection> connections;
    private volatile Map<Node , PortInfo> portInfoMap;
    private volatile Map<PortInfo , Connection> exitConnections;
    private volatile List<PocketMain> pockets;
    private volatile List<SystemView> systemViews;

    //checkers and workers har har

    StartAvailableChecker startAvailableChecker;



    SystemView startingSystemView;
    private final Map<Connection, ChangeListener<Boolean>> waitingSendListeners = new HashMap<>();
    private final Map<PocketMain, ChangeListener<Boolean>> pocketListeners = new HashMap<>();
    private final Map<SystemView, ChangeListener<Boolean>> SystemListeners = new HashMap<>();




    public WholeMovement() {
        connections = constants.getConnections();
        portInfoMap = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        pockets = constants.getPockets();
        systemViews = constants.getSystemViews();
        startAvailableChecker = new StartAvailableChecker(systemViews);

    }
    public void StartSending(){
        System.out.println("StartSending");
        Reset();

        getStartSystemView();



        for (PocketMain pocket : pockets) {
            pocket.setMovementManager(this);
            SendingPockets(startingSystemView , pocket);
        }

        for (SystemView systemView : systemViews) {
            MakeSystemConnectionsWaiting(systemView);
        }

    }




    public void SendingPockets(SystemView systemView , PocketMain pocket){
        Connection exitConnection = pocket.ReleaseAct(pocket, systemView, portInfoMap, exitConnections);
        if (exitConnection != null) {
            resumeMovement(pocket , exitConnection);
            return;
        }

//        System.out.println(pocket.getType());


        AddToWaitingSystemCapacity(systemView , pocket);



    }
    public void MakeSystemConnectionsWaiting(SystemView systemView){
        for (SubSystemView subSystemView : systemView.getSubSystems()) {
            if (subSystemView.DoesItHavaExitGate()){
                PortInfo portInfo = portInfoMap.get(subSystemView.getExitPort());
                Connection exitConnection = exitConnections.get(portInfo);


                ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> {
                    if (!CanWeSendPocketOnThisConnection(exitConnection)) {
                        AddToWaitingSend(systemView, exitConnection);
                    }
                };

                waitingSendListeners.put(exitConnection, listener);
                exitConnection.getCurve().isItUsedProperty().addListener(listener);

                if (!SystemListeners.containsKey(systemView)) {
                    SystemListeners.put(systemView, listener);
                    systemView.isItDownProperty().addListener(listener);
                }

            }


        }
    }
    private boolean CanWeSendPocketOnThisConnection(Connection connection){

        return connection.getCurve().isIsItUsed() && connection.getFrom().getSystem().isItDown();
    }

    public void AddToWaitingSend(SystemView systemView , Connection connection){
        for (int i = 0 ; i < systemView.getCapacity().length ; i++) {
            if (systemView.getCapacity()[i] != null){
                PocketMain pocket = systemView.getCapacity()[i];
                systemView.getCapacity()[i] = null;
                SendingPockets(systemView , pocket);

            }
        }
    }
    private boolean itThere(PocketMain pocket , SystemView systemView ){
        for (PocketMain pocket1 : systemView.getCapacity()) {
            if (pocket1 == null) continue;
            if (pocket1.equals(pocket)) return true;
        }
        return false;
    }
    public void resumeMovement(PocketMain pocket , Connection connection){

        ChangeListener<Boolean> l = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs,
                                Boolean oldVal, Boolean newVal) {

                if (!newVal) {
                    obs.removeListener(this);
                    SendingPockets(connection.getTo().getSystem(), pocket);
                }
            }
        };
        pocketListeners.put(pocket, l);
        pocket.isItMovedProperty().addListener(l);
    }
    public void AddToWaitingSystemCapacity(SystemView systemView , PocketMain pocket){
        if (itThere(pocket , systemView)) {
            return;
        };
        for (int i = systemView.getCapacity().length - 1 ; i >= 0 ; i--){
            if (systemView.getCapacity()[i] == null){
                systemView.getCapacity()[i] = pocket;
                break;
            }
        }
    }

    public void getStartSystemView(){
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem()) startingSystemView = systemView;
        }
    }

    private void Reset(){

        for (Map.Entry<Connection, ChangeListener<Boolean>> entry : waitingSendListeners.entrySet()) {
            Connection connection = entry.getKey();
            ChangeListener<Boolean> listener = entry.getValue();
            connection.getCurve().isItUsedProperty().removeListener(listener);
        }
        waitingSendListeners.clear();

        for (Map.Entry<PocketMain, ChangeListener<Boolean>> entry : pocketListeners.entrySet()) {
            Pocket pocket = entry.getKey();
            ChangeListener<Boolean> listener = entry.getValue();
            pocket.isItMovedProperty().removeListener(listener);
        }
        pocketListeners.clear();


        for (Map.Entry<SystemView, ChangeListener<Boolean>> entry : SystemListeners.entrySet()) {
            SystemView systemView = entry.getKey();
            ChangeListener<Boolean> listener = entry.getValue();
            systemView.isItDownProperty().removeListener(listener);
        }
        SystemListeners.clear();

        for (Connection connection : connections) {
            connection.getCurve().setIsItUsed(false);
        }
        for (SystemView systemView : systemViews) {
            systemView.setIsItDown(false);
            Arrays.fill(systemView.getCapacity(), null);
        }
        for (PocketMain pocket : pockets) {
//            PocketSwitchManager.reInitialize(pocket);
            pocket.getPathMover().stop();
            pocket.setIsItMoved(false);
            pocket.setIsItCollided(false);
            pocket.setHP(pocket.getMaxHp());

            pocket.setLayoutX(500);
            pocket.setLayoutY(500);
            pocket.setItAffected(false);


            pocket.setBehaviour(pocket.getFirstPocketType());
            pocket.setWhichSystemViewThisPocketIsAffectedBy(null);
        }
    }

}
