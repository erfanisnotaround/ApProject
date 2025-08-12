package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class Scroll_Of_SisyPhus implements AbilityExecutable {

    private final int coinsCost = 15;
    private long lastUsed = 0;

    @Override
    public AbilityTypes AbilityType() {
        return null;
    }

    @Override
    public boolean isReady(GameContext context) {
        return context.coins.getNumberOfCoins() >= coinsCost;
    }

    @Override
    public void execute(GameContext context , double length) {
        context.getGameState().setWeAreAddingAbility(true);
        lastUsed = context.now();
        context.coins.Decrement(coinsCost);
        context.getGameState().setMovingSystemsAvailable(true);
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
