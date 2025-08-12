package org.example.phaze2.controllers.moverController.moveRelated;

import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SubSystemView;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PathPrioritizing {

    private List<Connection> connections;
    private Map<Port, Connection> exitConnections;
    private List<PocketMain> pockets;
    private List<SystemView> systemViews;
    public PathPrioritizing(GameState gameState) {

        connections = gameState.getResources().getConnections();
        exitConnections = gameState.getResources().getExitConnections();
        pockets = gameState.getResources().getPockets();
        systemViews = gameState.getResources().getSystemViews();

    }

    public List<Connection> firstPrioritizedSubSystems(SystemView systemView , PortTypes portType){
        List<Connection> FirstConnection = new ArrayList<>();

        for(SubSystemView subSystemView : systemView.getSubSystems()){
            if (subSystemView.DoesItHavaExitGate()){
                Connection exitConnection = exitConnections.get(subSystemView.getExitPort());
                if (exitConnection == null){ continue;}
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
                Connection exitConnection = exitConnections.get(subSystemView.getExitPort());
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
