package org.example.phaze2.controllers.moverController;

public class HysteresisBlock {
    private final double inner2, outer2;   // squared radii
    private boolean blocked = false;

    public HysteresisBlock(double innerRadius, double outerRadius) {
        this.inner2 = innerRadius * innerRadius;
        this.outer2 = outerRadius * outerRadius;
    }
    /** feed *squared* distance of the closest intruder on this side */
    public void update(double bestD2) {
        if (!blocked && bestD2 < inner2)  blocked = true;
        else if (blocked && bestD2 > outer2) blocked = false;
    }
    public boolean isBlocked() { return blocked; }
}
