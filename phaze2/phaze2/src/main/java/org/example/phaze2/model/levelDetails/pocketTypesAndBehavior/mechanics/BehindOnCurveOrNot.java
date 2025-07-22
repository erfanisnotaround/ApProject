package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import javafx.geometry.Point2D;
import org.example.phaze2.controllers.moverController.moveRelated.PathData;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class BehindOnCurveOrNot implements OnCurveCheck_able {

    @Override
    public Point2D ClosesPointOn(Point2D point, Curve curve) {
        PathData path1 = PathData.fromPolyline(curve);
        double closestS = 0;
        double minDist = Double.MAX_VALUE;

        for (double s = 0; s < path1.total(); s += 1) {
            Point2D point1 = path1.pointAt(s);
            double dist = point1.distance(point);
            if (dist < minDist) {
                minDist = dist;
                closestS = s;
            }
        }
        return path1.pointAt(closestS);
    }

    public Point2D PocketDirectionCurve(Point2D point, Curve curve) {
        PathData path2 =PathData.fromPolyline(curve);
        double s2 = path2.estimateS(point);
        return new Point2D(
                Math.cos(path2.angleAt(s2)),
                Math.sin(path2.angleAt(s2))
        );
    }
    public boolean BehindOrNot(Point2D OurPoint , Curve curve , Point2D closestPoint) {
        Point2D StartingPointDirection = PocketDirectionCurve(OurPoint, curve);
        Point2D toClosest = closestPoint.subtract(OurPoint);
        double dot = toClosest.normalize().dotProduct(StartingPointDirection.normalize());

        return dot > 0;

    }


}
