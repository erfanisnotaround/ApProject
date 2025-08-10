package org.example.phaze2.controllers.moverController.checkings;

import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class StartAvailableChecker implements StartAvailableForConnections {
    List<SystemView> systemViews;
    List<Connection> connections;
    public StartAvailableChecker(List<SystemView> systemViews , List<Connection> connections) {
        this.connections = connections;
        this.systemViews = systemViews;
    }
    @Override
    public boolean canWeStartConnections() {
        for (SystemView systemView : systemViews) {
            if (!systemView.isTheLightOn()) return false;
        }
        for (Connection connection : connections) {
            if (!connection.isCanWeUse()) return false;
        }
        return true;
    }
}
