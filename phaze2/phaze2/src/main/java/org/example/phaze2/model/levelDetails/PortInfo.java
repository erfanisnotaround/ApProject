package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.example.phaze2.model.constants.PortTypes;

public class PortInfo {
    public final SystemView system;
    public final int        subIndex;
    public final boolean    isExit;
    public final PortTypes type;
    public BooleanProperty isItReachedDestination ;

    public PortInfo(SystemView system, int subIndex, boolean isExit, PortTypes type) {
        this.system   = system;
        this.subIndex = subIndex;
        this.isExit    = isExit;
        this.type      = type;
        this.isItReachedDestination = new SimpleBooleanProperty(false);
    }
}
