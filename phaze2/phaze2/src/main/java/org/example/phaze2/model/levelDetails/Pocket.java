package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.controllers.moverController.PathPrioritizing;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Releasable;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;
import java.util.Random;

public class Pocket extends ImageView implements Movable , Releasable {
    protected Random random = new Random();
    protected PathPrioritizing pathPrioritizing;
    protected PathMover pathMover;
    protected double distanceFromTheLine = 0;
    protected double HP;
    protected static int coinsPerEntry;
    protected BooleanProperty IsItCollided = new SimpleBooleanProperty(false);
    protected BooleanProperty IsItMoved = new SimpleBooleanProperty(false);
    protected PocketTypes type;
    protected double delay;
    protected Movable movable;
    public Pocket(PocketTypes type) {
        this.type = type;
//        movable = PocketMoveFactory.givePocketMovementType(type);
    }

    public static int getCoinsPerEntry() {
        return coinsPerEntry;
    }

    public static void setCoinsPerEntry(int coinsPerEntry) {
        Pocket.coinsPerEntry = coinsPerEntry;
    }

    @Override
    public void move(Curve curve) {
        movable.move(curve);
    }

    @Override
    public Connection ReleaseAct(SystemView systemView, Map<Node, PortInfo> portInfoMap, Map<PortInfo, Connection> exitConnections) {
        return null;
    }


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
}
