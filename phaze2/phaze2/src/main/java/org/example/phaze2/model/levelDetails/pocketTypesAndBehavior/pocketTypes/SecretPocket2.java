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

public class SecretPocket2 extends Pocket implements Movable {

    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Mecha_Core.png").toExternalForm());
    public SecretPocket2(PocketTypes type) {
        super(type);
        Initialize();
    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(4);
        imagePath = "/org/example/phaze2/images/Mecha_Core.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.2);
        setScaleY(0.2);
        HP = MaxHp = 6;
        speed = 200;
        acceleration = 0;
        preferredType = PortTypes.ALL;

        pathMover = new PathMover(this , 0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        pathMover.move(curve , 100 , 30 , false);
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {
        Connection exitConnection = systemView.behave(pocket);

        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed, acceleration, pocket );
        }


        return exitConnection;
    }
}
