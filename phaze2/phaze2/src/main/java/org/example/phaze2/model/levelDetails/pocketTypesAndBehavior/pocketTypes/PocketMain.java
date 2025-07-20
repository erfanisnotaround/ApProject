package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.InitData;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class PocketMain extends Pocket  implements InitData {
    Pocket behaviour;
    private final double DistractionSteps = 100;
    public PocketMain(PocketTypes type) {
        super(type);
        behaviour = PocketMoveFactory.giveType(type);
        Initialize();
        initData(behaviour);
    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(behaviour.getCoinsPerEntry());
        setMaxHp(behaviour.getMaxHp()); setHP(behaviour.getHP());
        setPathMover(behaviour.getPathMover());
        setMovementManager(behaviour.getMovementManager());
        behaviour.getPathMover().setNode(this);
        getPathMover().setNode(this);
        setFirstPocketType(behaviour.getType());
    }

    @Override
    public void initData(Pocket pocket) {

        imagePath = behaviour.getImagePath();
        setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        setScaleX(behaviour.getScaleX()); setScaleY(behaviour.getScaleY());


        setLayoutX(behaviour.getLayoutX());
        setLayoutY(behaviour.getLayoutY());
        pocket.getPathMover().setNode(this);


        setLayoutX(2);

    }

    @Override
    public void PrepareNewBehavior(Pocket pocket) {
        behaviour.setPathMover(getPathMover());
        setType(behaviour.getType());
        initData(pocket);
        if (isIsItMoved()){
            behaviour.movingStrategy(this , getPathMover().getCurve());
        }
    }


    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
        behaviour.move(curve, speed, acceleration, this);
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {

        return behaviour.ReleaseAct(this , systemView, exitConnections);
    }

    public void distract(double x , double y) {
        getPathMover().AddingImpactVector(x, y , DistractionSteps);
    }

    public void setBehaviour(PocketTypes behaviourType) {
        behaviour.StopStrategy(behaviour , this );

        this.behaviour = PocketMoveFactory.giveType(behaviourType);

        setTypeBeforeChange(getType());
        PrepareNewBehavior(behaviour);
//        System.out.println("Behaviour set to " + behaviour.getType());
    }

    public void StopStrategyMoving() {
        behaviour.StopStrategy(behaviour , this);
    }

    @Override
    public double getSpeed() {
        return behaviour.getSpeed();
    }
}
