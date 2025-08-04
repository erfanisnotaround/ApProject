package org.example.phaze2.model.abilities.modelingAbilities;

import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;

public final class GameContext {

    // *mutable* pointers to the always-live subsystems
    public final WholeMovement movement;
    public final List<PocketMain> pockets;
    public final List<SystemView> systems;
    public final CoinsManager coins;

    public GameContext(WholeMovement m,
                        List<PocketMain> p,
                        List<SystemView> s,
                        CoinsManager c) {
        this.movement = m;
        this.pockets  = p;
        this.systems  = s;
        this.coins    = c;
    }



    public long now() { return System.currentTimeMillis(); }
}

