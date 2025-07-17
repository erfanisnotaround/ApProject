package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import org.example.phaze2.controllers.moverController.MovingPlanMaker;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.controllers.moverController.WholeMovement;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.controllers.moverController.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Releasable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;
import java.util.Random;

public abstract class Pocket extends ImageView implements Movable , Releasable , MovingPlanMaker {
    protected WholeMovement movementManager;
    protected boolean isItAffected = false;
    protected Random random = new Random();
    protected PathMover pathMover;
    protected double distanceFromTheLine = 0;
    protected double HP;
    protected double MaxHp;
    protected static int coinsPerEntry;
    protected BooleanProperty IsItCollided = new SimpleBooleanProperty(false);
    protected BooleanProperty IsItMoved = new SimpleBooleanProperty(false);
    protected PocketTypes type;
    protected double delay;
    protected Movable movable;
    protected double speed;
    protected double acceleration;
    protected PortTypes preferredType;
    protected String imagePath;
    private PocketTypes TypeBeforeChange;
    private PocketTypes FirstPocketType;
    private double placeOfX;
    private double placeOfY;
    private SystemView WhichSystemViewThisPocketIsAffectedBy = null;
    public Pocket(PocketTypes type) {
        this.type = type;
    }

    public int getCoinsPerEntry() {
        return coinsPerEntry;
    }

    public void setCoinsPerEntry(int coinsPerEntry) {
        Pocket.coinsPerEntry = coinsPerEntry;
    }

    @Override
    public void move(Curve curve, double speed, double acceleration, PocketMain pocket) {
    }

    @Override
    public Connection ReleaseAct(PocketMain pocket, SystemView systemView, Map<Port, Connection> exitConnections) {
        return null;
    }

    @Override
    public void movingStrategy(PocketMain pocketMain, Curve curve) {

    }

    @Override
    public void StopStrategy(Pocket LastPocket, PocketMain pocketMain) {

    }

    protected void Initialize(){}


    public double getDelay() {return delay;}

    public void setDelay(double delay) {this.delay = delay;}

    public PocketTypes getType() {
        return type;
    }

    public void setType(PocketTypes type) {
        this.type = type;
    }

    public boolean isIsItCollided() {
        return IsItCollided.get();
    }

    public BooleanProperty isItCollidedProperty() {
        return IsItCollided;
    }

    public void setIsItCollided(boolean isItCollided) {
        this.IsItCollided.set(isItCollided);
    }

    public double getDistanceFromTheLine() {
        return distanceFromTheLine;
    }

    public void setDistanceFromTheLine(double distanceFromTheLine) {
        this.distanceFromTheLine = distanceFromTheLine;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public PathMover getPathMover() {
        return pathMover;
    }

    public void setPathMover(PathMover pathMover) {
        this.pathMover = pathMover;
    }


    public boolean isIsItMoved() {
        return IsItMoved.get();
    }

    public BooleanProperty isItMovedProperty() {
        return IsItMoved;
    }

    public void setIsItMoved(boolean isItMoved) {
        this.IsItMoved.set(isItMoved);
    }

    public double getMaxHp() {
        return MaxHp;
    }

    public void setMaxHp(double maxHp) {
        MaxHp = maxHp;
    }

    public PortTypes getPreferredType() {
        return preferredType;
    }

    public boolean isItAffected() {
        return isItAffected;
    }

    public void setItAffected(boolean itAffected) {
        isItAffected = itAffected;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public WholeMovement getMovementManager() {
        return movementManager;
    }

    public void setMovementManager(WholeMovement movementManager) {
        this.movementManager = movementManager;
    }

    public PocketTypes getFirstPocketType() {
        return FirstPocketType;
    }

    public void setFirstPocketType(PocketTypes firstPocketType) {
        FirstPocketType = firstPocketType;
    }

    public PocketTypes getTypeBeforeChange() {
        return TypeBeforeChange;
    }

    public void setTypeBeforeChange(PocketTypes typeBeforeChange) {
        TypeBeforeChange = typeBeforeChange;
    }

    public SystemView getWhichSystemViewThisPocketIsAffectedBy() {
        return WhichSystemViewThisPocketIsAffectedBy;
    }

    public void setWhichSystemViewThisPocketIsAffectedBy(SystemView whichSystemViewThisPocketIsAffectedBy) {
        WhichSystemViewThisPocketIsAffectedBy = whichSystemViewThisPocketIsAffectedBy;
    }

    public double getPlaceOfX() {
        return placeOfX;
    }

    public void setPlaceOfX(double placeOfX) {
        this.placeOfX = placeOfX;
    }

    public double getPlaceOfY() {
        return placeOfY;
    }

    public void setPlaceOfY(double placeOfY) {
        this.placeOfY = placeOfY;
    }
}
