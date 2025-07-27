package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

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
        angleNeeded = 0;
    }

    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {
        pathMover.move(curve , RealSpeed , RealAcceleration , true , multiplier);

    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier, Connection exitConnection) {


        if (exitConnection != null && exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed , acceleration , pocket, multiplier );
        } else if (exitConnection != null && !exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed  /2  , acceleration  , pocket, multiplier);
        }


        return exitConnection;
    }
}
