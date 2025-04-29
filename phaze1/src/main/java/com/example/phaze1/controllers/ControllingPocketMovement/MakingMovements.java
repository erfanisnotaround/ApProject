package com.example.phaze1.controllers.ControllingPocketMovement;
import com.example.phaze1.Model.SystemsInfo.*;
import com.example.phaze1.Model.constants;
import javafx.collections.ObservableList;
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
    public MakingMovements(Pane LineContainer , ArrayList<SystemView> systemViews) {
        this.LineContainer = LineContainer;
        this.systemViews = systemViews;
    }
    public void goForPocketMovement() throws IOException {
        portInfo = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        startSystem = getStartSystem(systemViews);
        System.out.println(startSystem.x + startSystem.y + " miew");
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
            Shape rectangle = connection.from.type.createShape();
            rectangle.setScaleX(2);
            rectangle.setScaleY(2);
            LineContainer.getChildren().add(rectangle);
            connection.curve.makeMovementOnThis(rectangle , connection);

        }
        else {
            System.out.println("There's no pocket connection");
        }

    }
    public Path getPath(Polyline poly){
        ObservableList<Double> pts = poly.getPoints();
        Path path = new Path();
        if (pts.size() < 2) return path;

        path.getElements().add(new MoveTo(pts.get(0), pts.get(1)));
        for (int i = 2; i < pts.size(); i += 2) {
            path.getElements().add(new LineTo(pts.get(i), pts.get(i+1)));
        }
        return path;
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
