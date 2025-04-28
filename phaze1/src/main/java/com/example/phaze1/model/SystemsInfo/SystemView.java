package com.example.phaze1.model.SystemsInfo;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SystemView extends Pane {
    double x;
    double y;
    String systemID;
    boolean isItStartSystem;
    boolean isItFinisherSystem;
    int numberOfSubSystems;
    boolean isTheLightOn;
    ArrayList<ViewOfSubSystem> SubSystems = new ArrayList<>();
    public SystemView(){
        String style =
                "-fx-background-color: lightgray;" +
                        " -fx-background-radius: 20;" +
                        " -fx-border-color: darkgray;" +
                        " -fx-border-width: 2;" +
                        " -fx-border-radius: 20;";
        setStyle(style);
    }

}
