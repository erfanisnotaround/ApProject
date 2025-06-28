package org.example.phaze2.model.portConnectingDetails;

import javafx.scene.Node;
import org.example.phaze2.model.levelDetails.PortInfo;

import java.util.*;

public class ConnectionRegistry {
    private final Map<Node, PortInfo> portInfo = new HashMap<>();
    private final Set<Node> exitGates = new HashSet<>();
    private final Set<Node> enterGates = new HashSet<>();
    private final List<Connection> connections = new ArrayList<>();
    private final Map<PortInfo, Connection> exitConnections = new HashMap<>();

    public void registerExit(Node gate, PortInfo info) {
        portInfo.put(gate, info);
        exitGates.add(gate);
    }

    public void registerEnter(Node gate, PortInfo info) {
        portInfo.put(gate, info);
        enterGates.add(gate);
    }

    public Optional<PortInfo> getPortInfo(Node gate) {
        return Optional.ofNullable(portInfo.get(gate));
    }

    public Set<Node> getExitGates() {
        return exitGates;
    }

    public Set<Node> getEnterGates() {
        return enterGates;
    }
    public void removeGates(Node exitGate , Node enterGate) {
        exitGates.remove(exitGate);
        enterGates.remove(enterGate);
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
        exitConnections.put(connection.getFrom(), connection);
        exitConnections.put(connection.getTo(), connection);
    }

    public void removeConnection(Connection conn) {
        connections.remove(conn);
        exitConnections.remove(conn.getFrom());
        exitConnections.remove(conn.getTo());
        exitGates.add(conn.getFromNode());
        enterGates.add(conn.getToNode());
    }

    public List<Connection> getConnections() {
        return Collections.unmodifiableList(connections);
    }

    public Map<Node, PortInfo> getPortMap() {
        return Collections.unmodifiableMap(portInfo);
    }
}
