package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.List;

public class SpyBehavior extends SystemView implements SystemBehavior, SwitchingPocketMovementInSystems {


    public SpyBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        SystemView suitableSystemView = FineARandomSPySystem();
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(suitableSystemView , EntryPocket.getPreferredType());
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(suitableSystemView , EntryPocket.getPreferredType());

        return getConnection(firstConnections , secondConnections);

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
            if (systemView instanceof SpyBehavior){
                spySystemViews.add(systemView);
            }
        }
        return  makeARandomSPySystem(spySystemViews);

    }
    public SystemView makeARandomSPySystem(List<SystemView> SpySystemViews) {
        int randomSpySystem = random.nextInt(SpySystemViews.size());
        return SpySystemViews.get(randomSpySystem);
    }

    @Override
    public Pocket switchPocket(Pocket pocket, PocketTypes type) {
        return null;
    }
}
