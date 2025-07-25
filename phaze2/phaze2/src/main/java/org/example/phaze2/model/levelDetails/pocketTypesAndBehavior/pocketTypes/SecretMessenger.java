package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SecretMessenger extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/Arcane_Seal.png").toExternalForm());
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
    public void movingStrategy(PocketMain pocketMain, Curve curve) {

    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {
        behavior.StopStrategy(LastPocket, pocketMain);
        System.out.println();
    }

    @Override
    protected void Initialize() {
        imagePath = "/org/example/phaze2/images/Arcane_Seal.png";
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(0.15);
        setScaleY(0.15);
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
