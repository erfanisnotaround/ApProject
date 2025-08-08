package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.List;

public class SpyBehavior extends SystemView implements SystemBehavior {


    public SpyBehavior(SystemTypes systemType, int numberOfSubSystems , GameState gameState) {
        super(systemType, numberOfSubSystems , gameState);
    }

    @Override
    public Connection ReleaseBehave(PocketMain EntryPocket, double multiplier) {
        SystemView suitableSystemView = FineARandomSPySystem();
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(suitableSystemView , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(suitableSystemView , EntryPocket.getPreferredType());

        return getConnection(firstConnections , secondConnections);

    }

    @Override
    public void EnterBehave(PocketMain EntryPocket, double multiplier) {

    }

    public Connection getConnection(List<Connection> firstConnections, List<Connection> secondConnections ) {

        if (!firstConnections.isEmpty()) {
            return firstConnections.get(random.nextInt(firstConnections.size()));

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(random.nextInt(secondConnections.size()));

        }
        else return null;
    }


    public SystemView FineARandomSPySystem(){
        List<SystemView> systemViews = Constants.getInstance().getSystemViews();
        List<SystemView> spySystemViews = new ArrayList<>();

        for (SystemView systemView : systemViews) {
            if (systemView instanceof SpyBehavior && !systemView.isItDown()){
                spySystemViews.add(systemView);
            }
        }
        return  makeARandomSPySystem(spySystemViews);

    }
    public SystemView makeARandomSPySystem(List<SystemView> SpySystemViews) {
        int randomSpySystem = random.nextInt(SpySystemViews.size());
        return SpySystemViews.get(randomSpySystem);
    }


}
