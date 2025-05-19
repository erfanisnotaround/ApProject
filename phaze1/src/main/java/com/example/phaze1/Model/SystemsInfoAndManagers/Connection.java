package com.example.phaze1.Model.SystemsInfoAndManagers;

import javafx.scene.Node;
import javafx.scene.shape.Polyline;

public class Connection {
    public final GatePortInfo from;
    public final GatePortInfo to;
    public final Curve curve;
    public final Node fromNode;
    public final Node   toNode;

    public Connection(GatePortInfo fromInfo,
                      GatePortInfo toInfo,
                      Curve curve,
                      Node fromNode,
                      Node toNode) {
        this.from     = fromInfo;
        this.to       = toInfo;
        this.curve    = curve;
        this.fromNode = fromNode;
        this.toNode   = toNode;

        // make absolutely sure the curve points back at this connection:
        this.curve.connection = this;
    }

    public Node getFromNode() { return fromNode; }
    public Node getToNode()   { return toNode;   }
}
