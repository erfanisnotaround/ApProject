package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting;

import org.example.phaze2.model.constants.Resources;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class PocketRepository {
    private final Resources res;
    public PocketRepository(Resources res) { this.res = res; }
    void add(PocketMain p)    { res.getPockets().add(p); }
    void remove(PocketMain p) { res.getPockets().remove(p); }
}