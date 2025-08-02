package org.example.phaze2.model.hudModels;

import org.example.phaze2.model.abilities.AbilityTypes;

import java.util.ArrayList;
import java.util.List;

public class AbilityAliveManager {


    private List<AbilityTypes> aliveAbilities = new ArrayList<>();

    public void AddAliveAbility(AbilityTypes abilityType) {
        aliveAbilities.add(abilityType);
    }
    public void RemoveAbility(AbilityTypes abilityType) {
        aliveAbilities.remove(abilityType);
    }
    public List<AbilityTypes> getAliveAbilities() {
        return aliveAbilities;
    }
    public void setAliveAbilities(List<AbilityTypes> aliveAbilities) {
        this.aliveAbilities = aliveAbilities;
    }
}
