package com.example.phaze1.Model.SystemsInfo;

import com.example.phaze1.controllers.ControllingPocketMovement.TimeLineAnimator;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Curve extends Polyline {
    Color color ;
    double StrokeWidth;
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
    public void makeMovementOnThis(Node pocket , Connection connection){
        GatePortInfo destination = connection.to;
        TimeLineAnimator.animateAlong(this , pocket , Duration.seconds(5));
//        PathTransition pt = new PathTransition(Duration.seconds(5) , getPath(connection.curve), pocket);
//        pt.play();
    }
    public Path getPath(Polyline poly){
        ObservableList<Double> pts = poly.getPoints();
        Path path = new Path();
        if (pts.size() < 2) return path;
        path.getElements().add(new MoveTo(pts.get(0), pts.get(1)));
        for (int i = 2; i < pts.size(); i += 2) {
            path.getElements().add(new LineTo(pts.get(i), pts.get(i+1)));
        }
        return path;

    }

}
