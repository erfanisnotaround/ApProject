package com.example.phaze1.model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class System extends Pane {
    double x;
    double y;
    String systemID;
    boolean isItStartSystem;
    boolean isItFinisherSystem;
    int numberOfSubSystems;
    boolean isTheLightOn;
    ArrayList<ViewOfSubSystem> SubSystems = new ArrayList<>();

}
