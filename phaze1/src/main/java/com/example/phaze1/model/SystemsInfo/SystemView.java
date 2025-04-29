package com.example.phaze1.model.SystemsInfo;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SystemView extends Pane {
    public double x;
    public double y;
    public String systemID;
    public boolean isItStartSystem;
    public boolean isItFinisherSystem;
    public int numberOfSubSystems;
    public boolean isTheLightOn;
    public ArrayList<ViewOfSubSystem> SubSystems = new ArrayList<>();
    public SystemView(){
        String style =
                "-fx-background-color: lightgray;" +
                        " -fx-background-radius: 20;" +
                        " -fx-border-color: darkgray;" +
                        " -fx-border-width: 2;" +
                        " -fx-border-radius: 10;";
        setStyle(style);
    }

}
