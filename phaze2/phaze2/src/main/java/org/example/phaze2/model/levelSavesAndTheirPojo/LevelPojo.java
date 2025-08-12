package org.example.phaze2.model.levelSavesAndTheirPojo;

import java.util.List;

public class LevelPojo {
    private List<PocketPojo> pockets;
    private List<SystemsPojo> systems;
    private List<ConnectionPojo> connections;
    private LevelCurrentDetailsPojo currentDetails;



    public List<PocketPojo> getPockets() {
        return pockets;
    }

    public void setPockets(List<PocketPojo> pockets) {
        this.pockets = pockets;
    }

    public List<SystemsPojo> getSystems() {
        return systems;
    }

    public void setSystems(List<SystemsPojo> systems) {
        this.systems = systems;
    }

    public List<ConnectionPojo> getConnections() {
        return connections;
    }

    public void setConnections(List<ConnectionPojo> connections) {
        this.connections = connections;
    }

    public LevelCurrentDetailsPojo getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(LevelCurrentDetailsPojo currentDetails) {
        this.currentDetails = currentDetails;
    }
}
