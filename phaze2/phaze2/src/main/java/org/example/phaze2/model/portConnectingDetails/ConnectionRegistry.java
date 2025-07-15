package org.example.phaze2.model.portConnectingDetails;

import javafx.scene.Node;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.Port;

import java.util.*;

public class ConnectionRegistry {
    Constants constants = Constants.getInstance();
    private final Set<Port> exitGates = new HashSet<>();
    private final Set<Port> enterGates = new HashSet<>();
    private final List<Connection> connections = constants.getConnections();
    private final Map<Port, Connection> exitConnections = constants.getExitConnections();

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
        connections.add(connection);
        exitConnections.put(connection.getFromPort(), connection);
        exitConnections.put(connection.getToPort(), connection);
    }

    public void removeConnection(Connection conn) {
        connections.remove(conn);
        exitConnections.remove(conn.getFromPort());
        exitConnections.remove(conn.getToPort());
        exitGates.add(conn.getFromPort());
        enterGates.add(conn.getToPort());
    }

    public List<Connection> getConnections() {
        return Collections.unmodifiableList(connections);
    }

}
