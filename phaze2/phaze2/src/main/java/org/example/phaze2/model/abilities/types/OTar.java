package org.example.phaze2.model.abilities.types;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public class OTar implements AbilityExecutable {

    int CoinsCost = 3;
    int LengthOfActivation = 10;

    @Override
    public AbilityTypes AbilityType() {
        return AbilityTypes.OTAR;
    }

    @Override
    public boolean isReady(GameContext context) {
        return context.coins.getNumberOfCoins() >= CoinsCost;
    }

    @Override
    public void execute(GameContext context , double length) {
        context.getGameState().setCanWeSpreadWave(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(LengthOfActivation));
        if (length > 0) pause.setDuration(Duration.seconds(length));
        pause.setOnFinished(event -> {
            context.getGameState().setCanWeSpreadWave(true);
            context.getAliveManager().RemoveAbility(AbilityTypes.OTAR);
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
