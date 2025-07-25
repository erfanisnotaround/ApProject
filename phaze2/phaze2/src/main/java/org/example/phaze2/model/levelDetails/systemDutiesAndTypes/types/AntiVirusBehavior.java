package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.AreaChecker;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class AntiVirusBehavior extends SystemView implements SystemBehavior , AreaChecker , SwitchingPocketMovementInSystems {
    private final double coolDownTime = 6;
    private final double RadiusOfCheckingArea = 2000;
    Timeline timeline;
    PauseTransition cooldown = new PauseTransition(Duration.seconds(coolDownTime));
    public AntiVirusBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);



        timeline = new Timeline(new KeyFrame(Duration.millis(100) ,event -> {
            if (checkArea(RadiusOfCheckingArea)){
                timeline.stop();
                cooldown.play();
                cooldown.setOnFinished(e -> {
                    timeline.play();
                });
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public Connection behave(PocketMain EntryPocket, double multiplier) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        return getConnection(firstConnections , secondConnections);
    }
    public Connection getConnection(List<Connection> firstConnections, List<Connection> secondConnections) {

        if (!firstConnections.isEmpty()) {
            return firstConnections.get(random.nextInt(firstConnections.size()));

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(random.nextInt(secondConnections.size()));

        }
        else return null;
    }

    @Override
    public boolean checkArea(double radius) {
        List<PocketMain> pockets = Constants.getInstance().getPockets();
        for (PocketMain pocket : pockets) {
            if (distance(pocket.getLayoutX() , pocket.getLayoutY() , getLayoutX() , getLayoutY()) < RadiusOfCheckingArea && pocket.isIsItMoved()) {
                if (pocket.getType() != PocketTypes.SECRET_MESSENGER) switchPocket(pocket, PocketTypeGroup.MESSENGER.getGroups().get(random.nextInt(PocketTypeGroup.MESSENGER.getGroups().size())));
                return true;
            }
        }
        return false;
    }
//
//    @Override
//    public Pocket switchPocket(PocketMain pocket, PocketTypes type) {
//
//        Pocket selectedPocket = PocketSwitchManager.switchPocket(pocket, type);
//        int indexOfFirstPocket = Constants.getInstance().getPockets().indexOf(pocket);
//        Constants.getInstance().getPockets().set(indexOfFirstPocket , selectedPocket);
//
//        return selectedPocket;
//    }


    private double distance(double x1, double y1, double x2, double y2) {
        Point2D point1 = new Point2D(x1, y1);
        Point2D point2 = new Point2D(x2, y2);

        return point1.distance(point2);
    }

    public double getRadiusOfCheckingArea() {
        return RadiusOfCheckingArea;
    }


    @Override
    public PocketMain switchPocket(PocketMain pocket, PocketTypes type) {
        pocket.setBehaviour(type);
        return pocket;
    }
}
