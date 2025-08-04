package org.example.phaze2.controllers.abilityManagers.abilityKeys;

import javafx.scene.control.Button;
import org.example.phaze2.model.abilities.AbilityTypes;

public class ShopButton extends Button {
    private final AbilityTypes abilityType;
    public ShopButton(String text , AbilityTypes abilityType) {
        super(text);
        this.abilityType = abilityType;
    }

    public AbilityTypes getAbilityType() {
        return abilityType;
    }
}
