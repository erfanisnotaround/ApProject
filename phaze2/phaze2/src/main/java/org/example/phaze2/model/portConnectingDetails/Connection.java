package org.example.phaze2.model.portConnectingDetails;

import javafx.scene.Node;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.PortInfo;

public class Connection {
    private PortInfo to;
    private PortInfo from;
    private Node toNode;
    private Node fromNode;
    private Curve curve;
    public Connection(PortInfo toInfo, PortInfo fromInfo, Curve curve , Node fromNode, Node toNode) {
        this.to = toInfo;
        this.from = fromInfo;
        this.curve = curve;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }
    public PortInfo getTo() {
        return to;
    }

    public void setTo(PortInfo to) {
        this.to = to;
    }

    public PortInfo getFrom() {
        return from;
    }

    public void setFrom(PortInfo from) {
        this.from = from;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Curve getCurve() {
        return curve;
    }

    public void setCurve(Curve curve) {
        this.curve = curve;
    }
}
