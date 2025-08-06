package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class Scroll_of_Aergia implements AbilityExecutable {


    private final int coinsCost = 10;
    private long lastUsed = 0;


    public Scroll_of_Aergia() {
    }

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
        context.getFollowerSpawner().spawn(FollowerType.Acceleration_zero_Maker);
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
