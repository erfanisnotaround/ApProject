package org.example.phaze2.controllers.moverController.checkings;

import javafx.geometry.Bounds;
import javafx.scene.shape.Polyline;

public final class GeometryUtil {

    /* Region-code bits for Cohen–Sutherland */
    private static final int INSIDE = 0;
    private static final int LEFT   = 1;
    private static final int RIGHT  = 2;
    private static final int BOTTOM = 4;
    private static final int TOP    = 8;

    public static boolean polylineIntersectsRect(Polyline pl, Bounds r) {

        // 0) AABB reject – almost free
        if (!pl.getBoundsInParent().intersects(r)) return false;

        var pts = pl.getPoints();
        for (int i = 2; i < pts.size(); i += 2) {
            double x1 = pts.get(i - 2), y1 = pts.get(i - 1);
            double x2 = pts.get(i    ), y2 = pts.get(i + 1);

            if (segmentIntersectsRect(x1, y1, x2, y2, r)) return true;
        }
        return false;
    }

    /* Cohen–Sutherland, no allocations */
    private static boolean segmentIntersectsRect(double x1, double y1,
                                                 double x2, double y2,
                                                 Bounds r) {

        double xmin = r.getMinX(), xmax = r.getMaxX();
        double ymin = r.getMinY(), ymax = r.getMaxY();

        int code1 = computeCode(x1, y1, xmin, ymin, xmax, ymax);
        int code2 = computeCode(x2, y2, xmin, ymin, xmax, ymax);

        while (true) {
            if ((code1 | code2) == 0) {           // both inside
                return true;
            }
            if ((code1 & code2) != 0) {           // both outside same region
                return false;
            }

            // choose an endpoint that is outside
            int outCode = (code1 != 0) ? code1 : code2;
            double x = 0, y = 0;

            if ((outCode & TOP) != 0) {           // above
                x = x1 + (x2 - x1) * (ymax - y1) / (y2 - y1);
                y = ymax;
            } else if ((outCode & BOTTOM) != 0) { // below
                x = x1 + (x2 - x1) * (ymin - y1) / (y2 - y1);
                y = ymin;
            } else if ((outCode & RIGHT) != 0) {  // right
                y = y1 + (y2 - y1) * (xmax - x1) / (x2 - x1);
                x = xmax;
            } else if ((outCode & LEFT) != 0) {   // left
                y = y1 + (y2 - y1) * (xmin - x1) / (x2 - x1);
                x = xmin;
            }

            // move the outside point to the intersection
            if (outCode == code1) {
                x1 = x; y1 = y; code1 = computeCode(x1, y1, xmin, ymin, xmax, ymax);
            } else {
                x2 = x; y2 = y; code2 = computeCode(x2, y2, xmin, ymin, xmax, ymax);
            }
        }
    }

    private static int computeCode(double x, double y,
                                   double xmin, double ymin,
                                   double xmax, double ymax) {
        int code = INSIDE;
        if (x < xmin) code |= LEFT;   else if (x > xmax) code |= RIGHT;
        if (y < ymin) code |= BOTTOM; else if (y > ymax) code |= TOP;
        return code;
    }
}
