package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.portConnectingDetails.WireRenderer;

public class CurveLayerManager  {
    private final Pane layer;

    public CurveLayerManager(Pane layer) {
        this.layer = layer;
    }

    public void addCurve(Curve curve) {
        layer.getChildren().addFirst(curve);
    }

    public void removeCurve(Curve curve) {
        layer.getChildren().remove(curve);
    }

    public void addNode(Node node) {
        layer.getChildren().add(node);
    }

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
