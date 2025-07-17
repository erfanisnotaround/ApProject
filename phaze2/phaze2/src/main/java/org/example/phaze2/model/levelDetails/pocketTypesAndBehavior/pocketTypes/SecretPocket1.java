package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SecretPocket1 extends Pocket implements Movable {

    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Shield.png").toExternalForm());

    private static final double MAX_SPEED   = 1000;  // px / s when lane is free
    private static final double MIN_SPEED   = 10;   // never crawl below this
    private static final double DECEL_STEP  = 20;   // px / s per frame when slowing
    private static final long   POLL_NS     = 40_000_000;


    private final AnimationTimer regulator;     // slow-down brain
    private SystemView targetSystem;
    public SecretPocket1(PocketTypes type) {
        super(type);
        Initialize();

        regulator = buildRegulator();

    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(3);
        imagePath = "/org/example/phaze2/images/Shield.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.04);
        setScaleY(0.04);
        speed = 200;
        acceleration = 0;
        HP = MaxHp = 4;
        preferredType = PortTypes.ALL;
        pathMover = new PathMover(0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        movingStrategy(pocket , curve);
        pathMover.move(curve , speed , acceleration , true );
    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {
        targetSystem = curve.getConnection().getToPort().getPortInfo().getSystem();
        regulator.start();
    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        regulator.stop();
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {
        Connection exitConnection = systemView.behave(pocket);

        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed, acceleration, pocket );
        }


        return exitConnection;
    }

    private AnimationTimer buildRegulator() {
        return new AnimationTimer() {
            private long lastCheck = 0;

            @Override public void handle(long now) {

                /* throttle polling so we don’t run every frame */
                if (now - lastCheck < POLL_NS) return;
                lastCheck = now;

                double currentV = pathMover.getSpeed();

                if (targetSystem != null && !targetSystem.isCapacityEmpty()) {
                    /* destination busy → decelerate down toward MIN_SPEED */
                    double newV = Math.max(MIN_SPEED, currentV - DECEL_STEP);
                    pathMover.setSpeed(newV);
                } else {
                    /* destination free → accelerate smoothly back to MAX_SPEED */
                    double newV = Math.min(MAX_SPEED, currentV + DECEL_STEP);
                    pathMover.setSpeed(newV);
                }
            }
        };
    }
}

