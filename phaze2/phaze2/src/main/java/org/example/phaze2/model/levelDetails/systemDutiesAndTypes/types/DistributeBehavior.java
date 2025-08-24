package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.BigPocketSplitter;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Arrays;
import java.util.List;

public class DistributeBehavior extends SystemView implements SystemBehavior {

    private final int CapacityCells = 10;
    BigPocketSplitter splitter;

    public DistributeBehavior(SystemTypes systemType, int numberOfSubSystems , GameState gameState , System level) {
        super(systemType, numberOfSubSystems , gameState , level);
        capacity = new PocketMain[CapacityCells];
        splitter = gameState.getVisualConstant().getSplitter();
    }

    @Override
    public Connection ReleaseBehave(PocketMain EntryPocket, double multiplier) {
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
    public void EnterBehave(PocketMain EntryPocket , double multiplier) {
        boolean isBigPocket = splitter.isBigPocket(EntryPocket);
        if (isBigPocket) {

            Arrays.fill(capacity, null);
            EntryPocket.setPocketIsLostByDisterbute(true);
            splitter.split(EntryPocket , this);

        }

    }


}
