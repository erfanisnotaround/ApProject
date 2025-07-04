package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;

public class VpnBehavior extends SystemView implements SystemBehavior , SwitchingPocketMovementInSystems{


    public VpnBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
    }

    @Override
    public void behave(Pocket EntryPocket) {

    }

    @Override
    public void switchPocket(Pocket pocket) {

    }
}
