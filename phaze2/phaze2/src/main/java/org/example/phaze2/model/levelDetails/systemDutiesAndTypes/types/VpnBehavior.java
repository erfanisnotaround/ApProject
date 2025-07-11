package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.SecretMessenger;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.PocketSwitchManager;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class VpnBehavior extends SystemView implements SystemBehavior, SwitchingPocketMovementInSystems {


    public VpnBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
        AddListener();
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        Pocket newEntryPocket = switchPocket(EntryPocket , PocketTypes.SECRET_MESSENGER);
//        if (Constants.getInstance().getPockets().indexOf(newEntryPocket) == -1){
//            System.out.println("Pg ey ahmagh");
//        }
//        else System.out.println("pusso pusso");
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , PortTypes.TRIANGLE);
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , PortTypes.TRIANGLE);

        Connection newEntryConnection = getConnection(firstConnections , secondConnections);

        if (newEntryConnection != null) {
            EntryPocket.getMovementManager().RegisterPocket(newEntryPocket , newEntryConnection);
        }

        return newEntryConnection;
    }
    public Connection getConnection(List<Connection> firstConnections, List<Connection> secondConnections ) {

        if (!firstConnections.isEmpty()) {
            return firstConnections.get(random.nextInt(firstConnections.size()));

        } else if (!secondConnections.isEmpty()) {
            return secondConnections.get(random.nextInt(secondConnections.size()));

        }
        else return null;
    }

    public void AddListener() {
        IsItDown.addListener((observableValue, aBoolean, t1) -> {
            if (!t1){
                OperationOfChangingPockets();
            }
        });
    }
    public void OperationOfChangingPockets() {
        List<Pocket> pockets = Constants.getInstance().getPockets();
        for (Pocket pocket : pockets) {
            if (pocket.getType() != pocket.getTypeBeforeChange() && pocket.getWhichSystemViewThisPocketIsAffectedBy().equals(this)) {
//                switchPocket(pocket,pocket.getTypeBeforeChange());
            }
        }
    }

    @Override
    public Pocket switchPocket(Pocket pocket, PocketTypes type) {
        Pocket selectedPocket = PocketSwitchManager.switchPocket(pocket, type);
        int indexOfFirstPocket = Constants.getInstance().getPockets().indexOf(pocket);
        Constants.getInstance().getPockets().set(indexOfFirstPocket , selectedPocket);

        return selectedPocket;
    }
}
