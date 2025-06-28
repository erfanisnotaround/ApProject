package org.example.phaze2.model.portConnectingDetails;

import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.levelDetails.PortInfo;

public class ConnectionChecker implements  ConnectionValidJudge{
    private final WireManager wireManager;

    public ConnectionChecker(WireManager wireManager) {
        this.wireManager = wireManager;
    }


    @Override
    public boolean isConnectionValid(PortInfo From, PortInfo To, double wireLength) {
        return From.type == To.type && wireManager.canUse(wireLength);
    }
}
