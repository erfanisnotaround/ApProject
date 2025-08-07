package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

public interface SystemBehavior {
    Connection ReleaseBehave(PocketMain EntryPocket , double multiplier);
    void EnterBehave(PocketMain EntryPocket , double multiplier);
}
