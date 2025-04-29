package com.example.phaze1.Model;

import com.example.phaze1.Model.SystemsInfo.GateType;

public class SubSystem {
    private boolean doesItHaveEnterGate;
    private boolean doesItHavaExitGate;
    private GateType EnterGate;
    private GateType ExitGate;

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

    public GateType getEnterGate() {
        return EnterGate;
    }

    public void setEnterGate(GateType enterGate) {
        EnterGate = enterGate;
    }

    public GateType getExitGate() {
        return ExitGate;
    }

    public void setExitGate(GateType exitGate) {
        ExitGate = exitGate;
    }
}
