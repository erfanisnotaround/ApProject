package org.example.phaze2.model.saversOfGame;

import org.example.phaze2.controllers.abilityManagers.AbilityManager;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAbilityController;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAdder;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.hudModels.AbilityAliveManager;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.levelSavesAndTheirPojo.AfterPreShow;

public final class LoadAndSaveCompleterNecessaries {
    private FollowerAdder followerAdder;
    private FollowerAbilityController followerAbilityController;
    private AbilityAliveManager abilityAliveManager;
    private ConnectionUI connectionUI;
    private CoinsManager coinsManager;
    private AbilityManager abilityManager;
    private AfterPreShow afterPreShow;
    private GameState gameState;

    public LoadAndSaveCompleterNecessaries(FollowerAdder followerAdder  , ConnectionUI connectionUI , AbilityAliveManager abilityAliveManager,
                                           CoinsManager coinsManager , AbilityManager abilityManager, AfterPreShow afterPreShow,
                                           GameState gameState) {
        this.followerAdder = followerAdder;
        followerAbilityController = followerAdder.getFollowerAbilityController();
        this.connectionUI = connectionUI;
        this.abilityAliveManager = abilityAliveManager;
        this.coinsManager = coinsManager;
        this.abilityManager = abilityManager;
        this.afterPreShow = afterPreShow;
        this.gameState = gameState;
    }

    public FollowerAdder getFollowerAdder() {
        return followerAdder;
    }

    public FollowerAbilityController getFollowerAbilityController() {
        return followerAbilityController;
    }
    public ConnectionUI getConnectionUI() {
        return connectionUI;
    }
    public AbilityAliveManager getAbilityAliveManager() {
        return abilityAliveManager;
    }

    public CoinsManager getCoinsManager() {
        return coinsManager;
    }

    public void setCoinsManager(CoinsManager coinsManager) {
        this.coinsManager = coinsManager;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public AfterPreShow getAfterPreShow() {
        return afterPreShow;
    }

    public void setAfterPreShow(AfterPreShow afterPreShow) {
        this.afterPreShow = afterPreShow;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
