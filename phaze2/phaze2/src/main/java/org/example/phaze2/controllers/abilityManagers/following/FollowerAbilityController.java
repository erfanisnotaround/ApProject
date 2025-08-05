package org.example.phaze2.controllers.abilityManagers.following;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerAbilityHandler;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.*;

public class FollowerAbilityController {

    private final List<Entry> active = new ArrayList<>();
    private final FollowerAbilityHandler handler = new FollowerAbilityHandler();

    private final AnimationTimer ticker = new AnimationTimer() {
        @Override public void handle(long now) { update(); }
    };

    public FollowerAbilityController() {
        ticker.start();
    }

    public void AddFollower(Follower follower, Curve curve) {
        double ratio = handler.getRatio(
                new Point2D(follower.getCenterX(), follower.getCenterY()),
                curve.getPathData());
        Point2D p = handler.getPointAt(ratio, curve.getPathData());
        follower.setCenterX(p.getX());
        follower.setCenterY(p.getY());
        follower.setRatio(ratio);

        curve.AddFollower(follower);
        active.add(new Entry(follower, curve));
    }



    private void update() {
        Iterator<Entry> it = active.iterator();
        while (it.hasNext()) {
            Entry e = it.next();

            if (e.follower.getParent() == null) {
                it.remove(); continue;
            }

            PocketMain pocket = e.curve.getPocketMovingOnIt();
            if (pocket != null) {

                double ratio = pocket.getPathMover().getS();
                if (e.follower.getRatio() - ratio < 1) {
                    if (e.hitPockets.add(pocket)) {
                        e.follower.execute(pocket);
                    }
                }
            } else {
                e.hitPockets.remove(pocket);
            }
        }
    }

    private static boolean intersects(Shape a, Shape b) {
        Shape inter = Shape.intersect(a, b);
        Bounds bb   = inter.getBoundsInLocal();
        return bb.getWidth() > 0 && bb.getHeight() > 0;
    }

    private static final class Entry {
        final Follower follower;
        final Curve curve;
        final Set<PocketMain> hitPockets = new HashSet<>();
        Entry(Follower f, Curve c) { follower = f; curve = c; }
    }
}
