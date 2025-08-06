package org.example.phaze2.model.abilities.types;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class OAnahita implements AbilityExecutable {
    private int CoinsCost = 5;
    @Override
    public AbilityTypes AbilityType() {
        return AbilityTypes.OANAHITA;
    }

    @Override
    public boolean isReady(GameContext context) {
        return context.coins.getNumberOfCoins() >= CoinsCost;
    }

    @Override
    public void execute(GameContext context) {
        for (PocketMain pocketMain : Constants.getInstance().getPockets()){
            pocketMain.setHP(pocketMain.getMaxHp());
        }
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
