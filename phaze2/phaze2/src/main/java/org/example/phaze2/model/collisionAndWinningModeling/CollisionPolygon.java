// File: CollisionPolygon.java
package org.example.phaze2.model.collisionAndWinningModeling;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class CollisionPolygon {
    private final Point2D position;
    private final List<Point2D> vertices;
    private final List<Point2D> axes;

    public CollisionPolygon(Polygon polygon) {
        this.position = new Point2D(polygon.getLayoutX(), polygon.getLayoutY());
        this.vertices = calculateVertices(polygon);
        this.axes = calculateAxes();
    }

    /**
     * Projects this polygon onto a given axis.
     * @return A double array containing [min, max].
     */
    public double[] project(Point2D axis) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (Point2D vertex : vertices) {
            double dotProduct = vertex.dotProduct(axis);
            min = Math.min(min, dotProduct);
            max = Math.max(max, dotProduct);
        }
        return new double[]{min, max};
    }

    public List<Point2D> getAxes() {
        return this.axes;
    }

    // Simple getter for the pre-calculated vertices.
    public List<Point2D> getVertices() {
        return this.vertices;
    }

    // Simple getter for the polygon's position.
    public Point2D getPosition() {
        return this.position;
    }

    private static List<Point2D> calculateVertices(Polygon polygon) {
        List<Point2D> vertices = new ArrayList<>();
        ObservableList<Double> points = polygon.getPoints();
        for (int i = 0; i < points.size(); i += 2) {
            vertices.add(polygon.localToParent(points.get(i), points.get(i + 1)));
        }
        return vertices;
    }


    private List<Point2D> calculateAxes() {
        List<Point2D> calculatedAxes = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Point2D p1 = vertices.get(i);
            Point2D p2 = vertices.get(i + 1 == vertices.size() ? 0 : i + 1);
            Point2D edge = p1.subtract(p2);
            calculatedAxes.add(new Point2D(-edge.getY(), edge.getX()).normalize());
        }
        return calculatedAxes;
    }
}