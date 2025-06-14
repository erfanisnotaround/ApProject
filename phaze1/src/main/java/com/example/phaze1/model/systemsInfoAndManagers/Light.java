package com.example.phaze1.model.systemsInfoAndManagers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Light extends Rectangle {
    public boolean isItOn = false;
    Color lightColor = Color.RED;
    public Light(double Radius , double width, double height) {
        super(width, height);
        setArcWidth(Radius);
        setArcHeight(Radius);
        setFill(lightColor);
    }
}
