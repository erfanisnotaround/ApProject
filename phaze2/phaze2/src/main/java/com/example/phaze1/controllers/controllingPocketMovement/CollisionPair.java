package com.example.phaze1.controllers.controllingPocketMovement;

import com.example.phaze1.model.systemsInfoAndManagers.Pocket;

public class CollisionPair {
    final Pocket a, b;
    CollisionPair(Pocket a, Pocket b) {
        if (System.identityHashCode(a) < System.identityHashCode(b)) {
            this.a = a; this.b = b;
        } else {
            this.a = b; this.b = a;
        }
    }
    @Override public boolean equals(Object o) {
        if (!(o instanceof CollisionPair)) return false;
        CollisionPair p = (CollisionPair)o;
        return p.a == a && p.b == b;
    }
    @Override public int hashCode() {
        return System.identityHashCode(a) ^ System.identityHashCode(b);
    }
}