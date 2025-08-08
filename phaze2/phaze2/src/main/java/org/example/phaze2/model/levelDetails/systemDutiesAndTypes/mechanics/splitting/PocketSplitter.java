package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting;

import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface PocketSplitter {
    void split(PocketMain bigPocket , SystemView systemView);
}