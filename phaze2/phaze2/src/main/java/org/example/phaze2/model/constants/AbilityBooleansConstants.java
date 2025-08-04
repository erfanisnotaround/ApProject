package org.example.phaze2.model.constants;

public class AbilityBooleansConstants {
    private static AbilityBooleansConstants instance = new AbilityBooleansConstants();

    private boolean movingSystemsAvailable = true;


    private AbilityBooleansConstants() {}

    public static AbilityBooleansConstants getInstance() {
        return instance;
    }

    public boolean isMovingSystemsAvailable() {
        return movingSystemsAvailable;
    }

    public void setMovingSystemsAvailable(boolean movingSystemsAvailable) {
        this.movingSystemsAvailable = movingSystemsAvailable;
    }
}
