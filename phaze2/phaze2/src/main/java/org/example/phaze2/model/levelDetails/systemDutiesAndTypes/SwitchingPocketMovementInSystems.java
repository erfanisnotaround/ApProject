package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface SwitchingPocketMovementInSystems {
    public  Pocket  switchPocket(PocketMain pocket, PocketTypes type);
}
