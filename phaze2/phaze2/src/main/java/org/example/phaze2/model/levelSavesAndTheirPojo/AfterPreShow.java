package org.example.phaze2.model.levelSavesAndTheirPojo;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.phaze2.controllers.abilityManagers.AbilityManager;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.model.abilities.AbilityBaseType;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AfterPreShow {
    private double wait = 4;
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(wait));
    private List<PocketMain> pocketMains;
    private List<SystemView> systemViews;
    private Set<AbilityExecutorPojo> abilityInfo = new HashSet<>();
    private AbilityManager abilityManager;
    private WholeMovement wholeMovement;
    Pane cnn;

    public AfterPreShow(AbilityManager abilityManager , WholeMovement wholeMovement ) {
        this.abilityManager = abilityManager;
        this.wholeMovement = wholeMovement;
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
        wholeMovement.PutListenersForSystems();
        wholeMovement.getStartSystemView();
        for (PocketMain pocket : pocketMains) {

            pocket.setMovementManager(wholeMovement);
            if (pocket.isIsItMoved() && !pocket.isLost()){
                PathMover pathMover = pocket.getPathMover();
                pocket.getPathMover().move(pathMover.getCurve() , pocket.getSpeed() , pocket.getAcceleration() , true , 1);
                wholeMovement.resumeMovement(pocket , pocket.getPathMover().getCurve().getConnection());

            }
        }
        pocketsInSystems();
    }
    private void pocketsInSystems(){
        for (SystemView systemView : systemViews) {
            wholeMovement.AddToWaitingSend(systemView , null);
        }
    }


    private void loadAbilities(){
        for (AbilityExecutorPojo abilityExecutorPojo : abilityInfo){
            if (abilityExecutorPojo.getAbilityType().getBaseType() != AbilityBaseType.PERIOD_BASE) continue;
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

    public List<SystemView> getSystemViews() {
        return systemViews;
    }

    public void setSystemViews(List<SystemView> systemViews) {
        this.systemViews = systemViews;
    }
}
