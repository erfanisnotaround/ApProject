package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class Scroll_Of_Eliphus implements AbilityExecutable {
    @Override
    public AbilityTypes AbilityType() {
        return null;
    }

    @Override
    public boolean isReady(GameContext context) {
        return false;
    }

    @Override
    public void execute(GameContext context) {

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
