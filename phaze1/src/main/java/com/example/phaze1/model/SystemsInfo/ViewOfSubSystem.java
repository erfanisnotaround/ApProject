package com.example.phaze1.model.SystemsInfo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewOfSubSystem extends Rectangle {
    public boolean doesItHaveEnterGate;
    public boolean doesItHavaExitGate;
    public GateType EnterGate;
    public GateType ExitGate;

    public ViewOfSubSystem(double width, double height) {
        super(width, height);
        setArcWidth(5);
        setArcHeight(5);
        setFill(Color.GRAY);
    }
}
