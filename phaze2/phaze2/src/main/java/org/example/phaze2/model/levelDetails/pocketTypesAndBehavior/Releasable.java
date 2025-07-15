package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior;

import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public interface Releasable {
    Connection ReleaseAct(PocketMain pocket, SystemView systemView , Map<Port, Connection> exitConnections);
}
