package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class Messenger2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Triangle.png").toExternalForm());
    public Messenger2(PocketTypes type) {
        super(type);
        Initialize();

    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(3);
        imagePath = "/org/example/phaze2/images/Triangle.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));

        setScaleX(0.04);
        setScaleY(0.04);
        HP = MaxHp = 3;
        speed = 200;
        acceleration = 20;
        preferredType = PortTypes.TRIANGLE;
        angleNeeded = 0;
    }

    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {
//        pathMover.AddingImpactVector(5 , 6);

        pathMover.Initialize();

        pathMover.move(curve , RealSpeed, RealAcceleration , true , multiplier);
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier, Connection exitConnection) {


        if (exitConnection != null && exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed  , acceleration , pocket, multiplier );
        } else if (exitConnection != null && !exitConnection.getFromPort().getPortInfo().getType().equals(preferredType)) {
            move(exitConnection.getCurve() , speed   , acceleration  * 0, pocket, multiplier);
        }


        return exitConnection;
    }
}
