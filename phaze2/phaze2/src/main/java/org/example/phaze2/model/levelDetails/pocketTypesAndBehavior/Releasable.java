package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior;

import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.w3c.dom.Node;

import java.util.Map;

public interface Releasable {
    Connection ReleaseAct(SystemView systemView , Map<Node , PortInfo> portInfoMap , Map<PortInfo , Connection> exitConnections);
}
