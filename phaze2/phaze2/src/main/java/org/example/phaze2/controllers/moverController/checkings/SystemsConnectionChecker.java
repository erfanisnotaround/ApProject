package org.example.phaze2.controllers.moverController.checkings;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SubSystemView;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.*;

public class SystemsConnectionChecker extends AnimationTimer {

    private static final long POLL_NS = 100_000_000;   // 100 ms

    private final List<SystemView> systemViews;
    private final Map<Port, Connection> exitConnections;


    private long lastCheck = 0;

    public SystemsConnectionChecker(GameState gameState) {
        this.systemViews      = gameState.getResources().getSystemViews();
        this.exitConnections  = gameState.getResources().getExitConnections();
    }


    @Override
    public void handle(long now) {
        if (now - lastCheck < POLL_NS) return;
        lastCheck = now;

        for (SystemView sv : systemViews) {
            Bounds systemBounds = sv.getBoundsInParent();

            boolean isSystemFullyConnected = isSystemFullyConnected(sv);

            if (isSystemFullyConnected) {
                onSystemFullyConnected(sv);
            }
            else {
                onSystemNotFullyConnected(sv);

            }

            for (Connection c : exitConnections.values()) {

                if (c.getFromPort().getPortInfo().getSystem().equals(sv) || c.getToPort().getPortInfo().getSystem().equals(sv)) continue;

                boolean theyHit = GeometryUtil.polylineIntersectsRect(c.getCurve() , systemBounds);

                if (theyHit) {
                    c.setCanWeUse(false);

                }else {
                    c.setCanWeUse(true);
                }

            }

        }

    }

    private boolean isSystemFullyConnected(SystemView systemView) {
        for (SubSystemView ssv : systemView.getSubSystems()) {

            if (ssv.DoesItHaveEnterGate()) {
                Port enter = ssv.getEnterPort();
                if (!isPortConnected(enter)) return false;
            }
            if (ssv.DoesItHavaExitGate()) {
                Port exit = ssv.getExitPort();
                if (!isPortConnected(exit)) return false;
            }
        }
        return true;
    }

    private boolean isPortConnected(Port port) {
        Connection c = exitConnections.get(port);
        return c != null && c.getCurve() != null;     // extra null-check for safety
    }


    private void onSystemFullyConnected(SystemView systemView) {

        systemView.setLightBoolean(true);

    }
    private void onSystemNotFullyConnected(SystemView systemView) {

        systemView.setLightBoolean(false);

    }
}
