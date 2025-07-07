package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class DestructiveBehavior extends SystemView implements SystemBehavior, SwitchingPocketMovementInSystems {


    public DestructiveBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());


        if (!secondConnections.isEmpty()) {

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
