package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.necessary.Curve;

public class CurveLayerManager  {
    private final Pane layer;

    public CurveLayerManager(Pane layer) {
        this.layer = layer;
    }

    public void addCurve(Node curve) {
        layer.getChildren().addFirst(curve);
    }

    public void removeCurve(Curve curve) {
        layer.getChildren().remove(curve);
    }

    public void addNode(Node node) {
        layer.getChildren().add(node);
    }
    public void removeNode(Node node) {layer.getChildren().remove(node);}

    public void requestFocus() {
        layer.requestFocus();
    }

    public void setFocusTraversable(boolean value) {
        layer.setFocusTraversable(value);
    }

    public Pane getLayer() {
        return layer;
    }

}
