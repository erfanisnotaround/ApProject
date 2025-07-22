package org.example.phaze2.model.levelDetails.necessary;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class Port extends Group {
    private Shape shape;
    private PortInfo portInfo;
    public Port(Shape shape) {
        this.shape = shape;
        getChildren().add(shape);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        getChildren().remove(this.shape);
        this.shape = shape;
        getChildren().add(shape);

    }

    public PortInfo getPortInfo() {
        return portInfo;
    }

    public void setPortInfo(PortInfo portInfo) {
        this.portInfo = portInfo;
    }
}
