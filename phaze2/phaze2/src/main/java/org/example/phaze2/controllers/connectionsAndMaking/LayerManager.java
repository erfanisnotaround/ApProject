package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.necessary.Curve;

public class LayerManager {
    private final Pane layer;
    private final Pane paneOfActions;

    public LayerManager(Pane layer , Pane paneOfActions) {
        this.layer = layer;
        this.paneOfActions = paneOfActions;
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
