package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.scene.Node;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.portConnectingDetails.WireRenderer;

public class WireRendererManager implements WireRenderer {
    private final LayerManager layerManager;
    public WireRendererManager(LayerManager layerManager) {
        this.layerManager = layerManager;
    }
    public LayerManager getLayerManager() {
        return layerManager;
    }
    @Override
    public void renderNode(Node object) {
        layerManager.addNode(object);
    }

    @Override
    public void removeNode(Node object) {
        layerManager.removeNode(object);
    }

    @Override
    public void renderCurve(Curve curve) {
        layerManager.addCurve(curve);
    }

    @Override
    public void removeCurve(Curve curve) {
        layerManager.removeCurve(curve);
    }
}
