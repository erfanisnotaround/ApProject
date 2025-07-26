package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class VpnBehavior extends SystemView implements SystemBehavior, SwitchingPocketMovementInSystems {

    double limitSpeed = 110;
    double coolDown = 1;
    private PauseTransition pauseTransition = new PauseTransition(Duration.seconds(coolDown));

    public VpnBehavior(SystemTypes systemType, int numberOfSubSystems) {
        super(systemType, numberOfSubSystems);
        AddListener();
    }

    @Override
    public Connection behave(PocketMain EntryPocket, double multiplier) {



        if (EntryPocket.getPathMover().getSpeed() >= limitSpeed * multiplier){
            pauseTransition.setDuration(Duration.seconds(coolDown / multiplier));
            isItDownProperty().set(true);
            pauseTransition.play();
            pauseTransition.setOnFinished(event -> {
               isItDownProperty().set(false);
            });

        }
        if (EntryPocket.getType() != PocketTypes.SECRET_MESSENGER && EntryPocket.getType() != PocketTypes.SECRET_1) {

            EntryPocket.setBehaviour(PocketTypes.SECRET_MESSENGER);
            EntryPocket.setWhichSystemViewThisPocketIsAffectedBy(this);

        }
        else if (EntryPocket.getType() == PocketTypes.SECRET_1) {
            EntryPocket.setBehaviour(PocketTypes.SECRET_1);
            EntryPocket.setWhichSystemViewThisPocketIsAffectedBy(this);
        }
        List<Connection> firstConnections = pathPrioritizing.firstPrioritizedSubSystems(this , PortTypes.TRIANGLE);
        List<Connection> secondConnections = pathPrioritizing.SecondPrioritizedSubSystems(this , PortTypes.TRIANGLE);

        Connection newEntryConnection = getConnection(firstConnections , secondConnections);


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
            if (t1){
                OperationOfChangingPockets();
            }
        });
    }
    public void OperationOfChangingPockets() {
        List<PocketMain> pockets = Constants.getInstance().getPockets();
        for (PocketMain pocket : pockets) {
            if (pocket.getType() != pocket.getTypeBeforeChange() && pocket.getWhichSystemViewThisPocketIsAffectedBy() != null
                    &&pocket.getWhichSystemViewThisPocketIsAffectedBy().equals(this)) {
                pocket.setWhichSystemViewThisPocketIsAffectedBy(null);
                switchPocket(pocket,pocket.getTypeBeforeChange());
            }

        }
    }

    @Override
    public Pocket switchPocket(PocketMain pocket, PocketTypes type) {
        pocket.setBehaviour(type);
        return pocket;
    }
}
