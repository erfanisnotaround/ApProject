package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.example.phaze2.model.portConnectingDetails.CurveBuilder;

import java.util.ArrayList;
import java.util.List;

public class Curve extends Group implements CurveBuilder, Runnable {

    private final List<CubicCurve> segments = new ArrayList<>();
    private final List<Anchor>     anchors  = new ArrayList<>();

    private Color  stroke       = Color.DODGERBLUE;   // note: now mutable
    private double strokeWidth  = 4;

    public Connection connection;
    public BooleanProperty isItUsed = new SimpleBooleanProperty(false);

    /* ───────────────────────── build straight draft ───────────────────────── */
    @Override
    public void build(Point2D start, Point2D end) {

        getChildren().clear();        // wipe any previous draft
        segments.clear();
        anchors .clear();

        CubicCurve c = new CubicCurve(
                start.getX(), start.getY(),
                start.getX(), start.getY(),     // ctrl1 on start
                end  .getX(), end  .getY(),     // ctrl2 on end
                end  .getX(), end  .getY());
        style(c);

        Anchor a0 = createAnchor(start, c.startXProperty(), c.startYProperty());
        Anchor a1 = createAnchor(end,   c.endXProperty(),   c.endYProperty());

        c.controlX1Property().bindBidirectional(a0.centerXProperty());
        c.controlY1Property().bindBidirectional(a0.centerYProperty());
        c.controlX2Property().bindBidirectional(a1.centerXProperty());
        c.controlY2Property().bindBidirectional(a1.centerYProperty());

        anchors.add(a0); anchors.add(a1);
        segments.add(c);

        getChildren().addAll(c, a0, a1);
    }

    public void insertAnchor(CubicCurve seg, double t) {

        Split split = Split.from(seg, t);
        int idx = segments.indexOf(seg);

        segments.remove(idx);
        segments.add(idx,     split.left());
        segments.add(idx + 1, split.right());

        getChildren().remove(seg);
        getChildren().addAll(split.left(), split.right());

        Anchor a = createAnchor(split.shared(),
                split.right().startXProperty(),
                split.right().startYProperty());
        segmentsBindings(split, a);

        anchors.add(idx + 1, a);
        getChildren().add(a);

        retuneNeighbours(a);
    }

    public double ApproximateLength(int samplesPerSegment) {
        double len = 0;
        for (CubicCurve s : segments) len += Geometry.approxCubicLen(s, samplesPerSegment);
        return len;
    }
    public List<CubicCurve> getSegments() { return segments; }
    public List<Anchor>     getAnchors()  { return anchors;  }

    public void setFill(Color c) {
        stroke = c;
        for (CubicCurve s : segments) s.setStroke(c);
    }

    public double closestT(CubicCurve span, double mx, double my, int samples) {
        double bestT = 0, bestD = Double.MAX_VALUE;
        for (int i = 0; i <= samples; i++) {
            double t = (double) i / samples;
            Point2D p = Geometry.bezier(span, t);
            double d = p.distance(mx, my);
            if (d < bestD) { bestD = d; bestT = t; }
        }
        return bestT;
    }



    private Anchor createAnchor(Point2D p,
                                javafx.beans.property.DoubleProperty x,
                                javafx.beans.property.DoubleProperty y) {
        x.set(p.getX());  y.set(p.getY());
        Anchor a = new Anchor(Color.ORANGE, x, y ,() -> retuneNeighbours(a));
        return a;
    }
    private void style(CubicCurve c) {
        c.setStroke(stroke); c.setStrokeWidth(strokeWidth); c.setFill(null);
    }
    private void segmentsBindings(Split s, Anchor a) {
        s.left() .endXProperty().bindBidirectional(a.centerXProperty());
        s.left() .endYProperty().bindBidirectional(a.centerYProperty());
    }

    private void retuneNeighbours(Anchor moved) {
        int i = anchors.indexOf(moved);
        if (i < 0) return;

        if (i > 0) retuneSpan(i - 1);           // span on the left
        if (i < segments.size() - 1) retuneSpan(i);     // span on the right
    }
    private void retuneSpan(int spanIndex) {
        Anchor p0 = (spanIndex == 0)               ? anchors.get(0) : anchors.get(spanIndex - 1);
        Anchor p1 = anchors.get(spanIndex);
        Anchor p2 = anchors.get(spanIndex + 1);
        Anchor p3 = (spanIndex + 2 < anchors.size()) ? anchors.get(spanIndex + 2) : anchors.get(spanIndex + 1);

        final double t = 0.5;           // Catmull-Rom tension 0.5

        double c1x = p1.getCenterX() + (p2.getCenterX() - p0.getCenterX()) * t / 3;
        double c1y = p1.getCenterY() + (p2.getCenterY() - p0.getCenterY()) * t / 3;
        double c2x = p2.getCenterX() - (p3.getCenterX() - p1.getCenterX()) * t / 3;
        double c2y = p2.getCenterY() - (p3.getCenterY() - p1.getCenterY()) * t / 3;

        CubicCurve span = segments.get(spanIndex);
        span.setControlX1(c1x); span.setControlY1(c1y);
        span.setControlX2(c2x); span.setControlY2(c2y);
    }

    @Override public void run() { }
}
