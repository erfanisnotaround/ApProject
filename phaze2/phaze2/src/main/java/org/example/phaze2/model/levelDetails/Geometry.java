package org.example.phaze2.model.levelDetails;

import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurve;

public class Geometry {
    static double approxCubicLen(CubicCurve c, int samples) {
        double len = 0;
        double prevX = c.getStartX(), prevY = c.getStartY();
        for (int i = 1; i <= samples; i++) {
            double t = (double) i / samples;
            Point2D pt = bezier(c, t);
            len += pt.distance(prevX, prevY);
            prevX = pt.getX(); prevY = pt.getY();
        }
        return len;
    }
    static Point2D bezier(CubicCurve c, double t) {
        double u = 1 - t, tt = t * t, uu = u * u;
        double x = uu*u * c.getStartX() +
                3*uu*t * c.getControlX1() +
                3*u*tt * c.getControlX2() +
                tt*t * c.getEndX();
        double y = uu*u * c.getStartY() +
                3*uu*t * c.getControlY1() +
                3*u*tt * c.getControlY2() +
                tt*t * c.getEndY();
        return new Point2D(x, y);
    }
    public static double cubic(double p0, double p1,
                               double p2, double p3, double t) {
        double u = 1 - t;
        return u*u*u * p0 +
                3*u*u*t * p1 +
                3*u*t*t * p2 +
                t*t*t * p3;
    }
}

