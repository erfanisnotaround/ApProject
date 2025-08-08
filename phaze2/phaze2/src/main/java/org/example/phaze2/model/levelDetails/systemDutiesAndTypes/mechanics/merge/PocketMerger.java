package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge;

import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;

public interface PocketMerger {
    PocketMain merge(List<PocketMain> parts, PocketTypes outType, String groupId, SystemView system);
}
