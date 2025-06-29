package org.example.phaze2.model.levelDetails;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.example.phaze2.model.constants.PortTypes;

import java.awt.*;

public class SubSystemView extends Rectangle {
    private boolean doesItHaveEnterGate;
    private boolean doesItHavaExitGate;
    private PortTypes EnterGate;
    private PortTypes ExitGate;
    private Shape EnterPort;
    private Shape ExitPort;

    public SubSystemView(double width, double height) {
        super(width, height);
        setArcWidth(5);
        setArcHeight(5);
        setFill(Color.DARKORCHID);
    }

    public boolean isDoesItHaveEnterGate() {
        return doesItHaveEnterGate;
    }

    public void setDoesItHaveEnterGate(boolean doesItHaveEnterGate) {
        this.doesItHaveEnterGate = doesItHaveEnterGate;
    }

    public boolean isDoesItHavaExitGate() {
        return doesItHavaExitGate;
    }

    public void setDoesItHavaExitGate(boolean doesItHavaExitGate) {
        this.doesItHavaExitGate = doesItHavaExitGate;
    }

    public PortTypes getEnterGate() {
        return EnterGate;
    }

    public void setEnterGate(PortTypes enterGate) {
        EnterGate = enterGate;
    }

    public PortTypes getExitGate() {
        return ExitGate;
    }

    public void setExitGate(PortTypes exitGate) {
        ExitGate = exitGate;
    }

    public Shape getEnterPort() {
        return EnterPort;
    }

    public void setEnterPort(Shape enterPort) {
        EnterPort = enterPort;
    }

    public Shape getExitPort() {
        return ExitPort;
    }

    public void setExitPort(Shape exitPort) {
        ExitPort = exitPort;
    }
}
