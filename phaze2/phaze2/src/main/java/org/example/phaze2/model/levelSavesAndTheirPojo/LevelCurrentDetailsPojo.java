package org.example.phaze2.model.levelSavesAndTheirPojo;

import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.hudModels.CoinsManager;

import java.util.List;
import java.util.Set;

public class LevelCurrentDetailsPojo {

    private Set<AbilityExecutorPojo> aliveAbilities;
    private int CoinsHave;

    public int getCoinsHave() {
        return CoinsHave;
    }

    public void setCoinsHave(int coinsHave) {
        CoinsHave = coinsHave;
    }

    public Set<AbilityExecutorPojo> getAliveAbilities() {
        return aliveAbilities;
    }

    public void setAliveAbilities(Set<AbilityExecutorPojo> aliveAbilities) {
        this.aliveAbilities = aliveAbilities;
    }
}
