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

public class BigPocket2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResourceAsStream("/org/example/phaze2/images/Big2.png"));

    private final double UnitCounter  = 100;
    private final double changePerUnit = 0.5;
    private final int StepsPerUnit = 2000;

    public BigPocket2(PocketTypes type) {
        super(type);
        Initialize();
    }

    @Override
    protected void Initialize() {


        imagePath = "/org/example/phaze2/images/Big2.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.2);
        setScaleY(0.2);

        HP = MaxHp = 10;
        speed = 200;
        acceleration = 0;
        preferredType = PortTypes.ALL;
        angleNeeded = 0;
        setCoinsPerEntry(10);
        coinsPerEntry = 10;

    }

    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {

        pathMover.Initialize();
        movingStrategy(pocket , curve);

        pathMover.move(curve , RealSpeed , RealAcceleration, true , multiplier);


    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {
        System.out.println("we make it ");
        double curveLength = curve.ApproximateLength();
        double units = curveLength/UnitCounter;

        double XChange = units * changePerUnit;
        double YChange = units * changePerUnit;

        pocketMain.getPathMover().AddWholeMoveVector(XChange/StepsPerUnit ,  YChange/StepsPerUnit);
    }

    @Override
    public void StopStrategy(Pocket LastPocket , PocketMain pocketMain) {
        pocketMain.getPathMover().AddWholeMoveVector(0 , 0);
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier, Connection exitConnection) {

        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed , acceleration, pocket, multiplier );
        }


        return exitConnection;
    }
}
