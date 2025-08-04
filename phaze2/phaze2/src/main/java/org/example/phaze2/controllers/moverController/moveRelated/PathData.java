package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record PathData(List<Point2D> pts, double[] cumLen, double total) {

    public static PathData fromPolyline(Polyline pl) {
        List<Double> raw = pl.getPoints();
        int n = raw.size() / 2;
        List<Point2D> pts = new ArrayList<>(n);
        for (int i = 0; i < raw.size(); i += 2)
            pts.add(new Point2D(raw.get(i), raw.get(i + 1)));

        double[] cum = new double[n];
        cum[0] = 0;
        for (int i = 1; i < n; i++) {
            cum[i] = cum[i - 1] + pts.get(i).distance(pts.get(i - 1));
        }
        return new PathData(pts, cum, cum[n - 1]);
    }

    public Point2D pointAt(double s) {

        if (s <= 0) return pts.get(0);
        if (s >= total) return pts.get(pts.size() - 1);

        int idx = Arrays.binarySearch(cumLen, s);
        if (idx < 0) idx = -(idx + 1);

        double segStart = cumLen[idx - 1], segEnd = cumLen[idx];
        double t = (s - segStart) / (segEnd - segStart);
        Point2D p0 = pts.get(idx - 1), p1 = pts.get(idx);
        return p0.interpolate(p1, t);
    }

    public double angleAt(double s) {
        if (s <= 0) return angle(pts.get(0), pts.get(1));
        if (s >= total) return angle(pts.get(pts.size() - 2), pts.get(pts.size() - 1));

        int idx = Arrays.binarySearch(cumLen, s);
        if (idx < 0) idx = -(idx + 1);
        Point2D p0 = pts.get(idx - 1), p1 = pts.get(idx);
        return angle(p0, p1);
    }
    public  double estimateS(Point2D position) {
        double minDist = Double.MAX_VALUE;
        double closestS = 0;

        double step = 1.0;

        for (double s = 0; s <= this.total(); s += step) {
            Point2D pt = this.pointAt(s);
            double dist = pt.distance(position);
            if (dist < minDist) {
                minDist = dist;
                closestS = s;
            }
        }

        return closestS;
    }
    public double distanceTo(Point2D q) {
        double best = Double.MAX_VALUE;

        for (int i = 1; i < pts.size(); i++) {
            Point2D a = pts.get(i - 1);          
            Point2D b = pts.get(i);

            Point2D ab = b.subtract(a);
            Point2D aq = q.subtract(a);

            double ab2 = ab.dotProduct(ab);
            if (ab2 == 0) continue;

            double t = Math.max(0, Math.min(1, aq.dotProduct(ab) / ab2));

            Point2D p = new Point2D(a.getX() + t * ab.getX(),
                    a.getY() + t * ab.getY());

            best = Math.min(best, p.distance(q));
        }
        return best;
    }
    private static double angle(Point2D a, Point2D b) { return Math.atan2(b.getY() - a.getY(), b.getX() - a.getX()); }
}
