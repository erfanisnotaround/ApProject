package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class Scroll_Of_Eliphus implements AbilityExecutable {


    private final int coinsCost = 20;
    private long lastUsed = 0;


    public Scroll_Of_Eliphus() {

    }

    @Override
    public AbilityTypes AbilityType() {
        return null;
    }

    @Override
    public boolean isReady(GameContext context) {
        return false;
    }

    @Override
    public void execute(GameContext context , double length) {


        context.getGameState().setWeAreAddingAbility(true);

        lastUsed = context.now();
        context.getFollowerSpawner().spawn(FollowerType.LineDistance_Zero_Maker);

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
