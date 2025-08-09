// org/example/phaze2/controllers/winAndPocketLoss/PocketReaper.java
package org.example.phaze2.controllers.winAndPocketLoss;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.*;

// Reaper that can run detection off the FX thread.
public class PocketReaper {
    private final List<PocketMain> pockets;
    private final DeadPocketPlacementStrategy placement;
    private final Set<PocketMain> reaped = Collections.newSetFromMap(new IdentityHashMap<>());

    public PocketReaper(List<PocketMain> pockets, DeadPocketPlacementStrategy placement) {
        this.pockets = pockets;
        this.placement = placement;
    }

    public List<PocketMain> detectDead() {
        System.out.println("fired3");
        List<PocketMain> dead = new ArrayList<>();
        for (PocketMain p : pockets) {
            if (p.getHP() <= 0 && !reaped.contains(p)) {
                reaped.add(p);
                dead.add(p);
            }
        }
        System.out.println("fired4");
        return dead;
    }

    public void applyOnFx(java.util.List<PocketMain> dead) {
        for (PocketMain p : dead) {
            p.getPathMover().stopAndDetachNow();      // touches JavaFX – must be FX
            placement.place(p);                       // e.g., move to a loss bin
        }
    }
}
