package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public interface SwitchingPocketMovementInSystems {
    public Pocket  switchPocket(Pocket pocket, PocketTypes type);
}
