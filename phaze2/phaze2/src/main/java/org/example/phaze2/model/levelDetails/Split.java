package org.example.phaze2.model.levelDetails;

import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurve;

public record Split(CubicCurve left, CubicCurve right, Point2D shared) {
    static Split from(CubicCurve c, double t) {
        Point2D p0 = new Point2D(c.getStartX(), c.getStartY());
        Point2D p1 = new Point2D(c.getControlX1(), c.getControlY1());
        Point2D p2 = new Point2D(c.getControlX2(), c.getControlY2());
        Point2D p3 = new Point2D(c.getEndX(),   c.getEndY());

        Point2D q0 = lerp(p0, p1, t);
        Point2D q1 = lerp(p1, p2, t);
        Point2D q2 = lerp(p2, p3, t);

        Point2D r0 = lerp(q0, q1, t);
        Point2D r1 = lerp(q1, q2, t);

        Point2D s  = lerp(r0, r1, t);

        CubicCurve left  = new CubicCurve(p0.getX(), p0.getY(),
                q0.getX(), q0.getY(),
                r0.getX(), r0.getY(),
                s .getX(), s .getY());
        CubicCurve right = new CubicCurve(s .getX(), s .getY(),
                r1.getX(), r1.getY(),
                q2.getX(), q2.getY(),
                p3.getX(), p3.getY());

        copyStyle(c, left);
        copyStyle(c, right);
        return new Split(left, right, s);
    }
    private static Point2D lerp(Point2D a, Point2D b, double t) {
        return a.multiply(1 - t).add(b.multiply(t));
    }
    private static void copyStyle(CubicCurve src, CubicCurve dst) {
        dst.setStroke(src.getStroke());
        dst.setStrokeWidth(src.getStrokeWidth());
        dst.setFill(null);
    }
}