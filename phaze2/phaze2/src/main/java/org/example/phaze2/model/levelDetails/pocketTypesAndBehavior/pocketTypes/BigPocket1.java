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
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Releasable;
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
        acceleration = 0;
        preferredType = PortTypes.ALL;

        pathMover = new PathMover(this , 0);
    }

    @Override
    public void move(Curve curve, double speed, double acceleration) {
        System.out.println("Moving curve");
        pathMover.move(curve , speed , acceleration , true);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2300), event -> {
//        }));
//        timeline.setCycleCount(1);
//        timeline.play();
//        timeline.setOnFinished(e -> {
////            pathMover.restart(-100);
//            pathMover.reverse();
//        });
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
