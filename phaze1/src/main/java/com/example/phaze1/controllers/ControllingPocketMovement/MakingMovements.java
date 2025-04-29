package com.example.phaze1.controllers.ControllingPocketMovement;
import com.example.phaze1.model.LevelLoader;
import com.example.phaze1.model.SystemsInfo.*;
import com.example.phaze1.model.constants;
import javafx.animation.PathTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakingMovements {
    Pane LineContainer;
    Map<Node, GatePortInfo> portInfo;
    Map<GateType , Connection> exitConnections;
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
        Connection connection = exitConnections.get(subSystem.ExitGate);
        if (connection != null) {
            GatePortInfo destination = connection.to;
            Rectangle rectangle = new Rectangle(5 , 5);
            rectangle.setFill(Color.BLACK);
            LineContainer.getChildren().add(rectangle);
            PathTransition pt = new PathTransition(Duration.seconds(5) , getPath(connection.curve) , rectangle);
            pt.play();
        }
        else {
            System.out.println("dd");
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
