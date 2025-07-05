package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Map;

public class BigPocket2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResourceAsStream("/org/example/phaze2/images/Big2.png"));


    public BigPocket2(PocketTypes type) {
        super(type);
        setLayoutX(200);
        setLayoutY(200);
        setCoinsPerEntry(10);
        setImage(image);
        setScaleX(0.2);
        setScaleY(0.2);
        pathMover = new PathMover(this);
    }

    @Override
    public void move(Curve curve) {
        pathMover.move(curve , 100 , 50 , false);
    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {
        List<Connection> AllConnections = pathPrioritizing.AllSubSystems(systemView , PortTypes.SQUARE);
        if (!AllConnections.isEmpty()) {
            int randomSecondConnection = random.nextInt(AllConnections.size());
            Connection connection = AllConnections.get(randomSecondConnection);

            return connection;

        }
        else return null;
    }
}
