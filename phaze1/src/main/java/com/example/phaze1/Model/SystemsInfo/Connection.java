package com.example.phaze1.Model.SystemsInfo;

public class Connection {
    public final GatePortInfo from;
    public final GatePortInfo to;
    public final Curve curve;
    public Connection(GatePortInfo from, GatePortInfo to, Curve curve) {
        this.from  = from;
        this.to    = to;
        this.curve = curve;
    }
}
