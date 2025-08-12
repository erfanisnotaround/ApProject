package org.example.phaze2.model.abilities.types;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class OAiryaman implements AbilityExecutable {

    int CoinsCost = 4;
    int LengthOfActivation = 5;

    @Override
    public AbilityTypes AbilityType() {
        return AbilityTypes.OAIRYAMAN;
    }

    @Override
    public boolean isReady(GameContext context) {
        return context.coins.getNumberOfCoins() >= CoinsCost;
    }

    @Override
    public void execute(GameContext context , double length) {

        context.getGameState().setDoesCollideCounts(false);
        PauseTransition pause = new PauseTransition(Duration.seconds(LengthOfActivation));
        pause.setOnFinished(event -> {
            context.getGameState().setDoesCollideCounts(true);
            context.getAliveManager().RemoveAbility(AbilityType());


        });
        pause.play();
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
