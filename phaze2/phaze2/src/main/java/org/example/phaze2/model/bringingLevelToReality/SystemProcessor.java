package org.example.phaze2.model.bringingLevelToReality;

import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.SystemView;

import java.util.ArrayList;
import java.util.List;

public class SystemProcessor implements Runnable{
    private final List<System> systemInfos;
    private List<SystemView> systemViews = new ArrayList<>();
    private ConnectionUI connectionUI;
    public SystemProcessor(List<System> systemInfos , ConnectionUI connectionUI) {
        this.systemInfos = systemInfos;
        this.connectionUI = connectionUI;
    }
    @Override
    public void run() {
        for (System system : systemInfos) {
            systemViews.add(processSystem(system));
        }
    }
    public SystemView processSystem(System system) {
        SystemView systemView = new SystemView();



        return systemView;
    }

    public List<SystemView> getSystemViews() {
        return systemViews;
    }
}
