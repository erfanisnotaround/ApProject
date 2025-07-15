package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
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
        pathMover = new PathMover(this , 90);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        pathMover.move(curve , 100 , 60 , true);
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
