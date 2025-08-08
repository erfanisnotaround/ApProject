package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.Messenger3;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class MergerBehavior extends SystemView implements SystemBehavior{

    private final int CapacityCells = 10;

    private final Messenger3[] MergeCapacityTake = new Messenger3[CapacityCells];

    public MergerBehavior(SystemTypes systemType, int numberOfSubSystems , GameState gameState) {
        super(systemType, numberOfSubSystems , gameState);
        capacity = new PocketMain[CapacityCells];

    }

    @Override
    public Connection ReleaseBehave(PocketMain EntryPocket, double multiplier) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());

        return getConnection(firstConnections , secondConnections , random.nextInt(firstConnections.size()) , random.nextInt(secondConnections.size()));

    }

    @Override
    public void EnterBehave(PocketMain EntryPocket, double multiplier) {

    }

    public Connection getConnection(List<Connection> firstConnections, List<Connection> secondConnections , int randomFirstConnection , int randomSecondConnection) {

        if (!firstConnections.isEmpty()) {
            return firstConnections.get(randomFirstConnection);

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(randomSecondConnection);

        }
        else return null;
    }


}
