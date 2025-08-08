package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types.*;

public class SystemBehaviorFactory {

    public static SystemView create(System system , GameState gameState) {
        return switch (system.getSystemType()){
            case SPY -> new SpyBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState) ;
            case DESTRUCTIVE -> new DestructiveBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState);
            case VPN -> new VpnBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState);
            case ANTI_VIRUS -> new AntiVirusBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState);
            case MERGER -> new MergerBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState);
            case DISTRIBUTOR -> new DistributeBehavior(system.getSystemType() , system.getNumberOfSubSystems() , gameState);
        };
    }
}
