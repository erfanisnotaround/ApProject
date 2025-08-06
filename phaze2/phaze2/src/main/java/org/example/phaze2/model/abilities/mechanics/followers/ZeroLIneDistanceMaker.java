package org.example.phaze2.model.abilities.mechanics.followers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAdder;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class ZeroLIneDistanceMaker extends Follower{

    public ZeroLIneDistanceMaker(FollowerType FollowerType) {
        super(FollowerType);
    }

    @Override
    public void execute(PocketMain pocket) {
        Point2D latestLineDistance = pocket.getPathMover().getLatestLineDistance();
        pocket.distract(- latestLineDistance.getX(), - latestLineDistance.getY());




    }

}
