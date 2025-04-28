package com.example.phaze1.model.SystemsInfo;

import com.example.phaze1.model.GateType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewOfSubSystem extends Rectangle {
    boolean doesItHaveEnterGate;
    boolean doesItHavaExitGate;
    GateType EnterGate;
    GateType ExitGate;

    public ViewOfSubSystem(double width, double height) {
        super(width, height);
        setArcWidth(5);
        setArcHeight(5);
        setFill(Color.GRAY);
    }
}
