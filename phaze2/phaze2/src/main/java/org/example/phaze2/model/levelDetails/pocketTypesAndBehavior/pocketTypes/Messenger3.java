package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.controllers.moverController.PathPrioritizing;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;
import java.util.Map;

public class Messenger3 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/infinity.png").toExternalForm());

    public Messenger3(PocketTypes type) {
        super(type);
        setCoinsPerEntry(1);
        setImage(image);
        setRotate(-90);
        setScaleX(0.1);
        setScaleY(0.1);
        pathPrioritizing = new PathPrioritizing();
        pathMover = new PathMover(this , 90);

    }

    @Override
    public void move(Curve curve) {
        pathMover.move(curve , 100 , 60 , true);
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
