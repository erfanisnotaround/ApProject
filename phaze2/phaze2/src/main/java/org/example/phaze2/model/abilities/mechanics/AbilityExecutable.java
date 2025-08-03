package org.example.phaze2.model.abilities.mechanics;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public interface AbilityExecutable {
    AbilityTypes AbilityType();

    boolean isReady(GameContext context);

    boolean execute(GameContext context);

    long cooldown();

    long lastUsed();
    void Execute();
}
