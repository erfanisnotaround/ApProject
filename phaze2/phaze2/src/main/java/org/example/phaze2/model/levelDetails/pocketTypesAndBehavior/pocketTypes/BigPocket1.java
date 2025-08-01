package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.controllers.moverController.moveRelated.Releasable;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class BigPocket1 extends Pocket implements Movable , Releasable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Big1.png").toExternalForm());



    public BigPocket1(PocketTypes type) {
        super(type);
        Initialize();
    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(8);
        imagePath = "/org/example/phaze2/images/Big1.png";
        image = new Image(getClass().getResource(imagePath).toExternalForm());
        setImage(image);
        setScaleX(0.2);
        setScaleY(0.2);

        HP = MaxHp = 8;
        speed = 200;
        acceleration = 10;
        angleNeeded = 0;
        preferredType = PortTypes.ALL;
    }

    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {
        pathMover.Initialize();
        pathMover.move(curve , RealSpeed , RealAcceleration , true , multiplier);
    }
    

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier, Connection exitConnection) {

        if (exitConnection != null && exitConnection.getCurve().getAnchors().isEmpty()) {
            move(exitConnection.getCurve() , speed , acceleration * 0 , pocket, multiplier );
        } else if (exitConnection != null && !exitConnection.getCurve().getAnchors().isEmpty()) {
            move(exitConnection.getCurve() , speed  , acceleration , pocket, multiplier);
        }


        return exitConnection;
    }


}
