package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class SpyBehavior extends SystemView implements SystemBehavior, SwitchingPocketMovementInSystems {


    public SpyBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());


        if (!firstConnections.isEmpty()) {

            int randomFirstConnection = random.nextInt(firstConnections.size());
            Connection connection = firstConnections.get(randomFirstConnection);

            return connection;

        } else if (!secondConnections.isEmpty()) {

            int randomSecondConnection = random.nextInt(secondConnections.size());
            Connection connection = secondConnections.get(randomSecondConnection);

            return connection;

        }
        else return null;
    }

    @Override
    public void switchPocket(Pocket pocket) {

    }
}
