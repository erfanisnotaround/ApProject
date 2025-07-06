package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.controllers.moverController.PathPrioritizing;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Releasable;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class BigPocket1 extends Pocket implements Movable , Releasable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Big1.png").toExternalForm());


    public BigPocket1(PocketTypes type) {
        super(type);
        pathPrioritizing = new PathPrioritizing();
        setImage(image);
        setScaleX(0.2);
        setScaleY(0.2);
        pathMover = new PathMover(this);
    }


    @Override
    public void move(Curve curve) {
        System.out.println("Moving curve");
        pathMover.move(curve , 100 , 50 , true);
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
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {
        List<Connection> AllConnections = pathPrioritizing.AllSubSystems(systemView , PortTypes.SQUARE);
        if (!AllConnections.isEmpty()) {
            int randomSecondConnection = random.nextInt(AllConnections.size());
            Connection connection = AllConnections.get(randomSecondConnection);

            return connection;

        }
        else return null;
    }
}
