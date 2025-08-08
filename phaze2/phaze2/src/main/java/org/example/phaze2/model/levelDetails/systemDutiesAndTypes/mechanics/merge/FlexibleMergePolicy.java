package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge;

import javafx.geometry.Point2D;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types.MergerBehavior;

import java.util.List;

public class FlexibleMergePolicy implements PocketMergePolicy {
    private final int threshold;
    private final double RadiusOfChecking = 1000;

    public FlexibleMergePolicy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public int requiredCount(String groupId, SystemView system) {
        return threshold;
    }

    @Override
    public PocketTypes resultType(String groupId, List<PocketMain> inputs) {
        if (inputs.isEmpty()) return null;



        PocketTypes inputType = inputs.get(0).getType();

        return switch (inputType) {
            case Messenger_3 -> PocketTypes.BIG_1;
            case SECRET_MESSENGER -> PocketTypes.BIG_2;
            default -> PocketTypes.BIG_1;
        };
    }

    @Override
    public boolean canWeMerge(String groupId, MergerBehavior system,GroupStash slots ,List<PocketMain> pockets) {
        Point2D systemLayout = new Point2D(system.getLayoutX() + system.getWidth()/2, system.getLayoutY() + system.getHeight() / 2);

        for (PocketMain pocket : pockets) {
            if (!pocket.getGroupId().equals(groupId)) continue;
            if (slots.isThere(pocket)) continue;
            double distance = systemLayout.distance(pocket.centre());

            if (distance < RadiusOfChecking  && !pocket.isLastRound()) return false;
        }
        return true;
    }
}
