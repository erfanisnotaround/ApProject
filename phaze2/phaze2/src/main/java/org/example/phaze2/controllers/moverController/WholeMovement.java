package org.example.phaze2.controllers.moverController;

import javafx.scene.Node;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

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
    public WholeMovement() {

        connections = constants.getConnections();
        portInfoMap = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        pockets = constants.getPockets();
        systemViews = constants.getSystemViews();


        startAvailableChecker = new StartAvailableChecker(systemViews);

    }
    public void StartSending(){
        if (!startAvailableChecker.canWeStartConnections()) return;
        getStartSystemView();

        for (Pocket pocket : pockets) {

        }


    }
    public void getStartSystemView(){
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem()) startingSystemView = systemView;
        }
    }
}
