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
import java.util.Random;

public class Messenger2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Triangle.png").toExternalForm());
    public Messenger2(PocketTypes type) {
        super(type);
        setCoinsPerEntry(3);
        setImage(image);
        setScaleX(0.02);
        setScaleY(0.02);

        HP = MaxHp = 3;

        pathMover = new PathMover(this , 0);
        pathPrioritizing = new PathPrioritizing();
    }

    @Override
    public void move(Curve curve) {
//        pathMover.AddingImpactVector(5 , 6);
        pathMover.move(curve , 100 , 60 , true);
    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(systemView , PortTypes.TRIANGLE);
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(systemView , PortTypes.TRIANGLE);

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
