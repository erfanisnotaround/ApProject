package org.example.phaze2.controllers.abilityManagers.following;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerAbilityHandler;
import org.example.phaze2.model.abilities.mechanics.followers.ZeroAccelerationMaker;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FollowerAbilityController {


    List<Follower> followers = new ArrayList<>();
    FollowerAbilityHandler handler;

    public void AddFollower(Follower follower, Curve curve) {

        followers.add(follower);
        curve.AddFollower(follower);
        follower.setRatio(handler.getRatio(new Point2D(follower.getCenterX() , follower.getCenterY()) , curve.getPathData()));

        Point2D pointAt = handler.getPointAt(follower.getRatio() , curve.getPathData());
        follower.setCenterX(pointAt.getX());
        follower.setCenterY(pointAt.getY());


    }
    private void HandleFollower(Follower follower , Curve curve) {
        List<PocketMain> current = new ArrayList<>();
        List<PocketMain> previous = new ArrayList<>();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10) , actionEvent -> {
            PocketMain pocketMain = curve.getPocketMovingOnIt();
            if (pocketMain != null) {
                if (intersects(pocketMain.getHitBox() , follower)){
                    current.add(pocketMain);
                }
            }
            for (PocketMain pocketMain1 : current) {
                if (!previous.contains(pocketMain1)) {
                    follower.execute(pocketMain1);
                }
            }
            previous.clear();
            previous.addAll(current);
            current.clear();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private boolean intersects(Shape shape1 , Shape shape2) {
        Shape shape = Shape.intersect(shape1, shape2);

        Bounds bounds1 = shape.getBoundsInLocal();

        return bounds1.getWidth() > 0 && bounds1.getHeight() > 0;
    }
}
