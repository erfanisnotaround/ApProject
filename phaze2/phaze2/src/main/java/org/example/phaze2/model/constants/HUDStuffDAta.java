package org.example.phaze2.model.constants;

import org.example.phaze2.model.hudModels.AbilityAliveManager;
import org.example.phaze2.model.hudModels.BasicTransfer;
import org.example.phaze2.model.hudModels.CoinsManager;

public final class HUDStuffDAta {
    private CoinsManager coinsManager;
    private AbilityAliveManager abilityAliveManager = new AbilityAliveManager();
    private BasicTransfer basicTransfer;

    public AbilityAliveManager getAbilityAliveManager() {
        return abilityAliveManager;
    }

    public void setAbilityAliveManager(AbilityAliveManager abilityAliveManager) {
        this.abilityAliveManager = abilityAliveManager;
    }

    public BasicTransfer getBasicTransfer() {
        return basicTransfer;
    }

    public void setBasicTransfer(BasicTransfer basicTransfer) {
        this.basicTransfer = basicTransfer;
    }

    public CoinsManager getCoinsManager() {
        return coinsManager;
    }

    public void setCoinsManager(CoinsManager coinsManager) {
        this.coinsManager = coinsManager;
    }
}
