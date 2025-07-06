package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.controllers.moverController.PathPrioritizing;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.*;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Messenger1 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/square.png").toExternalForm());
    public Messenger1(PocketTypes type) {
        super(type);
        setCoinsPerEntry(2);
//        pathMover = new PathMover(this);
        setImage(image);
        setScaleX(0.03);
        setScaleY(0.03);


        pathMover = new PathMover(this);
        pathPrioritizing = new PathPrioritizing();

    }
    double speed = 200;

    @Override
    public void move(Curve curve) {
        pathMover.move(curve , speed , 10 , true);


    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {

        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(systemView , PortTypes.SQUARE);
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(systemView , PortTypes.SQUARE);


        if (!firstConnections.isEmpty()) {

            int randomFirstConnection = random.nextInt(firstConnections.size());
            Connection connection = firstConnections.get(randomFirstConnection);

            move(connection.getCurve());

            return connection;

        } else if (!secondConnections.isEmpty()) {

            int randomSecondConnection = random.nextInt(secondConnections.size());
            Connection connection = secondConnections.get(randomSecondConnection);

            move(connection.getCurve());
            return connection;

        }
        else return null;
    }
}
