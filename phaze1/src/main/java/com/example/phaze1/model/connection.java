package com.example.phaze1.model;

import javafx.scene.shape.Polyline;

public class connection {
    private GateType startGate;
    private GateType endGate;
    private Polyline wire;
    public connection(GateType startGate, GateType endGate , Polyline wire) {
        this.startGate = startGate;
        this.endGate = endGate;
        this.wire = wire;
    }
    public GateType getStartGate() {
        return startGate;
    }

    public void setStartGate(GateType startGate) {
        this.startGate = startGate;
    }

    public GateType getEndGate() {
        return endGate;
    }

    public void setEndGate(GateType endGate) {
        this.endGate = endGate;
    }

    public Polyline getWire() {
        return wire;
    }

    public void setWire(Polyline wire) {
        this.wire = wire;
    }
}
