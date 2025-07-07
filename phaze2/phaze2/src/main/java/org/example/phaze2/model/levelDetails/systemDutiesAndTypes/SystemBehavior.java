package org.example.phaze2.model.levelDetails.systemDutiesAndTypes;

import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.portConnectingDetails.Connection;

public interface SystemBehavior {
    Connection behave(Pocket EntryPocket);
}
