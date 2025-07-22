package org.example.phaze2.model.levelDetails.necessary;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.phaze2.model.constants.PortTypes;

public class SubSystemView extends Rectangle {
    private boolean doesItHaveEnterGate;
    private boolean doesItHavaExitGate;
    private PortTypes EnterGate;
    private PortTypes ExitGate;
    private Port EnterPort;
    private Port ExitPort;

    public SubSystemView(double width, double height) {
        super(width, height);
        setArcWidth(5);
        setArcHeight(5);
        setFill(Color.DARKORCHID);
    }

    public boolean DoesItHaveEnterGate() {
        return doesItHaveEnterGate;
    }

    public void setDoesItHaveEnterGate(boolean doesItHaveEnterGate) {
        this.doesItHaveEnterGate = doesItHaveEnterGate;
    }

    public boolean DoesItHavaExitGate() {
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

    public Port getEnterPort() {
        return EnterPort;
    }

    public void setEnterPort(Port enterPort) {
        EnterPort = enterPort;
    }

    public Port getExitPort() {
        return ExitPort;
    }

    public void setExitPort(Port exitPort) {
        ExitPort = exitPort;
    }
}
