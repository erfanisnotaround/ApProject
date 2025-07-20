package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.sql.Time;
import java.util.Map;

public class SecretPocket2 extends Pocket implements Movable {

    ChangeListener<Boolean> inRange;
    BooleanProperty inRangeProperty = new SimpleBooleanProperty(false);
    Timeline AreaCheckerTimeLine;
    double CheckingRadius = 300;

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
        speed = 50;
        acceleration = 0;
        preferredType = PortTypes.ALL;

        pathMover = new PathMover(0);

    }


    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        movingStrategy(pocket , curve);
        pathMover.move(curve , speed , acceleration , false);
    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {
        inRange = (ChangeListener<Boolean>) (observable, oldValue, newValue) -> {

            pocketMain.getPathMover().reverse();
        };

        inRangeProperty.addListener(inRange);
        CheckArea(pocketMain);
    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        if (inRange!=null) inRangeProperty.removeListener(inRange);
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
            if (pocketCheck.equals(pocketMain) && !pocketMain.isIsItMoved()) continue;

            double deltaX = pocketCheck.getPlaceOfX() - pocketMain.getPlaceOfX();
            double deltaY = pocketCheck.getPlaceOfY() - pocketMain.getPlaceOfY();

            if (Math.hypot(deltaX, deltaY) < CheckingRadius) {
                inRangeProperty.set(!inRangeProperty.get());
            }
        }
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
