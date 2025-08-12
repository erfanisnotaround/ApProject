package org.example.phaze2.controllers.abilityManagers;

import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.abilityManagers.following.FollowerSpawner;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.hudModels.AbilityAliveManager;
import org.example.phaze2.model.hudModels.CoinsManager;

import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.Map;

public class AbilityManager {
    private AbilityAliveManager aliveAbilities;
    private GameContext gameContext;
    private WholeMovement wholeMovement;
    private CoinsManager coinManager;

    private Map<AbilityTypes , AbilityExecutable> executableMap = new HashMap<>();

    public AbilityManager(WholeMovement wholeMovement , CoinsManager coinsManager , FollowerSpawner followerSpawner , GameState gameState) {
        this.aliveAbilities = gameState.getHudStuffDAta().getAbilityAliveManager();
        this.wholeMovement = wholeMovement;
        this.coinManager = coinsManager;
        this.gameContext = new GameContext(wholeMovement , gameState.getResources().getPockets(), gameState.getResources().getSystemViews(),
                coinsManager , followerSpawner , aliveAbilities , gameState);

        followerSpawner.SetGameContext(gameContext);
    }
    public void ExecuteAbility(AbilityTypes abilityType){

        if (!executableMap.containsKey(abilityType)) RegisterAbility(abilityType);

        aliveAbilities.AddAliveAbility(abilityType);

        AbilityExecutable executable = executableMap.get(abilityType);
        ExecuteAbility(executable);

    }
    public void ExecuteAbility(AbilityExecutable executable){
        if (!executable.isReady(gameContext)) {
            executable.execute(gameContext  , 0);
        }

    }
    public void RegisterAbility(AbilityTypes abilityType){
        AbilityExecutable executable = AbilityFactory.createAbility(abilityType);
        executableMap.put(abilityType, executable );
    }
    public GameContext getGameContext() {
        return gameContext;
    }
    public Map<AbilityTypes , AbilityExecutable> getExecutableMap() {
        return executableMap;
    }
    public AbilityExecutable getAbilityExecutable(AbilityTypes abilityType) {
        return executableMap.get(abilityType);
    }



}
