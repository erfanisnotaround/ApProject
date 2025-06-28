package org.example.phaze2.model.portConnectingDetails;

import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.Curve;

public interface WireRenderer {
    void render(Curve curve );
    void remove(Curve curve );
}
