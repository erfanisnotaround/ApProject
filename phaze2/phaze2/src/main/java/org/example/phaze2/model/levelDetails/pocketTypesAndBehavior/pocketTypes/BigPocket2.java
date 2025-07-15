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

public class BigPocket2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResourceAsStream("/org/example/phaze2/images/Big2.png"));

    private final double UnitCounter  = 100;
    private final double changePerUnit = 0.5;
    private final int StepsPerUnit = 200;

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
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        movingStrategy(pocket , curve);
        pathMover.move(curve , speed , acceleration , false);
    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {
        double curveLength = curve.ApproximateLength();
        double units = curveLength/UnitCounter;

        double XChange = units * changePerUnit;
        double YChange = units * changePerUnit;

        pocketMain.getPathMover().AddWholeMoveVector(XChange/StepsPerUnit ,  YChange/StepsPerUnit);
    }

    @Override
    public void StopStrategy(Pocket LastPocket , PocketMain pocketMain) {
        pocketMain.getPathMover().setLatestLineDistance(pocketMain.getPathMover().getCurrentLineDistance());
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
