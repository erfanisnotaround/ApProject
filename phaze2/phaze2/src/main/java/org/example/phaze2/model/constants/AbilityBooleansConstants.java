package org.example.phaze2.model.constants;

public class AbilityBooleansConstants {
    private static AbilityBooleansConstants instance = new AbilityBooleansConstants();

    private boolean movingSystemsAvailable = true;
    private boolean weAreAddingAbility = true;


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

    public boolean isWeAreAddingAbility() {
        return weAreAddingAbility;
    }

    public void setWeAreAddingAbility(boolean weAreAddingAbility) {
        this.weAreAddingAbility = weAreAddingAbility;
    }
}
