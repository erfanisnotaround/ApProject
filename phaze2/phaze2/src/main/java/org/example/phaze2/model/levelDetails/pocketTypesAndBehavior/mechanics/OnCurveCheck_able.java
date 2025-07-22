package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import javafx.geometry.Point2D;
import org.example.phaze2.model.levelDetails.necessary.Curve;

public interface OnCurveCheck_able {

    Point2D ClosesPointOn(Point2D point , Curve curve);
}
