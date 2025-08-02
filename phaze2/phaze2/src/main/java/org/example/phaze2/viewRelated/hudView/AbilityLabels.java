package org.example.phaze2.viewRelated.hudView;

import javafx.scene.control.Label;
import org.example.phaze2.model.abilities.AbilityTypes;

public class AbilityLabels extends Label {
    private AbilityTypes abilityType;

    public AbilityLabels(AbilityTypes abilityType , String text){
        super(text);
        this.abilityType = abilityType;
    }

    public AbilityTypes getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityTypes abilityType) {
        this.abilityType = abilityType;
    }
}
