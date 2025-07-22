package org.example.phaze2.controllers.moverController.checkings;

import org.example.phaze2.model.levelDetails.necessary.SystemView;

import java.util.List;

public class StartAvailableChecker implements StartAvailableForConnections {
    List<SystemView> systemViews;
    public StartAvailableChecker(List<SystemView> systemViews) {
        this.systemViews = systemViews;
    }
    @Override
    public boolean canWeStartConnections() {
        for (SystemView systemView : systemViews) {
            if (!systemView.isTheLightOn()) return false;
        }
        return true;
    }
}
