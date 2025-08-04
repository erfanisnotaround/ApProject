package org.example.phaze2.model.abilities.mechanics.followers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class ZeroLIneDistanceMaker extends Follower{

    public ZeroLIneDistanceMaker(FollowerType FollowerType) {
        super(FollowerType);
    }

    @Override
    void execute(PocketMain pocket) {
        Point2D currentLineDistance = pocket.getPathMover().getCurrentLineDistance();
        pocket.getPathMover().setLatestLineDistance(new Point2D(0 , 0));
        pocket.distract(-currentLineDistance.getX() , -currentLineDistance.getY());



    }


}
