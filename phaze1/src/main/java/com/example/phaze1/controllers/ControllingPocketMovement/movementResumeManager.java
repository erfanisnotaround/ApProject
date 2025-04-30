package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class movementResumeManager {
    private Pane LineContainer;
    Map<Node , GatePortInfo> PortInfo;
    Map<GatePortInfo , Connection> exitConnections;

    public movementResumeManager(Map<Node , GatePortInfo> PortInfo , Map<GatePortInfo , Connection> exitConnections , Pane LineContainer) {
        this.PortInfo = PortInfo;
        this.exitConnections = exitConnections;
        this.LineContainer = LineContainer;
    }
    public void resume(SystemView ParentSystem) {
        List<GatePortInfo> destinationPorts = new ArrayList<>();
        for (ViewOfSubSystem subsystem : ParentSystem.SubSystems) {
            if (subsystem.doesItHaveEnterGate){
                GatePortInfo EnterPort = PortInfo.get(subsystem.EnterPort);
                destinationPorts.add(EnterPort);
            }
        }
        BooleanBinding AreAllArrived = isItReachedDestination(destinationPorts);
        IsSystemReadyToSendPocketsFromSystem(ParentSystem , AreAllArrived);

    }
    public void IsSystemReadyToSendPocketsFromSystem(SystemView ParentSystem , BooleanBinding AreAllArrived) {
        AreAllArrived.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                CouldWeResume(ParentSystem);
                ParentSystem.light.setFill(Color.LIGHTBLUE);
                makingAllEnterFalse(ParentSystem);
            }
//            if (oldValue){
//                CouldWeResume(ParentSystem);
//                ParentSystem.light.setFill(Color.LIGHTBLUE);
//            }
        });
    }
    public void makingAllEnterFalse(SystemView ParentSystem) {
        for (ViewOfSubSystem subsystem : ParentSystem.SubSystems) {
            if (subsystem.doesItHaveEnterGate){
                GatePortInfo EnterPort = PortInfo.get(subsystem.EnterPort);
                EnterPort.isItReachedDestination.set(false);
            }
        }
    }
    public void CouldWeResume(SystemView ParentSystem) {
        if (!ParentSystem.isItFinisherSystem){
            sendingPockets(ParentSystem);
        }
        else {
            System.out.println("You Win");
        }
    }
    public void sendingPockets(SystemView ParentSystem) {
        for (ViewOfSubSystem subsystem : ParentSystem.SubSystems) {
            if (subsystem.doesItHavaExitGate){
                Connection connection = exitConnections.get(PortInfo.get(subsystem.ExitPort));
                if (connection != null) {
                    Port rectangle = connection.from.type.createShape();
                    rectangle.setScaleX(2);
                    rectangle.setScaleY(2);
                    LineContainer.getChildren().add(rectangle);
                    connection.curve.makeMovementOnThis(rectangle , connection);
                    resume(connection.to.system);
                }
                else {
                    System.out.println(":d");
                }
            }
        }
    }
    public BooleanBinding isItReachedDestination(List<GatePortInfo> destinationPorts) {
        ObservableValue<?>[] dependencies = destinationPorts.stream()
                .map(p -> p.isItReachedDestination)
                .toArray(ObservableValue[]::new);

        BooleanBinding allArrived = Bindings.createBooleanBinding(
                () -> destinationPorts.stream()
                        .allMatch(p -> p.isItReachedDestination.get()),
                dependencies
        );
        return allArrived;
    }
}
