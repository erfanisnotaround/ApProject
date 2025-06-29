package org.example.phaze2.model.levelDetails.systemDuties;

import org.example.phaze2.model.constants.SystemTypes;

public class SystemBehaviorFactory {
    public static SystemBehavior create(SystemTypes systemType) {
        return switch (systemType){
            case SPY -> new SpyBehavior() ;
            case DESTRUCTIVE -> new DestructiveBehavior();
            case VPN -> new VpnBehavior();
            case ANTI_VIRUS -> new AntiVirusBehavior();
            case MERGER -> new MergerBehavior();
            case DISTRIBUTOR -> new DistributeBehavior();
        };
    }
}
