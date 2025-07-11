package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.SecretMessenger;
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
        if (!(EntryPocket instanceof SecretMessenger)) {
            if (EntryPocket.getHP() == EntryPocket.getMaxHp() ){
                EntryPocket.setHP(EntryPocket.getMaxHp() - 1);
            }

            EntryPocket.setItAffected(true);
        }

        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());


        return getConnection(secondConnections );
    }
    public Connection getConnection(List<Connection> secondConnections) {
        if (!secondConnections.isEmpty()) {
            return secondConnections.get(random.nextInt(secondConnections.size()));

        }
        else return null;
    }

    @Override
    public Pocket switchPocket(Pocket pocket, PocketTypes type) {
        return pocket;
    }
}
