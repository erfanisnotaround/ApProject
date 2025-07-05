package org.example.phaze2.controllers.moverController;

import javafx.scene.Node;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SubSystemView;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PathPrioritizing {
    Constants constants = Constants.getInstance();
    private List<Connection> connections;
    private Map<Node, PortInfo> portInfoMap;
    private Map<PortInfo , Connection> exitConnections;
    private List<Pocket> pockets;
    private List<SystemView> systemViews;
    public PathPrioritizing() {

        connections = constants.getConnections();
        portInfoMap = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        pockets = constants.getPockets();
        systemViews = constants.getSystemViews();

    }

    public List<Connection> firstPrioritizedSubSystems(SystemView systemView , PortTypes portType){
        List<Connection> FirstConnection = new ArrayList<>();

        for(SubSystemView subSystemView : systemView.getSubSystems()){
            if (subSystemView.DoesItHavaExitGate()){
                PortInfo portInfo = portInfoMap.get(subSystemView.getExitPort());
                Connection exitConnection = exitConnections.get(portInfo);
                if (exitConnection == null){continue;}
                if (subSystemView.getExitGate() == portType && !exitConnection.getCurve().isIsItUsed()){
                    FirstConnection.add(exitConnection);
                }
            }
        }

        return FirstConnection;
    }
    public List<Connection> SecondPrioritizedSubSystems(SystemView systemView , PortTypes portType){
        List<Connection> SecondConnection = new ArrayList<>();

        for(SubSystemView subSystemView : systemView.getSubSystems()){
            if (subSystemView.DoesItHavaExitGate()){
                PortInfo portInfo = portInfoMap.get(subSystemView.getExitPort());
                Connection exitConnection = exitConnections.get(portInfo);
                if (exitConnection == null){continue;}
                if (subSystemView.getExitGate() != portType && !exitConnection.getCurve().isIsItUsed()){
                    SecondConnection.add(exitConnection);
                }
            }
        }

        return SecondConnection;
    }
    public List<Connection> AllSubSystems(SystemView systemView , PortTypes portType){
        List<Connection> AllSubSystemViews = new ArrayList<>();

        AllSubSystemViews.addAll(firstPrioritizedSubSystems(systemView , portType));
        AllSubSystemViews.addAll(SecondPrioritizedSubSystems(systemView , portType));

        return AllSubSystemViews;
    }
}
