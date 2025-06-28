package org.example.phaze2.model.portConnectingDetails;

import org.example.phaze2.model.levelDetails.PortInfo;

public interface ConnectionValidJudge {
    boolean isConnectionValid(PortInfo From , PortInfo To , double wireLength);
}
