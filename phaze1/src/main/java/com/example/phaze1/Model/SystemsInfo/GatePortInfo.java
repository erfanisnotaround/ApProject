package com.example.phaze1.Model.SystemsInfo;

public class GatePortInfo {
    public final SystemView system;
    public final int        subIndex;
    public final boolean    isExit;
    public final GateType type;

    public GatePortInfo(SystemView system, int subIndex, boolean isExit, GateType type) {
        this.system   = system;
        this.subIndex = subIndex;
        this.isExit    = isExit;
        this.type      = type;
    }
}
