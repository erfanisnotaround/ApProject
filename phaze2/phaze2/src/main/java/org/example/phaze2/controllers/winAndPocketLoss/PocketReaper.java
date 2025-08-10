// org/example/phaze2/controllers/winAndPocketLoss/PocketReaper.java
package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.application.Platform;
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
        List<PocketMain> dead = new ArrayList<>();
        for (PocketMain p : pockets) {
            if (p.getHP() <= 0 && !reaped.contains(p)) {
                reaped.add(p);
                dead.add(p);
            }
        }
        return dead;
    }

    public void applyOnFx(List<PocketMain> dead) {
        for (PocketMain p : dead) {
            Platform.runLater(() -> {
                p.getPathMover().stopAndDetachNow();
            });
            placement.place(p);
        }
    }
    public void reset(){
        reaped.clear();
        placement.reset();
    }
}
