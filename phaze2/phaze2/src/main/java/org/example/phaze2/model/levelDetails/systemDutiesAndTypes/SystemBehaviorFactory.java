package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types.*;

public class SystemBehaviorFactory {

    public static SystemView create(System system) {
        return switch (system.getSystemType()){
            case SPY -> new SpyBehavior(system.getSystemType() , system.getNumberOfSubSystems()) ;
            case DESTRUCTIVE -> new DestructiveBehavior(system.getSystemType() , system.getNumberOfSubSystems());
            case VPN -> new VpnBehavior(system.getSystemType() , system.getNumberOfSubSystems());
            case ANTI_VIRUS -> new AntiVirusBehavior(system.getSystemType() , system.getNumberOfSubSystems());
            case MERGER -> new MergerBehavior(system.getSystemType() , system.getNumberOfSubSystems());
            case DISTRIBUTOR -> new DistributeBehavior(system.getSystemType() , system.getNumberOfSubSystems());
        };
    }
}
