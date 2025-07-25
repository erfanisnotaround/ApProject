package org.example.phaze2.controllers.collisionAndWinning;

import javafx.geometry.Point2D;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class CollisionPair {
    private final Point2D impactPoint;
    private final PocketMain a, b;
    CollisionPair(PocketMain a, PocketMain b , Point2D impactPoint) {
        if (System.identityHashCode(a) < System.identityHashCode(b)) {
            this.a = a; this.b = b;
        } else {
            this.a = b; this.b = a;
        }
        this.impactPoint = impactPoint;
    }
    @Override public boolean equals(Object o) {
        if (!(o instanceof CollisionPair)) return false;
        CollisionPair p = (CollisionPair)o;
        return p.a == a && p.b == b;
    }
    @Override public int hashCode() {
        return System.identityHashCode(a) ^ System.identityHashCode(b);
    }
    public PocketMain getFirstPocket() {
        return a;
    }
    public PocketMain getSecondPocket() {
        return b;
    }
    public Point2D getImpactPoint() {
        return impactPoint;
    }
}
