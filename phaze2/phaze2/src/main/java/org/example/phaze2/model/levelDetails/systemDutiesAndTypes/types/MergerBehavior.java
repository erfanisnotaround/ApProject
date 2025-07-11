package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class MergerBehavior extends SystemView implements SystemBehavior, SwitchingPocketMovementInSystems {


    public MergerBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());

        return getConnection(firstConnections , secondConnections , random.nextInt(firstConnections.size()) , random.nextInt(secondConnections.size()));

    }
    public Connection getConnection(List<Connection> firstConnections, List<Connection> secondConnections , int randomFirstConnection , int randomSecondConnection) {

        if (!firstConnections.isEmpty()) {
            return firstConnections.get(randomFirstConnection);

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(randomSecondConnection);

        }
        else return null;
    }

    @Override
    public Pocket switchPocket(Pocket pocket, PocketTypes type) {
        return pocket;
    }
}
