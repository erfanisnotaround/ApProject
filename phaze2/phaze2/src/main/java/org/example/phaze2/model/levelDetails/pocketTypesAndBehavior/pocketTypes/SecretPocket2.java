package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SecretPocket2 extends Pocket implements Movable {

    ChangeListener<Boolean> inRange;
    BooleanProperty inRangeProperty = new SimpleBooleanProperty(false);
    Timeline AreaCheckerTimeLine;
    double CheckingRadius = 100;


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

        angleNeeded = 0;

    }


    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {

        pathMover.Initialize();

        pathMover.move(curve , RealSpeed, RealAcceleration, true , multiplier);
//
        movingStrategy(pocket , curve);
    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {

        CheckArea(pocketMain);
    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        System.out.println("StopStrategy of Secret2");
        if (AreaCheckerTimeLine!=null) AreaCheckerTimeLine.stop();
    }
    private void CheckArea(PocketMain pocketMain) {
        AreaCheckerTimeLine = new Timeline(new KeyFrame(Duration.millis(10) , actionEvent -> {
            SearchForPocketsInRange(pocketMain);
        }));
        AreaCheckerTimeLine.setCycleCount(-1);
        AreaCheckerTimeLine.play();
    }
    public void SearchForPocketsInRange(PocketMain pocketMain) {
        for (PocketMain pocketCheck : Constants.getInstance().getPockets()){
            if (!pocketMain.getType().equals(PocketTypes.SECRET_2)||pocketCheck.equals(pocketMain) || !pocketMain.isIsItMoved()) continue;

            double deltaX = pocketCheck.centre().getX() - pocketMain.centre().getX();
            double deltaY = pocketCheck.centre().getY() - pocketMain.centre().getY();

            if (Math.hypot(deltaX, deltaY) < CheckingRadius) {
                pocketMain.getMakingGoBehindOrForward().BehindOrNot(pocketCheck , pocketMain.getPathMover().getCurve());
            }
        }
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier, Connection exitConnection) {



        if (exitConnection != null) {
            move(exitConnection.getCurve() , speed , acceleration , pocket, multiplier );
        }


        return exitConnection;
    }
}
