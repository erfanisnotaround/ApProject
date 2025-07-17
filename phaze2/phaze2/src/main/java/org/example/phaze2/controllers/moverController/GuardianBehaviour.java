package org.example.phaze2.controllers.moverController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.PathData;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public final class GuardianBehaviour implements MoveBehaviour {

    private static final double INNER_R = 300, OUTER_R = 350, SPEED = 120;
    private static final int    SCAN_MS = 33;

    private final HysteresisBlock fwd  = new HysteresisBlock(INNER_R, OUTER_R);
    private final HysteresisBlock back = new HysteresisBlock(INNER_R, OUTER_R);

    private Timeline scanner;
    private PathData path;                // geometry of the current lane
    private PocketMain me;
    private PathMover mover;

    @Override public void start(PocketMain p, PathMover m) {
        this.me    = p; this.mover = m;
        this.path  = m.getPath();

        scanner = new Timeline(new KeyFrame(
                Duration.millis(SCAN_MS), e -> scanAndReact()));
        scanner.setCycleCount(Animation.INDEFINITE);
        scanner.play();
    }
    @Override public void stop() {
        if (scanner != null) { scanner.stop(); scanner = null; }
    }
    @Override public void tick(double dt) {/* nothing – work done in scanner */}

    /* ── inner loop --------------------------------------------------- */
    private void scanAndReact() {

        double myS = mover.getSpeed();
        double bestAhead2 = Double.MAX_VALUE, bestBack2 = Double.MAX_VALUE;

        for (PocketMain other : Constants.getInstance().getPockets()) {
            if (other == me) continue;
            double dx = other.getPlaceOfX() - me.getPlaceOfX();
            double dy = other.getPlaceOfY() - me.getPlaceOfY();
            double d2 = dx*dx + dy*dy;

            // ignore outside OUTER_R
            if (d2 > OUTER_R*OUTER_R) continue;

            double otherS = projectS(other);
            if (otherS > myS) bestAhead2 = Math.min(bestAhead2, d2);
            else              bestBack2  = Math.min(bestBack2,  d2);
        }

        // update hysteresis
        fwd .update(bestAhead2);
        back.update(bestBack2);

        decideSpeed();
    }

    private void decideSpeed() {
        double dir = Math.signum(mover.getSpeed());

        if (fwd.isBlocked() && back.isBlocked()) {
            mover.setSpeed(0);                         // boxed in
        } else if (fwd.isBlocked() && dir >= 0) {
            mover.setSpeed(-SPEED);                    // retreat
        } else if (back.isBlocked() && dir <= 0) {
            mover.setSpeed(+SPEED);                    // advance
        } else if (!fwd.isBlocked() && !back.isBlocked() && dir == 0) {
            mover.setSpeed(+SPEED);                    // resume fwd patrol
        }
    }

    /* simplest projection – sample the path linearly */
    private double projectS(PocketMain p) {
        double bestS = 0, bestD2 = Double.MAX_VALUE;
        int samples = 60;
        double step = path.total() / samples;
        for (int i = 0; i <= samples; i++) {
            double s = i*step;
            var pt = path.pointAt(s);
            double dx = pt.getX() - p.getPlaceOfX();
            double dy = pt.getY() - p.getPlaceOfY();
            double d2 = dx*dx + dy*dy;
            if (d2 < bestD2) { bestD2 = d2; bestS = s; }
        }
        return bestS;
    }
}