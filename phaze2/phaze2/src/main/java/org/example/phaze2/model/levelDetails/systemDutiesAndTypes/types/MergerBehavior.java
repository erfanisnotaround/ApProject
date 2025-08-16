package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.Messenger3;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.GroupStash;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.PocketMergePolicy;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.PocketMerger;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.SingleGroupBuffer;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class MergerBehavior extends SystemView implements SystemBehavior{

    private final int CapacityCells = 10;

    private final GroupStash stash = new SingleGroupBuffer(CapacityCells);
    private final PocketMerger merger;
    private final PocketMergePolicy mergePolicy;
    private final GameState gameState;

    public MergerBehavior(SystemTypes systemType, int numberOfSubSystems , GameState gameState) {
        super(systemType, numberOfSubSystems , gameState);
        capacity = new PocketMain[CapacityCells];
        merger = gameState.getMergerConfig().getMerger();
        mergePolicy = gameState.getMergerConfig().getPolicy();
        this.gameState = gameState;
    }

    @Override
    public Connection ReleaseBehave(PocketMain EntryPocket, double multiplier) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());



        if (!firstConnections.isEmpty()) {
            return firstConnections.get(random.nextInt(firstConnections.size()));

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(random.nextInt(secondConnections.size()));

        }
        else return null;


    }

    @Override
    public void EnterBehave(PocketMain EntryPocket, double multiplier) {
        if (EntryPocket.getType() == null || EntryPocket.getGroupId() == null) return;
        if (EntryPocket.getType() != PocketTypes.Messenger_3) return;

        boolean accepted = stash.accept(EntryPocket);
        if (accepted) {
            EntryPocket.setCapturedByMerger(true);
            getGameState().getVisualConstant().getView().remove(EntryPocket);
            getGameState().getResources().getPockets().remove(EntryPocket);
            tryMergeNow();
        }


    }

    private void tryMergeNow() {
        if (stash.isEmpty()) return;
        String groupId = stash.getGroupId();
        int needed = mergePolicy.requiredCount(groupId, this);
        if (stash.size() < needed) return;


        if (!mergePolicy.canWeMerge(groupId , this , stash ,gameState.getResources().getPockets())) return;

        List<PocketMain> inputs = stash.take(stash.size());

        PocketMain merged = merger.merge(inputs, mergePolicy.resultType(groupId, inputs), groupId, this);

        if (merged == null) return;

        for (int i  = 0 ; i < capacity.length ; i++) {
            PocketMain m = capacity[i];
            if (m != null) {

                merged.getMovementManager().SendingPockets(this , merged , i);

            }
        }
        capacity[capacity.length - 1] = null;
        merged.getMovementManager().SendingPockets(this , merged , -1);



    }

    public Connection getConnection(List<Connection> firstConnections, List<Connection> secondConnections , int randomFirstConnection , int randomSecondConnection) {

        if (!firstConnections.isEmpty()) {
            return firstConnections.get(randomFirstConnection);

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(randomSecondConnection);

        }
        else return null;
    }

    public GroupStash getStash() {
        return stash;
    }

    @Override
    public void reset() {
        super.reset();
        stash.reset();
    }
}
