package com.example.phaze1.controllers.ControllingPocketMovement;
import com.example.phaze1.Model.SystemsInfoAndManagers.*;
import com.example.phaze1.Model.Constants.constants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MakingMovements {
    movementResumeManager movementResumeManager;
    Pane LineContainer;
    Map<Node, GatePortInfo> portInfo;
    Map<GatePortInfo , Connection> exitConnections;
    ArrayList<SystemView> systemViews;
    public SystemView startSystem;
    BooleanProperty isTheSystemReadyTOSendPockets = new SimpleBooleanProperty(true);
    public MakingMovements(Pane LineContainer , ArrayList<SystemView> systemViews) {
        this.LineContainer = LineContainer;
        this.systemViews = systemViews;
    }
    public void goForPocketMovement() throws IOException {
        portInfo = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        movementResumeManager = new movementResumeManager(portInfo, exitConnections , LineContainer);
        startSystem = getStartSystem(systemViews);
        StartMovement();
    }
    public void StartMovement(){
        for (ViewOfSubSystem subSystem : startSystem.SubSystems) {
            if (subSystem.doesItHavaExitGate){
                sendPocket(subSystem);
            }
        }
    }
    public void sendPocket(ViewOfSubSystem subSystem){
        Connection connection = exitConnections.get(portInfo.get(subSystem.ExitPort));
        if (connection != null) {
            startSystem.light.setFill(Color.LIGHTBLUE);
            Port rectangle = connection.from.type.createShape();
            rectangle.setScaleX(2);
            rectangle.setScaleY(2);
            LineContainer.getChildren().add(rectangle);
            connection.curve.makeMovementOnThis(rectangle , connection);
            movementResumeManager.resume(connection.to.system);
        }
        else {
            System.out.println("There's no pocket connection");
        }

    }
    public SystemView getStartSystem(ArrayList<SystemView> systemViews) {
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem){
                return systemView;
            }
        }
        return systemViews.getFirst();
    }
}
