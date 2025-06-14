package com.example.phaze1.model.systemsInfoAndManagers;

import javafx.scene.control.Button;

public class OneLevelInLevelScene extends Button {
    private boolean IsItPassed = false;
    private int level = 0;
    public OneLevelInLevelScene(boolean IsItPassed, int level) {
        this.IsItPassed = IsItPassed;
        this.level = level;
    }

    public boolean isItPassed() {
        return IsItPassed;
    }

    public void setItPassed(boolean itPassed) {
        IsItPassed = itPassed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
