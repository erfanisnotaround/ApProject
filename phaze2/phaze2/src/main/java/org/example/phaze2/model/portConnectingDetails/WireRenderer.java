package org.example.phaze2.model.portConnectingDetails;

import javafx.scene.Node;
import org.example.phaze2.model.levelDetails.necessary.Curve;

public interface WireRenderer {
    void renderNode(Node curve );
    void removeNode(Node curve );
    void renderCurve(Curve curve);
    void removeCurve(Curve curve);
}
