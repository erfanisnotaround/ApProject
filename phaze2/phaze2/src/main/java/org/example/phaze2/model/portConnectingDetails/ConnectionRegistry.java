package org.example.phaze2.model.portConnectingDetails;

import javafx.scene.Node;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.PortInfo;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.*;

public class ConnectionRegistry {
    Constants constants = Constants.getInstance();
    private final Set<Port> exitGates = new HashSet<>();
    private final Set<Port> enterGates = new HashSet<>();
    private final List<Connection> connections;
    private final Map<Port, Connection> exitConnections;

    private GameState gameState;

    public ConnectionRegistry(GameState gameState){
        this.gameState = gameState;
        connections = gameState.getResources().getConnections();
        exitConnections = gameState.getResources().getExitConnections();
    }

    public void registerExit(Port gate, PortInfo info) {
        gate.setPortInfo(info);
        exitGates.add(gate);
    }

    public void registerEnter(Port gate, PortInfo info) {
        gate.setPortInfo(info);
        enterGates.add(gate);
    }


    public Set<Port> getExitGates() {
        return exitGates;
    }

    public Set<Port> getEnterGates() {
        return enterGates;
    }
    public void removeGates(Node exitGate , Node enterGate) {
        exitGates.remove(exitGate);
        enterGates.remove(enterGate);
    }

    public void addConnection(Connection connection) {


        gameState.getResources().getConnections().add(connection);
        gameState.getResources().getExitConnections().put(connection.getFromPort(), connection);
        gameState.getResources().getExitConnections().put(connection.getToPort(), connection);

    }

    public void removeConnection(Connection conn) {
        if (conn == null) return;

        exitGates.add(conn.getFromPort());
        enterGates.add(conn.getToPort());

        gameState.getResources().getConnections().remove(conn);
        gameState.getResources().getExitConnections().remove(conn.getFromPort());
        gameState.getResources().getExitConnections().remove(conn.getToPort());

    }

    public List<Connection> getConnections() {
        return Collections.unmodifiableList(connections);
    }


}
