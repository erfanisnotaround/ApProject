package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge;

import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types.MergerBehavior;

import java.util.List;

public interface PocketMergePolicy {
    int requiredCount(String groupId, SystemView system);
    PocketTypes resultType(String groupId, List<PocketMain> inputs);
    boolean canWeMerge(String groupId, MergerBehavior system , GroupStash slots ,List<PocketMain> pockets);
}