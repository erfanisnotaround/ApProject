package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.example.phaze2.controllers.moverController.checkings.StartAvailableChecker;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SubSystemView;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WholeMovement {
    Constants constants = Constants.getInstance();

    private CoinsManager coinsManager;
    // resources
    private volatile List<Connection> connections;
    private volatile Map<Port, Connection> exitConnections;
    private volatile List<PocketMain> pockets;
    private volatile List<SystemView> systemViews;
    private double speedMultiplier = 1;

    //checkers and workers har har

    StartAvailableChecker startAvailableChecker;



    SystemView startingSystemView;
    private final Map<Connection, ChangeListener<Boolean>> waitingSendListeners = new HashMap<>();
    private final Map<PocketMain, ChangeListener<Boolean>> pocketListeners = new HashMap<>();
    private final Map<SystemView, ChangeListener<Boolean>> SystemListeners = new HashMap<>();




    public WholeMovement(CoinsManager coinsManager) {
        connections = constants.getConnections();
        exitConnections = constants.getExitConnections();
        pockets = constants.getPockets();
        systemViews = constants.getSystemViews();
        startAvailableChecker = new StartAvailableChecker(systemViews);
        this.coinsManager = coinsManager;

    }
    public void StartSending(double speedMultiplier , double AvailableTime){
        this.speedMultiplier = speedMultiplier;
        System.out.println("StartSending");
        Reset();

        getStartSystemView();



        for (PocketMain pocket : pockets) {
            pocket.setAvailableTime(AvailableTime);
            pocket.setMovementManager(this);
            SendingPockets(startingSystemView , pocket , -1);
        }

        PutListenersForSystems();


    }


    public void PutListenersForSystems(){
        for (SystemView systemView : systemViews) {
            MakeSystemConnectionsWaiting(systemView);
        }
    }


    public void SendingPockets(SystemView systemView , PocketMain pocket , int  fromSystem ){
        Connection exitConnection = pocket.ReleaseAct(pocket, systemView, exitConnections , speedMultiplier, null);
        if (exitConnection != null) {
            if (fromSystem != -1) {
                systemView.getCapacity()[fromSystem] = null;
            }
            resumeMovement(pocket , exitConnection);
            return;
        }

//        System.out.println(pocket.getType());


        AddToWaitingSystemCapacity(systemView , pocket);



    }
    public void MakeSystemConnectionsWaiting(SystemView systemView){
        for (SubSystemView subSystemView : systemView.getSubSystems()) {
            if (subSystemView.DoesItHavaExitGate()){
                Connection exitConnection = exitConnections.get(subSystemView.getExitPort());


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

        return connection.getCurve().isIsItUsed() && connection.getFromPort().getPortInfo().getSystem().isItDown();
    }

    public void AddToWaitingSend(SystemView systemView , Connection connection){
        for (int i = 0 ; i < systemView.getCapacity().length ; i++) {
            if (systemView.getCapacity()[i] != null){
                PocketMain pocket = systemView.getCapacity()[i];

                SendingPockets(systemView , pocket , i);


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

        if (IsItBig(pocket)) {
            connection.ChangePorts(PortTypes.TRIANGLE);
            connection.getCurve().setHP(connection.getCurve().getHP() - 1);
        }


        ChangeListener<Boolean> l = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs,
                                Boolean oldVal, Boolean newVal) {

                if (!newVal) {

                    obs.removeListener(this);

                    int coins = pocket.getCoinsPerEntry();
                    coinsManager.Increment(coins);

                    SendingPockets(connection.getToPort().getPortInfo().getSystem(), pocket , -1);
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
        for (int i = 0 ; i < systemView.getCapacity().length ; i++){
            if (systemView.getCapacity()[i] == null){
                systemView.getCapacity()[i] = pocket;
                systemView.EnterSystem();
                break;
            }
        }
    }
    private boolean IsItBig(PocketMain pocket){
        if (pocket.getType() == PocketTypes.BIG_1) return true;
        else if (pocket.getType() == PocketTypes.BIG_2) return true;

        return false;
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
            connection.resetIt();
            connection.getCurve().setHP(connection.getCurve().getFullHP());
        }
        for (SystemView systemView : systemViews) {
            systemView.setIsItDown(false);
            Arrays.fill(systemView.getCapacity(), null);
            systemView.reset();
        }
        for (PocketMain pocket : pockets) {
            pocket.getPathMover().reset();
            pocket.setAvailableTime(2000);
            pocket.getPathMover().stop();
            pocket.setIsItMoved(false);
            pocket.setIsItCollided(false);
            pocket.setHP(pocket.getMaxHp());

            pocket.setLayoutX(-1000);
            pocket.setLayoutY(-1000);
            pocket.setItAffected(false);


            pocket.setBehaviour(pocket.getFirstPocketType());
            pocket.setWhichSystemViewThisPocketIsAffectedBy(null);
        }
    }

}
