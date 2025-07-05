package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Map;

public class SecretMessenger extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/lock.png").toExternalForm());
    public SecretMessenger(PocketTypes type) {
        super(type);
        chooseTheMoveBehavior();
        setImage(image);
        setScaleX(0.1);
        setScaleY(0.1);
        setCoinsPerEntry(5);
        pathMover = new PathMover(this);
    }
    private void chooseTheMoveBehavior(){
        int randomBehavior = random.nextInt(PocketTypeGroup.MESSENGER.getGroups().size());
        movable = PocketMoveFactory.givePocketMovementType(PocketTypeGroup.MESSENGER.getGroups().get(randomBehavior));
    }

    @Override
    public void move(Curve curve) {

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
