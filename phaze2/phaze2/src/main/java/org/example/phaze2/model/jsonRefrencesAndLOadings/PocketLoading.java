package org.example.phaze2.model.jsonRefrencesAndLOadings;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;

public class PocketLoading {
    private String pocketName;
    private PocketTypes type;
    private double delay;


    public double getDelay() {return delay;}

    public void setDelay(double delay) {this.delay = delay;}


    public PocketTypes getType() {
        return type;
    }

    public void setType(PocketTypes type) {
        this.type = type;
    }

    public String getPocketName() {
        return pocketName;
    }

    public void setPocketName(String pocketName) {
        this.pocketName = pocketName;
    }
}
