package org.example.phaze2.controllers.moverController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.util.Duration;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SubSystemView;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WholeMovement {
    Constants constants = Constants.getInstance();

    // resources
    private List<Connection> connections;
    private Map<Node , PortInfo> portInfoMap;
    private Map<PortInfo , Connection> exitConnections;
    private List<Pocket> pockets;
    private List<SystemView> systemViews;

    //checkers and workers har har

    StartAvailableChecker startAvailableChecker;


    // in Class requirmenets

    SystemView startingSystemView;
    private final Map<Connection, ChangeListener<Boolean>> waitingSendListeners = new HashMap<>();



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



        for (Pocket pocket : pockets) {
            SendingPockets(startingSystemView , pocket);
        }

        for (SystemView systemView : systemViews) {
            MakeSystemConnectionsWaiting(systemView);
        }

    }




    public void SendingPockets(SystemView systemView , Pocket pocket){
        Connection exitConnection = pocket.ReleaseAct(systemView , portInfoMap , exitConnections);
        if (exitConnection != null) {
            resumeMovement(pocket , exitConnection);
            return;
        }

        System.out.println(pocket.getType());


        AddToWaitingSystemCapacity(systemView , pocket);



    }
    public void MakeSystemConnectionsWaiting(SystemView systemView){
        for (SubSystemView subSystemView : systemView.getSubSystems()) {
            if (subSystemView.DoesItHavaExitGate()){
                PortInfo portInfo = portInfoMap.get(subSystemView.getExitPort());
                Connection exitConnection = exitConnections.get(portInfo);


                ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> {
                    if (!newValue) {
                        AddToWaitingSend(systemView, exitConnection);
                    }
                };

                waitingSendListeners.put(exitConnection, listener);
                exitConnection.getCurve().isItUsedProperty().addListener(listener);
            }
        }
    }
    public void AddToWaitingSend(SystemView systemView , Connection connection){
        for (int i = 0 ; i < systemView.getCapacity().length ; i++) {
            if (systemView.getCapacity()[i] != null){
                Pocket pocket = systemView.getCapacity()[i];
                systemView.getCapacity()[i] = null;
                SendingPockets(systemView , pocket);

            }
        }
    }
    private boolean itThere(Pocket pocket , SystemView systemView ){
        for (Pocket pocket1 : systemView.getCapacity()) {
            if (pocket1 == null) continue;
            if (pocket1.equals(pocket)) return true;
        }
        return false;
    }
    public void resumeMovement(Pocket pocket , Connection connection){

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
        pocket.isItMovedProperty().addListener(l);
    }
    public void AddToWaitingSystemCapacity(SystemView systemView , Pocket pocket){
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


        for (Connection connection : connections) {
            connection.getCurve().setIsItUsed(false);
        }
        for (SystemView systemView : systemViews) {
            systemView.setIsItDown(false);
            Arrays.fill(systemView.getCapacity(), null);
        }
        for (Pocket pocket : pockets) {
            pocket.getPathMover().stop();
            pocket.setIsItMoved(false);
            pocket.setIsItCollided(false);
            pocket.setHP(pocket.getMaxHp());

            pocket.setLayoutX(500);
            pocket.setLayoutY(500);
        }


    }
}
