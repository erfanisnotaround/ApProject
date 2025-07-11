package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics;

import javafx.scene.layout.Pane;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public class PocketSwitchManager {

    private static Pane container;

    public static Pocket switchPocket(Pocket olderPocket , PocketTypes newPocketType) {
        Pocket newPocket = PocketMoveFactory.giveType(newPocketType);
        newPocket.setFirstPocketType(olderPocket.getFirstPocketType());




//        newPocket.setFirstPocketType(olderPocket.getFirstPocketType());
        newPocket.setTypeBeforeChange(olderPocket.getTypeBeforeChange());

        container.getChildren().add(newPocket);
        container.getChildren().remove(olderPocket);


        newPocket.setLayoutX(olderPocket.getLayoutX());
        newPocket.setLayoutY(olderPocket.getLayoutY());

        newPocket.setHP(olderPocket.getHP());


        newPocket.setSpeed(olderPocket.getSpeed());
        newPocket.setAcceleration(olderPocket.getAcceleration());

        newPocket.setMovementManager(olderPocket.getMovementManager());
        newPocket.setPathMover(olderPocket.getPathMover());
        newPocket.getPathMover().setNode(newPocket);
        newPocket.getPathMover().start();

        newPocket.setIsItMoved(olderPocket.isIsItMoved());
        newPocket.setIsItCollided(olderPocket.isIsItCollided());


        if (olderPocket.getPathMover().getCurve() != null && olderPocket.getMovementManager() != null) {
            olderPocket.getMovementManager().RegisterPocket(newPocket , olderPocket.getPathMover().getCurve().getConnection());
        }

        return newPocket;
    }

    public static void reInitialize(Pocket olderPocket) {
        Pocket newPocket = PocketMoveFactory.giveType(olderPocket.getFirstPocketType());

        int indexOfFirstPocket = Constants.getInstance().getPockets().indexOf(olderPocket);
        Constants.getInstance().getPockets().set(indexOfFirstPocket , newPocket);

        newPocket.getPathMover().stop();
        newPocket.setIsItMoved(false);
        newPocket.setIsItCollided(false);
        newPocket.setHP(newPocket.getMaxHp());

        newPocket.setLayoutX(500);
        newPocket.setLayoutY(500);
        newPocket.setItAffected(false);



        newPocket.setFirstPocketType(newPocket.getType());
        container.getChildren().remove(olderPocket);
        container.getChildren().add(newPocket);
    }
    public static void backToBeforeChange(Pocket olderPocket) {
        Pocket newPocket = PocketMoveFactory.giveType(olderPocket.getTypeBeforeChange());

        int indexOfFirstPocket = Constants.getInstance().getPockets().indexOf(olderPocket);
        Constants.getInstance().getPockets().set(indexOfFirstPocket , newPocket);

        newPocket.getPathMover().stop();
        newPocket.setIsItMoved(false);
        newPocket.setIsItCollided(false);
        newPocket.setHP(newPocket.getMaxHp());

        newPocket.setLayoutX(500);
        newPocket.setLayoutY(500);
        newPocket.setItAffected(false);



        newPocket.setFirstPocketType(newPocket.getType());
        container.getChildren().remove(olderPocket);
        container.getChildren().add(newPocket);
    }
    public static void setContainer(Pane container) {
        PocketSwitchManager.container = container;
    }

}
