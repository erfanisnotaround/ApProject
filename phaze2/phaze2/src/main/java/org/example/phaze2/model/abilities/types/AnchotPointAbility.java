package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class AnchotPointAbility implements AbilityExecutable {
    private int coinsCost = 1;
    @Override
    public AbilityTypes AbilityType() {
        return AbilityTypes.ANCHOR_POINT;
    }

    @Override
    public boolean isReady(GameContext context) {
        return context.coins.getNumberOfCoins() >= coinsCost;
    }

    @Override
    public void execute(GameContext context) {
        context.getGameState().setAddingANchorAvailable(true);
    }

    @Override
    public long cooldown() {
        return 0;
    }

    @Override
    public long lastUsed() {
        return 0;
    }
}
