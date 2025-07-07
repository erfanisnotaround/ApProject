package org.example.phaze2.controllers.moverController;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Polyline;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;

public class PathMover extends AnimationTimer {
    private Curve curve;
    private Pocket node;
    private PathData path;
    private double s = 0;           // distance travelled (px)
    private double v;               // current speed (px/s)
    private double a;         // constant acceleration (px/s²)
    private long lastNs = -1;// last frame timestamp

    private Point2D latestLineDistance = new Point2D(0, 0);
    private Point2D currentLineDistance = new Point2D(0, 0);

    private double lineDistancePerMoveX = 0;
    private double lineDistancePerMoveY = 0;
    private final int STEPS = 100;
    private double AngleNeeded;
    private boolean rotate;
    public PathMover(Pocket node , double angle) {
        this.node = node;
        AngleNeeded = angle;
    }

    public synchronized void  move(Curve pl, double initialSpeed, double acceleration,
                      boolean rotateAlongTangent) {


        stop();


        this.curve = pl;
        this.path = PathData.fromPolyline(pl);
        this.v = initialSpeed;
        this.a = acceleration;
        this.rotate = rotateAlongTangent;

        this.s      = 0;
        this.lastNs = -1;


        node.setIsItMoved(true);
        curve.setIsItUsed(true);
        start();



    }

    @Override public void handle(long now) {


        if (lastNs < 0) {
            lastNs = now; return;
        }


        if (currentLineDistance.getX() < latestLineDistance.getX()) {
            currentLineDistance = currentLineDistance.add(lineDistancePerMoveX, lineDistancePerMoveY);
        }

        double dt = (now - lastNs) / 1_000_000_000.0; // seconds
        lastNs = now;

        s += v * dt + 0.5 * a * dt * dt;
        v += a * dt;
//        System.out.println(s);

        if (s >= path.total()) {
            s = path.total();
            stop();

            curve.setIsItUsed(false);
            node.setIsItMoved(false);


        }

        Point2D p = path.pointAt(s);
        Bounds b = node.getBoundsInLocal();
        double cx = p.getX() - b.getWidth()  * 0.5 + currentLineDistance.getX();
        double cy = p.getY() - b.getHeight() * 0.5 + currentLineDistance.getY();

        node.setLayoutX(cx);
        node.setLayoutY(cy);

        if (rotate) {
            node.setRotate(Math.toDegrees(path.angleAt(s)) + AngleNeeded);
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

    public void AddingImpactVector(double x, double y) {
        latestLineDistance = latestLineDistance.add(x, y);
        lineDistancePerMoveX = x/STEPS;
        lineDistancePerMoveY = y/STEPS;
    }


}
