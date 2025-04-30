package com.example.phaze1.Model.SystemsInfoAndManagers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GatePortInfo {
    public final SystemView system;
    public final int        subIndex;
    public final boolean    isExit;
    public final GateType type;
    public BooleanProperty isItReachedDestination ;

    public GatePortInfo(SystemView system, int subIndex, boolean isExit, GateType type) {
        this.system   = system;
        this.subIndex = subIndex;
        this.isExit    = isExit;
        this.type      = type;
        this.isItReachedDestination = new SimpleBooleanProperty(false);
    }
}
