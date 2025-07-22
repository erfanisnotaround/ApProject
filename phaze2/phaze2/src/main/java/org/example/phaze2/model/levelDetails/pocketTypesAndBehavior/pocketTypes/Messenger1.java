package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class Messenger1 extends Pocket implements Movable {
    public Messenger1(PocketTypes type) {
        super(type);
        Initialize();
    }

    @Override
    protected void Initialize() {
        imagePath = "/org/example/phaze2/images/square.png";
        setCoinsPerEntry(2);
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.03);
        setScaleY(0.03);
        speed = 200;
        acceleration = 0;
        HP = MaxHp = 2;
        preferredType = PortTypes.SQUARE;
        pathMover = new PathMover(0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        pathMover.move(curve , this.speed, 100 , true);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2400), e -> {}));
        timeline.setCycleCount(1);
        timeline.setOnFinished(e -> {
            pathMover.setSpeed(0);
        });


    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {

        Connection exitConnection = systemView.behave(pocket);

        if (exitConnection != null && exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed, acceleration, pocket);
        } else if (exitConnection != null && !exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed/2 , acceleration, pocket );
        }


        return exitConnection;
    }
}
