package org.example.phaze2.model.levelSavesAndTheirPojo;

import org.example.phaze2.model.abilities.AbilityTypes;

public class AbilityExecutorPojo {
    private AbilityTypes abilityType;
    private long coolDown;
    private long lastUsed;
    private long timeRemaining;
    private long timeBetweenUsedANdNow;

    public AbilityTypes getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityTypes abilityType) {
        this.abilityType = abilityType;
    }

    public long getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(long coolDown) {
        this.coolDown = coolDown;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public long getTimeBetweenUsedANdNow() {
        return timeBetweenUsedANdNow;
    }

    public void setTimeBetweenUsedANdNow(long timeBetweenUsedANdNow) {
        this.timeBetweenUsedANdNow = timeBetweenUsedANdNow;
    }
}
