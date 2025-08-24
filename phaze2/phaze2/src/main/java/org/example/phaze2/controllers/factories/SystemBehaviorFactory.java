package org.example.phaze2.controllers.factories;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types.*;

public class SystemBehaviorFactory {

    public static SystemView create(System system , GameState gameState , System level) {
        return switch (system.getSystemType()){
            case SPY -> new SpyBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState , level) ;
            case DESTRUCTIVE -> new DestructiveBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState , level);
            case VPN -> new VpnBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState , level);
            case ANTI_VIRUS -> new AntiVirusBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState , level);
            case MERGER -> new MergerBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState , level);
            case DISTRIBUTOR -> new DistributeBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState , level);
        };
    }
}
