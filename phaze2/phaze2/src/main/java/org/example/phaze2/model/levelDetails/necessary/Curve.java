package org.example.phaze2.model.levelDetails.necessary;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.example.phaze2.model.portConnectingDetails.CurveBuilder;

import java.util.ArrayList;
import java.util.List;

public class Curve extends Polyline implements CurveBuilder , Runnable {


    private PocketMain pocketMovingOnIt;
    private final int FullHP = 3;
    private int HP = FullHP;
    private double latestAcceptableLength;
    private List<Anchor> middlePoints = new ArrayList<>();
    private Point2D firstPoint;
    private Point2D lastPoint;
    private final double strokeWidth = 4;
    private Connection connection;
    private BooleanProperty isItUsed = new SimpleBooleanProperty(false);


    private double StrokeWidth = 4;
    public Curve() {
        setFill(Color.GREEN);
    }


    public void AddAnchor(Anchor a) {
        middlePoints.add(a);
    }
    public void RemoveAnchor(Anchor a) {
        middlePoints.remove(a);
    }

    public Point2D getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point2D firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point2D getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point2D lastPoint) {
        this.lastPoint = lastPoint;
    }

    @Override
    public void run() {

    }

    @Override
    public void build(Point2D start, Point2D end) {

        firstPoint = start;
        lastPoint  = end;

        List<Point2D> knots = new ArrayList<>();
        knots.add(start);
        middlePoints.forEach(a -> knots.add(a.getCenter()));
        knots.add(end);

        ObservableList<Double> poly = getPoints();
        poly.clear();
        if (knots.size() < 2) return;

        final double STEP = 0.05;   // smaller → smoother, larger → faster
        for (int i = 0; i < knots.size() - 1; i++) {
            Point2D p0 = (i == 0)               ? knots.get(i)     : knots.get(i - 1);
            Point2D p1 =                         knots.get(i);
            Point2D p2 =                         knots.get(i + 1);
            Point2D p3 = (i + 2 < knots.size()) ? knots.get(i + 2) : p2;

            for (double t = 0; t <= 1.0; t += STEP) {
                Point2D pt = catmullRom(p0, p1, p2, p3, t);
                poly.addAll(pt.getX(), pt.getY());
            }
        }
        poly.addAll(end.getX(), end.getY());
        setStrokeWidth(strokeWidth);
    }
    private static Point2D catmullRom(Point2D p0, Point2D p1,
                                      Point2D p2, Point2D p3, double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double x = 0.5 * ((2 * p1.getX())
                + (-p0.getX() + p2.getX()) * t
                + (2 * p0.getX() - 5 * p1.getX() + 4 * p2.getX() - p3.getX()) * t2
                + (-p0.getX() + 3 * p1.getX() - 3 * p2.getX() + p3.getX()) * t3);
        double y = 0.5 * ((2 * p1.getY())
                + (-p0.getY() + p2.getY()) * t
                + (2 * p0.getY() - 5 * p1.getY() + 4 * p2.getY() - p3.getY()) * t2
                + (-p0.getY() + 3 * p1.getY() - 3 * p2.getY() + p3.getY()) * t3);
        return new Point2D(x, y);
    }
    public double ApproximateLength() {
        ObservableList<Double> p = getPoints();
        double len = 0;
        for (int i = 2; i < p.size(); i += 2) {
            double x0 = p.get(i - 2), y0 = p.get(i - 1);
            double x1 = p.get(i    ), y1 = p.get(i + 1);
            len += Math.hypot(x1 - x0, y1 - y0);
        }
        return len;
    }
    public void setFill(Color color) {
        setStroke(color);
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isIsItUsed() {
        return isItUsed.get();
    }

    public BooleanProperty isItUsedProperty() {
        return isItUsed;
    }

    public void setIsItUsed(boolean isItUsed) {
        this.isItUsed.set(isItUsed);
    }


    public double getLatestAcceptableLength() {
        return latestAcceptableLength;
    }

    public void setLatestAcceptableLength(double latestAcceptableLength) {
        this.latestAcceptableLength = latestAcceptableLength;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public int getFullHP() {
        return FullHP;
    }
    public List<Anchor> getAnchors(){
        return middlePoints;
    }

    public PocketMain getPocketMovingOnIt() {
        return pocketMovingOnIt;
    }

    public void setPocketMovingOnIt(PocketMain pocketMovingOnIt) {
        this.pocketMovingOnIt = pocketMovingOnIt;
    }

}
