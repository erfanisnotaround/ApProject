package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.BigPocketSplitter;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class DestructiveBehavior extends SystemView implements SystemBehavior {



    public DestructiveBehavior(SystemTypes systemType, int numberOfSubSystems , GameState gameState) {
        super(systemType, numberOfSubSystems , gameState);

    }

    @Override
    public Connection ReleaseBehave(PocketMain EntryPocket, double multiplier) {
        if (EntryPocket.getType()!= PocketTypes.SECRET_MESSENGER) {
            if (EntryPocket.getHP() == EntryPocket.getMaxHp() ){
                EntryPocket.setHP(EntryPocket.getMaxHp() - 1);
            }

            EntryPocket.setItAffected(true);
        }

        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , EntryPocket.getPreferredType());


        return getConnection(secondConnections );
    }

    @Override
    public void EnterBehave(PocketMain EntryPocket, double multiplier) {

    }

    public Connection getConnection(List<Connection> secondConnections) {
        if (!secondConnections.isEmpty()) {
            return secondConnections.get(random.nextInt(secondConnections.size()));

        }
        else return null;
    }


}
