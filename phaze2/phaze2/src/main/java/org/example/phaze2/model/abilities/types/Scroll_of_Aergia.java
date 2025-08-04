package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;
import org.example.phaze2.model.constants.AbilityBooleansConstants;

public class Scroll_of_Aergia implements AbilityExecutable {


    private final int coinsCost = 10;
    private long lastUsed = 0;
    private final AbilityBooleansConstants abilityBooleansConstants = AbilityBooleansConstants.getInstance();


    @Override
    public AbilityTypes AbilityType() {
        return AbilityTypes.Scroll_of_Aergia;
    }

    @Override
    public boolean isReady(GameContext context) {
        return context.coins.getNumberOfCoins() >= coinsCost;
    }

    @Override
    public void execute(GameContext context) {
        lastUsed = context.now();
    }

    @Override
    public long cooldown() {
        return 0;
    }

    @Override
    public long lastUsed() {
        return this.lastUsed;
    }

}
