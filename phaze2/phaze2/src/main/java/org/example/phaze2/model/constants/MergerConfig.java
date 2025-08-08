package org.example.phaze2.model.constants;

import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.DefaultMerger;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.FlexibleMergePolicy;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.PocketMergePolicy;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.PocketMerger;

public final class MergerConfig {
    private PocketMerger merger;
    private PocketMergePolicy policy;

    public PocketMerger getMerger() {
        return merger;
    }

    public void setMerger(PocketMerger merger) {
        this.merger = merger;
    }

    public PocketMergePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(PocketMergePolicy policy) {
        this.policy = policy;
    }
}
