package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.AreaChecker;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.PocketSwitchManager;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class AntiVirusBehavior extends SystemView implements SystemBehavior , AreaChecker , SwitchingPocketMovementInSystems {

    private final double RadiusOfCheckingArea = 2000;

    public AntiVirusBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000) ,event -> {
            checkArea(RadiusOfCheckingArea);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());


        if (!firstConnections.isEmpty()) {

            int randomFirstConnection = random.nextInt(firstConnections.size());
            Connection connection = firstConnections.get(randomFirstConnection);

            return connection;

        } else if (!secondConnections.isEmpty()) {

            int randomSecondConnection = random.nextInt(secondConnections.size());
            Connection connection = secondConnections.get(randomSecondConnection);

            return connection;

        }
        else return null;
    }

    @Override
    public void checkArea(double radius) {
        List<Pocket> pockets = Constants.getInstance().getPockets();
        for (Pocket pocket : pockets) {
            if (distance(pocket.getLayoutX() , pocket.getLayoutY() , getLayoutX() , getLayoutY()) < RadiusOfCheckingArea && pocket.isIsItMoved()) {
                switchPocket(pocket);
            }
        }
    }

    @Override
    public void switchPocket(Pocket pocket) {
        System.out.println("switchPocket");
        int choosePocket = random.nextInt(PocketTypeGroup.MESSENGER.getGroups().size());
        PocketTypes selectedPocketType = PocketTypeGroup.MESSENGER.getGroups().get(choosePocket);
        Pocket selectedPocket = PocketSwitchManager.switchPocket(pocket, selectedPocketType);
        int indexOfFirstPocket = Constants.getInstance().getPockets().indexOf(pocket);
        Constants.getInstance().getPockets().set(indexOfFirstPocket , selectedPocket);
    }


    private double distance(double x1, double y1, double x2, double y2) {
        Point2D point1 = new Point2D(x1, y1);
        Point2D point2 = new Point2D(x2, y2);

        return point1.distance(point2);
    }

    public double getRadiusOfCheckingArea() {
        return RadiusOfCheckingArea;
    }


}
