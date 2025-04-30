package com.example.phaze1.controllers.ControllingPocketMovement;
import com.example.phaze1.Model.SystemsInfo.*;
import com.example.phaze1.Model.constants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MakingMovements {
    Pane LineContainer;
    Map<Node, GatePortInfo> portInfo;
    Map<GatePortInfo , Connection> exitConnections;
    ArrayList<SystemView> systemViews;
    SystemView startSystem;
    BooleanProperty isTheSystemReadyTOSendPockets = new SimpleBooleanProperty(true);
    public MakingMovements(Pane LineContainer , ArrayList<SystemView> systemViews) {
        this.LineContainer = LineContainer;
        this.systemViews = systemViews;
    }
    public void goForPocketMovement() throws IOException {
        portInfo = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        startSystem = getStartSystem(systemViews);
        StartMovement();
    }
    public void StartMovement(){
        for (ViewOfSubSystem subSystem : startSystem.SubSystems) {
            if (subSystem.doesItHavaExitGate){
                Connection connection = sendPocket(subSystem);
                if (connection != null){
                    resumingSendingPockets(connection.to.system , connection.to);
                }
            }
        }
    }
    public Connection sendPocket(ViewOfSubSystem subSystem){
        Connection connection = exitConnections.get(portInfo.get(subSystem.ExitPort));
        if (connection != null) {
            Shape rectangle = connection.from.type.createShape();
            rectangle.setScaleX(2);
            rectangle.setScaleY(2);
            LineContainer.getChildren().add(rectangle);
            connection.curve.makeMovementOnThis(rectangle , connection , connection.to);
        }
        else {
            System.out.println("There's no pocket connection");
        }
        return connection;

    }
    public SystemView getStartSystem(ArrayList<SystemView> systemViews) {
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem){
                return systemView;
            }
        }
        return systemViews.getFirst();
    }
    public void resumingSendingPockets(SystemView destinationSystem , GatePortInfo destinationPort){
        isTheSystemReadyTOSendPockets(destinationSystem, destinationPort).addListener((observable, oldValue, newValue) -> {
            if (newValue){
                System.out.println("Pocket resumed");
                sendPocketsTooOtherSystems(destinationSystem);
            }
        });
    }
    public void sendPocketsTooOtherSystems(SystemView destinationSystem){
        for (ViewOfSubSystem subSystem : destinationSystem.SubSystems) {
            if (subSystem.doesItHavaExitGate){
                sendPocket(subSystem);
            }
        }
    }
    public BooleanProperty isTheSystemReadyTOSendPockets(SystemView destinationSystem  , GatePortInfo destinationPort){
        for (ViewOfSubSystem subSystem : destinationSystem.SubSystems) {
            if (subSystem.doesItHaveEnterGate){
                destinationPort.isItReachedDestination.addListener((observable, oldValue, newValue) -> {
                    if (!newValue){
                        isTheSystemReadyTOSendPockets.set(false);
                    }
                });
            }
        }
        return isTheSystemReadyTOSendPockets;
    }

}
