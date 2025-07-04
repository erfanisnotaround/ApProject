package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketMoveFactory;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;

public class Pocket extends ImageView implements Movable {
    private double distanceFromTheLine = 0;
    private double HP;
    private static int coinsPerEntry;
    private BooleanProperty IsItCollided = new SimpleBooleanProperty(false);
    private PocketTypes type;
    private double delay;
    Movable movable;
    public Pocket(PocketTypes type) {
        this.type = type;
        movable = PocketMoveFactory.givePocketMovementType(type);
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
}
