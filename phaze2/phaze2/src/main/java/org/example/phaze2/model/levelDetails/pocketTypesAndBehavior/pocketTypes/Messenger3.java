package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.util.Duration;
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

public class Messenger3 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/infinity.png").toExternalForm());

    PauseTransition pause;
    ChangeListener<Boolean> Collide;
    public Messenger3(PocketTypes type) {
        super(type);
        Initialize();

    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(1);
        imagePath = "/org/example/phaze2/images/infinity.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setRotate(-90);
        setScaleX(0.1);
        setScaleY(0.1);

        HP = MaxHp = 1;
        speed = 200;
        acceleration = 15;
        preferredType = PortTypes.INFINITY;
        pathMover = new PathMover(90);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        movingStrategy(pocket , curve);
        pathMover.move(curve , 100 , 60 , true);

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            System.out.println(pocket.getType() + " make It happen");
            pocket.setIsItCollided(true);
        });
        pause.play();
    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {
            Collide = (observableValue, aBoolean, t1) -> {
                if (t1) {
                    pocketMain.getPathMover().reverse();
                    pocketMain.setIsItCollided(false);
                    System.out.println("mio2 " + pocketMain.getType());
                }
            };
            pocketMain.isItCollidedProperty().addListener(Collide);
    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        if (Collide != null) pocketMain.isItCollidedProperty().removeListener(Collide);
        if (pause != null) pause.stop();
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {

        Connection exitConnection = systemView.behave(pocket);

        if (exitConnection != null && exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed, acceleration, pocket);
        } else if (exitConnection != null && !exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed , -1 * acceleration, pocket );
        }


        return exitConnection;
    }
}
