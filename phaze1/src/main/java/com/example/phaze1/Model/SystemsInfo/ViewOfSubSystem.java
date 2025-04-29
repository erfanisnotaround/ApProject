package com.example.phaze1.Model.SystemsInfo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ViewOfSubSystem extends Rectangle {
    public boolean doesItHaveEnterGate;
    public boolean doesItHavaExitGate;
    public GateType EnterGate;
    public GateType ExitGate;
    public Shape EnterPort;
    public Shape ExitPort;

    public ViewOfSubSystem(double width, double height) {
        super(width, height);
        setArcWidth(5);
        setArcHeight(5);
        setFill(Color.GRAY);
    }
}
