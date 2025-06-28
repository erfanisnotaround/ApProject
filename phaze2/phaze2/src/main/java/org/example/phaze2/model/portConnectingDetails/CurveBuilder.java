package org.example.phaze2.model.portConnectingDetails;

import javafx.geometry.Point2D;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.PortInfo;

public interface CurveBuilder {
    void build(Point2D StartPoint , Point2D EndPoint);
}
