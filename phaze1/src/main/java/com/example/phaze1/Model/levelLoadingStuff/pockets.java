package com.example.phaze1.Model.levelLoadingStuff;

import com.example.phaze1.Model.SystemsInfoAndManagers.GateType;

public class pockets {
    private GateType type;
    private double delay;
    private int whichSubSystem;

    public double getDelay() {return delay;}

    public void setDelay(double delay) {this.delay = delay;}

    public int getWhichSubSystem() {return whichSubSystem;}

    public void setWhichSubSystem(int whichSubSystem) {this.whichSubSystem = whichSubSystem;}

    public GateType getType() {
        return type;
    }

    public void setType(GateType type) {
        this.type = type;
    }
}
