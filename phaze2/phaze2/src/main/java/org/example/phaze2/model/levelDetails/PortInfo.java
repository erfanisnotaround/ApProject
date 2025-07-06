package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.example.phaze2.model.constants.PortTypes;

public class PortInfo {
    private final SystemView system;
    private final int        subIndex;
    private final boolean    isExit;
    private final PortTypes type;

    public PortInfo(SystemView system, int subIndex, boolean isExit, PortTypes type) {
        this.system   = system;
        this.subIndex = subIndex;
        this.isExit    = isExit;
        this.type      = type;
    }

    public SystemView getSystem() {
        return system;
    }

    public int getSubIndex() {
        return subIndex;
    }

    public boolean isExit() {
        return isExit;
    }

    public PortTypes getType() {
        return type;
    }
}
