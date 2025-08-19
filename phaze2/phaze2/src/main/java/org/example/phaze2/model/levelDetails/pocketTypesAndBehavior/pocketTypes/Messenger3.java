package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class Messenger3 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/infinity.png").toExternalForm());

    PauseTransition pause;
    ChangeListener<Boolean> Collide;
    public Messenger3(PocketTypes type , GameState gameState) {
        super(type , gameState);
        Initialize();

    }

    @Override
    protected void Initialize() {

        imagePath = "/org/example/phaze2/images/infinity.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setRotate(-90);
        setScaleX(0.15);
        setScaleY(0.15);

        HP = MaxHp = 1;
        speed = 200;
        acceleration = 5;
        preferredType = PortTypes.INFINITY;
        angleNeeded = 0;
        setCoinsPerEntry(1);
        coinsPerEntry = 1;
    }

    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {

        pathMover.Initialize();
        movingStrategy(pocket , curve);
        pathMover.move(curve , RealSpeed , RealAcceleration , true , multiplier);

    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {

        if (pocketMain.getType() != PocketTypes.Messenger_3 && pocketMain.getType() != PocketTypes.SECRET_MESSENGER) return;
        Collide = (observableValue, aBoolean, t1) -> {
            if (t1) {
                pocketMain.getPathMover().moveBackward();

            }
            pocketMain.setIsItCollided(false);
        };
        pocketMain.isItCollidedProperty().addListener(Collide);
    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        if (Collide != null) pocketMain.isItCollidedProperty().removeListener(Collide);
        if (pause != null) pause.stop();
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier, Connection exitConnection) {


        if (exitConnection != null && exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed  , acceleration , pocket, multiplier );
        } else if (exitConnection != null && !exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed  , -1 * acceleration , pocket, multiplier );
        }


        return exitConnection;
    }

    @Override
    public void EnterAct(SystemView systemView) {

    }
}
