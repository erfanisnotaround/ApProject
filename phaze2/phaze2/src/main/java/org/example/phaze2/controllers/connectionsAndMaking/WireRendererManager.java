package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.portConnectingDetails.WireRenderer;

public class WireRendererManager implements WireRenderer {
    private final CurveLayerManager layerManager;
    public WireRendererManager(CurveLayerManager layerManager) {
        this.layerManager = layerManager;
    }
    public CurveLayerManager getLayerManager() {
        return layerManager;
    }
    @Override
    public void render(Curve curve) {

        layerManager.addCurve(curve);
    }

    @Override
    public void remove(Curve curve) {
        layerManager.removeCurve(curve);
    }
}
