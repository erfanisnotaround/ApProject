package org.example.phaze2.model.abilities.mechanics.followers;

import org.example.phaze2.model.abilities.AbilityTypes;

public enum FollowerType {
    Acceleration_zero_Maker(AbilityTypes.Scroll_of_Aergia),
    LineDistance_Zero_Maker(AbilityTypes.Scroll_of_Eliphas);

    private AbilityTypes abilityType;
    FollowerType(AbilityTypes abilityType){
        this.abilityType = abilityType;
    }
    public AbilityTypes getAbilityType() {
        return abilityType;
    }
}
