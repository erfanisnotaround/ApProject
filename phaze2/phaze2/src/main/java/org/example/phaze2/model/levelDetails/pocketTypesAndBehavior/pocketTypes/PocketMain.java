package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.levelDetails.collisionNecessaries.HitBox;
import org.example.phaze2.model.levelDetails.collisionNecessaries.HitBoxGenerator;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.InitData;
import org.example.phaze2.controllers.moverController.moveRelated.MakingGoBehindOrForward;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class PocketMain extends Pocket  implements InitData {
    private HitBox hitBox;
    Pocket behaviour;
    MakingGoBehindOrForward makingGoBehindOrForward = new MakingGoBehindOrForward(this);
    private final double DistractionSteps = 100;
    public PocketMain(PocketTypes type) {
        super(type);
        pathMover = new PathMover(0);
        pathMover.setNode(this);
        behaviour = PocketMoveFactory.giveType(type);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),actionEvent -> {
            pathMover.moveForward();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Initialize();
        initData(behaviour);

    }

    @Override
    protected void Initialize() {
        setCoinsPerEntry(behaviour.getCoinsPerEntry());
        setMaxHp(behaviour.getMaxHp()); setHP(behaviour.getHP());
        setMovementManager(behaviour.getMovementManager());

        setFirstPocketType(behaviour.getType());
    }

    @Override
    public void initData(Pocket pocket) {

        imagePath = behaviour.getImagePath();
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        setImage(image);
        setScaleX(behaviour.getScaleX()); setScaleY(behaviour.getScaleY());


        pocket.setPathMover(pathMover);
        pocket.getPathMover().setNode(this);
        pocket.getPathMover().setAngleNeeded(pocket.getAngleNeeded());
        if (isIsItMoved()){
            System.out.println(behaviour.getType() + " made a change");

            behaviour.movingStrategy(this , getPathMover().getCurve());
        }

        hitBox = HitBoxGenerator.generateHitBox(image);
        hitBox.setScaleX(behaviour.getScaleX());
        hitBox.setScaleY(behaviour.getScaleY());

//        Constants.getInstance().container.getChildren().add(hitBox);




    }

    @Override
    public void PrepareNewBehavior(Pocket pocket) {
        behaviour.setPathMover(getPathMover());
        setType(behaviour.getType());
        initData(pocket);

    }


    @Override
    public void move(Curve curve, double RealSpeed, double RealAcceleration, PocketMain pocket, double multiplier) {
        behaviour.move(curve, RealSpeed, RealAcceleration, this, multiplier);
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections, double multiplier) {

        return behaviour.ReleaseAct(this , systemView, exitConnections, multiplier);
    }

    public void distract(double x , double y) {
        getPathMover().AddingImpactVector(x, y , DistractionSteps);
    }

    public void setBehaviour(PocketTypes behaviourType) {
        PauseTransition pause = new PauseTransition(Duration.millis(20));
        pause.setOnFinished(event -> {
            behaviour.StopStrategy(behaviour , this );
            System.out.println(behaviour.getType() + " hey looo");

            this.behaviour = PocketMoveFactory.giveType(behaviourType);

            setTypeBeforeChange(getType());
            PrepareNewBehavior(behaviour);
        });
        pause.play();

    }

    public void StopStrategyMoving() {
        behaviour.StopStrategy(behaviour , this);
    }

    @Override
    public double getSpeed() {
        return behaviour.getSpeed();
    }
    public MakingGoBehindOrForward getMakingGoBehindOrForward() {
        return makingGoBehindOrForward;
    }
    public HitBox getHitBox() {
        return hitBox;
    }

}
