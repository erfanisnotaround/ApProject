package org.example.phaze2.model.bringingLevelToReality;

import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;

import java.util.ArrayList;
import java.util.List;

public class SystemVisualizer {
    private Level CurrentLevel;
    private ConnectionUI connectionUI;
    private List<SystemView> SystemViews;
    private List<Pocket> Pockets;

    public SystemVisualizer(Level CurrentLevel , ConnectionUI connectionUI) {
        this.CurrentLevel = CurrentLevel;
        this.connectionUI = connectionUI;
    }

    public List<SystemView> getSystemViews() {
        SystemProcessor systemProcessor = new SystemProcessor(CurrentLevel.getSystems() , connectionUI);
        Thread systemsProcessor = new Thread(systemProcessor);
        systemsProcessor.start();
        try {
            systemsProcessor.join();
            SystemViews = systemProcessor.getSystemViews();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return SystemViews;
    }


    public List<Pocket> getPockets() {
        PocketProcessor pocketProcessor = new PocketProcessor(CurrentLevel.getPockets());
        Thread pocketsProcessor = new Thread(pocketProcessor);
        pocketsProcessor.start();

        try {
            pocketsProcessor.join();
            Pockets = pocketProcessor.getPockets();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Pockets;
    }

}
