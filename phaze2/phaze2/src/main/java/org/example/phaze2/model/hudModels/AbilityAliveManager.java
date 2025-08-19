package org.example.phaze2.model.hudModels;

import org.example.phaze2.model.abilities.AbilityTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbilityAliveManager {


    public AbilityAliveManager() {

    }
    private Set<AbilityTypes> aliveAbilities = new HashSet<>();

    public void AddAliveAbility(AbilityTypes abilityType) {
        aliveAbilities.add(abilityType);
    }
    public void RemoveAbility(AbilityTypes abilityType) {
        aliveAbilities.remove(abilityType);

    }
    public Set<AbilityTypes> getAliveAbilities() {
        return aliveAbilities;
    }
    public void setAliveAbilities(Set<AbilityTypes> aliveAbilities) {
        this.aliveAbilities = aliveAbilities;
    }
    public boolean isAlive(AbilityTypes abilityType) {
        return aliveAbilities.contains(abilityType);
    }
}
