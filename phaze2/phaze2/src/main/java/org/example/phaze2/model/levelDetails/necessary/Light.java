package org.example.phaze2.model.levelDetails.necessary;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Light extends Rectangle {
    public boolean isItOn = false;
    Color OFFColor = Color.RED;
    Color ONColor = Color.BLUE;
    public Light(double Radius , double width, double height) {
        super(width, height);
        setArcWidth(Radius);
        setArcHeight(Radius);
        setFill(OFFColor);
    }

    public void setItOn(boolean itOn) {
        isItOn = itOn;
        if (isItOn) {
            setFill(ONColor);
        }else setFill(OFFColor);
    }
}
