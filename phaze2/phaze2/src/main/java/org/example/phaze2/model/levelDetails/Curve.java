package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.example.phaze2.model.portConnectingDetails.CurveBuilder;

import java.util.ArrayList;
import java.util.List;

public class Curve extends Polyline implements CurveBuilder {
    private final int STEPS = 40;
    public Connection connection;
    public BooleanProperty isItUsed = new SimpleBooleanProperty(false);
    public Color color ;
    public double StrokeWidth;
    public double ApproximateLength() {
        ObservableList<Double> points = getPoints();
        double sum = 0;
        for (int i = 2; i < points.size(); i+=2) {
            double x0 = points.get(i-2), y0 = points.get(i-1);
            double x1 = points.get(i),   y1 = points.get(i+1);
            sum += Math.hypot(x1-x0, y1-y0);
        }
        return sum;
    }

    @Override
    public void build(Point2D StartPoint, Point2D EndPoint) {
        double dx = EndPoint.getX() - StartPoint.getX();
        double dy = EndPoint.getY() - StartPoint.getY();

        List<Double> points = new ArrayList<>();
        for (int i = 0; i <= STEPS; i++) {
            double t = (double) i / STEPS;
            points.add(StartPoint.getX() + dx * t);
            points.add(StartPoint.getY() + dy * Math.pow(t, 3));
        }


        this.getPoints().setAll(points);
    }
}
