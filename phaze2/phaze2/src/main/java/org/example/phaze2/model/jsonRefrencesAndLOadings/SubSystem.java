package org.example.phaze2.model.jsonRefrencesAndLOadings;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.phaze2.model.constants.PortTypes;

public class SubSystem{
    private boolean doesItHaveEnterGate;
    private boolean doesItHavaExitGate;
    @JsonProperty("EnterGate")
    private PortTypes EnterGate;
    @JsonProperty("ExitGate")
    private PortTypes ExitGate;

    public boolean DoesItHaveEnterGate() {
        return doesItHaveEnterGate;
    }

    public void setDoesItHaveEnterGate(boolean doesItHaveEnterGate) {
        this.doesItHaveEnterGate = doesItHaveEnterGate;
    }

    public boolean DoesItHavaExitGate() {
        return doesItHavaExitGate;
    }

    public void setDoesItHavaExitGate(boolean doesItHavaExitGate) {
        this.doesItHavaExitGate = doesItHavaExitGate;
    }

    public PortTypes getEnterGate() {
        return EnterGate;
    }

    public void setEnterGate(PortTypes enterGate) {
        EnterGate = enterGate;
    }

    public PortTypes getExitGate() {
        return ExitGate;
    }

    public void setExitGate(PortTypes exitGate) {
        ExitGate = exitGate;
    }
}
