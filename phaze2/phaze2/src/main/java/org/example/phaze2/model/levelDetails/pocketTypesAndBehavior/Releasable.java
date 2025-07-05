package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior;

import javafx.scene.Node;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public interface Releasable {
    Connection ReleaseAct(SystemView systemView , Map<Node, PortInfo> portInfoMap , Map<PortInfo , Connection> exitConnections);
}
