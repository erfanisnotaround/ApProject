package com.example.phaze1.Model.SystemsInfoAndManagers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SystemView extends Pane {
    public BooleanProperty lightBoolean = new SimpleBooleanProperty(false);
    public Button startButton;
    public Light light;
    public double x;
    public double y;
    public String systemID;
    public boolean isItStartSystem;
    public int numberOfSubSystems;
    public boolean isTheLightOn;
    public Pocket[] capacity = new Pocket[5];
    public ArrayList<ViewOfSubSystem> SubSystems = new ArrayList<>();
    public SystemView(){
        String style =
                "-fx-background-color: #781305;" +
                        " -fx-background-radius: 5;" +
                        " -fx-border-color: #5f0f04;" +
                        " -fx-border-width: 2;" +
                        " -fx-border-radius: 5;";
        setStyle(style);
        lightBoolean.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (light != null) {
                    light.isItOn = true;
                    light.lightColor = Color.LIGHTBLUE;
                    light.setFill(Color.LIGHTBLUE);
                }
            }
            else {
                if (light != null) {
                    light.isItOn = false;
                    light.lightColor = Color.RED;
                    light.setFill(Color.RED);
                }
            }
        });
    }

}
