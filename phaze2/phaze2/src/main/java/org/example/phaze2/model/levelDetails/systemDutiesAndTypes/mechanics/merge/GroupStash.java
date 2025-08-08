package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;

public interface GroupStash {
    boolean accept(PocketMain pocket);
    List<PocketMain> take(int count);
    int size();
    boolean isEmpty();
    String getGroupId();
    boolean isThere(PocketMain pocket);
}