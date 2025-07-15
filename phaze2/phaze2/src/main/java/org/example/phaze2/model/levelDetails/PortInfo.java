package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.example.phaze2.model.constants.PortTypes;

public class PortInfo {
    private final SystemView system;
    private int        subIndex;
    private final boolean    isExit;
    private PortTypes type;
    private PortTypes beforeChange;

    public PortInfo(SystemView system, int subIndex, boolean isExit, PortTypes type) {
        this.system   = system;
        this.subIndex = subIndex;
        this.isExit    = isExit;
        this.type      = type;
        this.beforeChange = type;
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
    public void setType(PortTypes type) {
        this.type = type;
    }

    public PortTypes getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(PortTypes beforeChange) {
        this.beforeChange = beforeChange;
    }
}
