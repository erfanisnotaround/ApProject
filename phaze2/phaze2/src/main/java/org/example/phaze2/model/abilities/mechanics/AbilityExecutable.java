package org.example.phaze2.model.abilities.mechanics;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public interface AbilityExecutable {
    AbilityTypes AbilityType();

    boolean isReady(GameContext context);

    void execute(GameContext context , double length);

    long cooldown();

    long lastUsed();

    default long durationMs() { return 0; }

    default void setLastUsed(long t) {}

    default long remainingActiveMs(GameContext ctx) {
        long d = durationMs();
        if (d <= 0) return 0;
        long rem = lastUsed() + d - ctx.now();
        return Math.max(0, rem);
    }

    default void resume(GameContext ctx, long remainingMs) {
        execute(ctx, remainingMs);
    }
}
