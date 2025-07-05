package org.example.phaze2.controllers.moverController;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Polyline;

public class PathMover extends AnimationTimer {

    private Node node;
    private PathData path;
    private double s = 0;           // distance travelled (px)
    private double v;               // current speed (px/s)
    private double a;         // constant acceleration (px/s²)
    private long lastNs = -1;       // last frame timestamp

    private boolean rotate;
    public PathMover(Node node) {
        this.node = node;
    }

    public void  move(Polyline pl, double initialSpeed, double acceleration,
                     boolean rotateAlongTangent) {
        this.path = PathData.fromPolyline(pl);
        this.v = initialSpeed;
        this.a = acceleration;
        this.rotate = rotateAlongTangent;

        start();



    }

    @Override public void handle(long now) {
        if (lastNs < 0) { lastNs = now; return; }
        double dt = (now - lastNs) / 1_000_000_000.0; // seconds
        lastNs = now;

        s += v * dt + 0.5 * a * dt * dt;
        v += a * dt;

        if (s >= path.total()) {
            s = path.total();
            stop();
        }


        Point2D p = path.pointAt(s);
        Bounds b = node.getBoundsInLocal();            // size of the node itself
        double cx = p.getX() - b.getWidth()  * 0.5;    // centre it horizontally
        double cy = p.getY() - b.getHeight() * 0.5;    // centre it vertically

        node.setLayoutX(cx);
        node.setLayoutY(cy);

        if (rotate) {
            node.setRotate(Math.toDegrees(path.angleAt(s)));
        }
    }

    public void restart(double startSpeed) {
        s = 0;
        v = startSpeed;
        lastNs = -1;
        start();
    }

    public void reverse() {
        this.v *= -1;
        this.a *= -1;
    }
    public void setSpeed(double speed) {
        this.v = speed;
    }
    public double getSpeed() {
        return v;
    }

}
