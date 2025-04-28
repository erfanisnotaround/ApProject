package com.example.phaze1.model.SystemsInfo;

import javafx.scene.shape.Polyline;

public class Connection {
    public final GatePortInfo from;
    public final GatePortInfo to;
    public final Polyline curve;
    public Connection(GatePortInfo from, GatePortInfo to, Polyline curve) {
        this.from  = from;
        this.to    = to;
        this.curve = curve;
    }
}
