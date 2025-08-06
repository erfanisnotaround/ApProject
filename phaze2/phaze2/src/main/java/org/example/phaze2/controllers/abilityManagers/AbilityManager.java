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
    AbilityAliveManager aliveAbilities;
    GameContext gameContext;
    WholeMovement wholeMovement;
    CoinsManager coinManager;

    Map<AbilityTypes , AbilityExecutable> executableMap = new HashMap<>();

    public AbilityManager(WholeMovement wholeMovement , CoinsManager coinsManager , FollowerSpawner followerSpawner , GameState gameState) {
        this.aliveAbilities = gameState.getResources().getAbilityAliveManager();
        this.wholeMovement = wholeMovement;
        this.coinManager = coinsManager;
        this.gameContext = new GameContext(wholeMovement , Constants.getInstance().getPockets(), Constants.getInstance().getSystemViews(),
                coinsManager , followerSpawner , aliveAbilities , gameState);

        followerSpawner.SetGameContext(gameContext);
    }
    public void ExecuteAbility(AbilityTypes abilityType){

        if (!executableMap.containsKey(abilityType)) RegisterAbility(abilityType);

        aliveAbilities.AddAliveAbility(abilityType);

        AbilityExecutable executable = executableMap.get(abilityType);
        ExecuteAbility(executable);

    }
    private void ExecuteAbility(AbilityExecutable executable){
        if (!executable.isReady(gameContext)) {
            executable.execute(gameContext);
        }

    }
    private void RegisterAbility(AbilityTypes abilityType){
        AbilityExecutable executable = AbilityFactory.createAbility(abilityType);
        executableMap.put(abilityType, executable );
    }
    public GameContext getGameContext() {
        return gameContext;
    }


}
