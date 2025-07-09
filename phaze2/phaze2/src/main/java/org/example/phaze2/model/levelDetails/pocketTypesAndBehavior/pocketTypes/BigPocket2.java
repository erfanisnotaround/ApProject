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

public class BigPocket2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResourceAsStream("/org/example/phaze2/images/Big2.png"));


    public BigPocket2(PocketTypes type) {
        super(type);
        Initialize();
    }

    @Override
    protected void Initialize() {
        setLayoutX(200);
        setLayoutY(200);
        setCoinsPerEntry(10);
        imagePath = "/org/example/phaze2/images/Big2.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.2);
        setScaleY(0.2);

        HP = MaxHp = 10;
        speed = 200;
        acceleration = 0;
        preferredType = PortTypes.ALL;

        pathMover = new PathMover(this , 0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration) {
        pathMover.move(curve , 600 , 50 , false);
    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {
        Connection exitConnection = systemView.behave(this);

        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed, acceleration);
        }


        return exitConnection;
    }
}
