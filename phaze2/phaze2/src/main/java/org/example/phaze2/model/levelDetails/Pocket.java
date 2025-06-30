package org.example.phaze2.model.levelDetails;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;
import org.example.phaze2.model.levelDetails.systemDuties.Movable;

public class Pocket extends Polygon implements Movable {
    private PocketTypes type;
    private double delay;


    @Override
    public void move(Curve curve) {

    }


    public double getDelay() {return delay;}

    public void setDelay(double delay) {this.delay = delay;}

    public PocketTypes getType() {
        return type;
    }

    public void setType(PocketTypes type) {
        this.type = type;
    }
}
