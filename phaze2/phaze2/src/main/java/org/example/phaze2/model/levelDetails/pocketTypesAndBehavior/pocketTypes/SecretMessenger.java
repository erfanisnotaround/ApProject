package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SecretMessenger extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/lock.png").toExternalForm());
    Pocket behavior;
    public SecretMessenger(PocketTypes type) {
        super(type);
        Initialize();
    }
    private void chooseTheMoveBehavior(){
        int randomBehavior = random.nextInt(PocketTypeGroup.MESSENGER.getGroups().size());
        behavior = PocketMoveFactory.giveType(PocketTypeGroup.MESSENGER.getGroups().get(randomBehavior));
        behavior.setPathMover(pathMover);
        preferredType = behavior.getPreferredType();
        HP = MaxHp = behavior.getMaxHp();
    }

    @Override
    protected void Initialize() {
        imagePath = "/org/example/phaze2/images/lock.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.03);
        setScaleY(0.03);
        setCoinsPerEntry(5);
        pathMover = new PathMover(0);
        chooseTheMoveBehavior();
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {
        chooseTheMoveBehavior();
        return behavior.ReleaseAct(pocket, systemView, exitConnections);
    }
}
