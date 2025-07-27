package org.example.phaze2.model.levelSavesAndTheirPojo;

import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;

public class PortPojo {
    private String systemID;
    private int subIndex;
    private boolean isExit;
    private PortTypes type;
    private PortTypes beforeChange;

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public int getSubIndex() {
        return subIndex;
    }

    public void setSubIndex(int subIndex) {
        this.subIndex = subIndex;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
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
