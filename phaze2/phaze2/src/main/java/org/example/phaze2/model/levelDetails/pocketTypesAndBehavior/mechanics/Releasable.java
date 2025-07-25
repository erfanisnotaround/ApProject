package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public interface Releasable {
    Connection ReleaseAct(PocketMain pocket, SystemView systemView , Map<Port, Connection> exitConnections , double multiplier);
}
