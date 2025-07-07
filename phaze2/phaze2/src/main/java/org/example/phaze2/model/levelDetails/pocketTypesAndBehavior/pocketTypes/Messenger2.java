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
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;
import java.util.Map;

public class Messenger2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Triangle.png").toExternalForm());
    public Messenger2(PocketTypes type) {
        super(type);
        setCoinsPerEntry(3);
        setImage(image);
        setScaleX(0.02);
        setScaleY(0.02);

        HP = MaxHp = 3;
        speed = 200;
        acceleration = 20;
        preferredType = PortTypes.TRIANGLE;
        pathMover = new PathMover(this , 0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration) {
//        pathMover.AddingImpactVector(5 , 6);
        pathMover.move(curve , 100 , 60 , true);
    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {

        Connection exitConnection = systemView.behave(this);

        if (exitConnection != null && exitConnection.getFrom().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed, acceleration);
        } else if (exitConnection != null && !exitConnection.getFrom().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed  , acceleration * 0);
        }


        return exitConnection;
    }
}
