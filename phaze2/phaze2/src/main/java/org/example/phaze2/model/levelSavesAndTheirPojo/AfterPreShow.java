package org.example.phaze2.model.levelSavesAndTheirPojo;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.phaze2.controllers.abilityManagers.AbilityManager;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AfterPreShow {
    private double wait = 10;
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(wait));
    private List<PocketMain> pocketMains;
    private Set<AbilityExecutorPojo> abilityInfo = new HashSet<>();
    private AbilityManager abilityManager;
    Pane cnn;

    public AfterPreShow(AbilityManager abilityManager ) {
        this.abilityManager = abilityManager;
//        this.cnn = container;
    }

    public void preShow() {
        pauseTransition.setOnFinished(event -> {
            SetPockets();
            loadAbilities();
        });
        pauseTransition.playFromStart();
    }
    private void SetPockets(){
        for (PocketMain pocket : pocketMains) {
            System.out.println("after" + pocket.isIsItMoved());
            pocket.setLayoutX(500);
            pocket.setLayoutY(500);
            if (pocket.isIsItMoved()){
                PathMover pathMover = pocket.getPathMover();
                pocket.getPathMover().move(pathMover.getCurve() , pocket.getSpeed() , pocket.getAcceleration() , true , 1);
            }
        }
    }
    private void loadAbilities(){
        for (AbilityExecutorPojo abilityExecutorPojo : abilityInfo){
            AbilityExecutable abilityExecutable = abilityManager.getAbilityExecutable(abilityExecutorPojo.getAbilityType());
            abilityExecutable.setLastUsed(abilityManager.getGameContext().now() - abilityExecutorPojo.getTimeBetweenUsedANdNow());
            abilityExecutable.resume(abilityManager.getGameContext() , abilityExecutorPojo.getTimeRemaining());
        }
    }


    public List<PocketMain> getPocketMains() {
        return pocketMains;
    }

    public void setPocketMains(List<PocketMain> pocketMains) {
        this.pocketMains = pocketMains;
    }

    public Set<AbilityExecutorPojo> getAbilityInfo() {
        return abilityInfo;
    }

    public void setAbilityInfo(Set<AbilityExecutorPojo> abilityInfo) {
        this.abilityInfo = abilityInfo;
    }
}
