package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.controllers.moverController.PathPrioritizing;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.*;
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;
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
        pathMover = new PathMover(this , 0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration) {
        pathMover.move(curve , this.speed, 100 , true);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2400), e -> {}));
        timeline.setCycleCount(1);
        timeline.setOnFinished(e -> {
            pathMover.setSpeed(0);
        });


    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {

        Connection exitConnection = systemView.behave(this);

        if (exitConnection != null && exitConnection.getFrom().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed, acceleration);
        } else if (exitConnection != null && !exitConnection.getFrom().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed/2 , acceleration);
        }


        return exitConnection;
    }
}
