package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SecretPocket1 extends Pocket implements Movable {

    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Shield.png").toExternalForm());

    public SecretPocket1(PocketTypes type) {
        super(type);
        Initialize();

    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(3);
        imagePath = "/org/example/phaze2/images/Shield.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.02);
        setScaleY(0.02);
        speed = 200;
        acceleration = 0;
        HP = MaxHp = 4;
        preferredType = PortTypes.ALL;
        pathMover = new PathMover(this , 0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration) {
        pathMover.move(curve , 30 , 100 , false );
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {
        Connection exitConnection = systemView.behave(pocket);

        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed, acceleration);
        }


        return exitConnection;
    }
}
