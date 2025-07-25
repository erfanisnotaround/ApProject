package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.SpeedCalculatorForPocketSecret1;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SecretPocket1 extends Pocket implements Movable {

    private Image image = new Image(getClass().getResource("/org/example/phaze2/images/Shield.png").toExternalForm());

    private double multiplier = 1.0;
    private static final double MAX_SPEED   = 1000;  // px / s when lane is free
    private static final double MIN_SPEED   = 10;   // never crawl below this
    private static final double DECEL_STEP  = 20;   // px / s per frame when slowing
    private static final long   POLL_NS     = 40_000_000;

    private boolean IsRunning = false;
    SpeedCalculatorForPocketSecret1 speedCalculator = new SpeedCalculatorForPocketSecret1(this);


    private AnimationTimer regulator;     // slow-down brain
    private SystemView targetSystem;
    public SecretPocket1(PocketTypes type) {
        super(type);
        Initialize();


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
        angleNeeded = 0;
        pathMover = new PathMover(angleNeeded);
    }

    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {
        movingStrategy(pocket , curve);

        pathMover.move(curve , RealSpeed * multiplier, RealAcceleration * multiplier, true , multiplier);
        if (!targetSystem.isCapacityEmpty()){
            pathMover.setSpeed(speedCalculator.calculateSpeed(targetSystem , multiplier) * multiplier);
        }

    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {

        targetSystem = curve.getConnection().getToPort().getPortInfo().getSystem();
        if (regulator != null) regulator.stop();     // stop previous
        regulator = buildRegulator();                // brand-new timer
        regulator.start();
    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        if (regulator != null) {
            regulator.stop();
            regulator = null;

        }
    }


    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier) {
        Connection exitConnection = systemView.behave(pocket , multiplier );

        this.multiplier = multiplier;
        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed , acceleration , pocket, multiplier );
        }


        return exitConnection;
    }

    private AnimationTimer buildRegulator() {
        return new AnimationTimer() {
            private long lastCheck = 0;

            @Override public void handle(long now) {

                if (now - lastCheck < POLL_NS / multiplier) return;
                lastCheck = now;

                if (getPathMover().getPocketMain()!=null){
                    if (!getPathMover().getPocketMain().isIsItMoved()) return;
                }

                double currentV = pathMover.getSpeed();

                if (targetSystem != null && !targetSystem.isCapacityEmpty()) {
                    double newV = speedCalculator.calculateSpeed(targetSystem , multiplier);
                    pathMover.setSpeed(newV * multiplier);

                } else {
                    pathMover.setSpeed(speed * multiplier);
                }
            }
        };
    }
}

