package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class PathMover extends AnimationTimer {
    private double multiplier = 1;
    private Curve curve;
    private PocketMain node;
    private PathData path;
    private double s = 0;           // distance travelled (px)
    private double v;               // current speed (px/s)
    private double a;         // constant acceleration (px/s²)
    private long lastNs = -1;// last frame timestamp

    private Point2D latestLineDistance = new Point2D(0, 0);
    private Point2D currentLineDistance = new Point2D(0, 0);


    private Point2D latestLineDistanceForWholeMove = new Point2D(0, 0);
    private Point2D currentLineDistanceForWholeMove = new Point2D(0, 0);

    private double lineDistancePerMoveXForWhole = 0;
    private double lineDistancePerMoveYForWhole = 0;



    private double lineDistancePerMoveX = 0;
    private double lineDistancePerMoveY = 0;
    private double STEPS = 200;
    private double AngleNeeded;
    private boolean rotate;
    public PathMover(double angle) {

        AngleNeeded = angle;
    }

    public synchronized void  move(Curve pl, double initialSpeed, double acceleration,
                      boolean rotateAlongTangent , double multiplier) {


        stop();


        this.curve = pl;
        this.path = PathData.fromPolyline(pl);
        this.v = initialSpeed * multiplier;
        this.a = acceleration * multiplier;
        this.rotate = rotateAlongTangent;

        this.s      = 0;
        this.lastNs = -1;
        this.multiplier = multiplier;


        node.setIsItMoved(true);
        curve.setIsItUsed(true);
        curve.setPocketMovingOnIt(node);
        start();


    }

    @Override public void handle(long now) {


        if (lastNs < 0) {
            lastNs = now; return;
        }

        boolean reachEnd = v >= 0 && s >= path.total();
        boolean reachStart = v <= 0 && s <=0;


        if (currentLineDistance.getX() < latestLineDistance.getX()) {
            currentLineDistance = currentLineDistance.add(lineDistancePerMoveX, lineDistancePerMoveY);
        }

        currentLineDistanceForWholeMove = currentLineDistanceForWholeMove.add(lineDistancePerMoveXForWhole, lineDistancePerMoveYForWhole);


        double dt = (now - lastNs) / 1_000_000_000.0;
        lastNs = now;

        s += v * dt + 0.5 * a * dt * dt;
        v += a * dt;
//        System.out.println(s);


        if (reachEnd || reachStart) {
            if (reachStart){
                reverse();
                s=0;
                return;
            }
            s = path.total();
            stop();

            node.StopStrategyMoving();
            curve.setIsItUsed(false);
            node.setIsItMoved(false);
            curve.setPocketMovingOnIt(null);


        }



        Point2D p = path.pointAt(s);
        Bounds b = node.getBoundsInLocal();
//        double cx = p.getX() - b.getWidth()  * 0.5 + currentLineDistance.getX() + currentLineDistanceForWholeMove.getX();
//        double cy = p.getY() - b.getHeight() * 0.5 + currentLineDistance.getY() + currentLineDistanceForWholeMove.getY();

        double cx = p.getX() - b.getWidth()  * 0.5;
        double cy = p.getY() - b.getHeight() * 0.5;


        node.setPlaceOfX(p.getX());
        node.setPlaceOfY(p.getY());



        node.setLayoutX(cx);
        node.setLayoutY(cy);


        node.getHitBox().setLayoutX(cx);
        node.getHitBox().setLayoutY(cy);

        node.setAvailableTime(node.getAvailableTime() - (3 * multiplier));
        if (node.getAvailableTime() <= 0) {
            stop();
            node.setLayoutX(cx);
            node.setLayoutY(cy);

            return;
        }



        if (rotate) {
            node.setRotate(Math.toDegrees(path.angleAt(s)) + AngleNeeded);
            node.getHitBox().setRotate(Math.toDegrees(path.angleAt(s)) + AngleNeeded);
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
    public Curve getCurve() {
        return curve;
    }

    public void AddingImpactVector(double x, double y , double STEPS) {
        this.STEPS = STEPS;
        latestLineDistance = latestLineDistance.add(x, y);
        lineDistancePerMoveX = x/STEPS;
        lineDistancePerMoveY = y/STEPS;
    }
    public void AddWholeMoveVector(double x, double y ) {
        lineDistancePerMoveXForWhole= x;
        lineDistancePerMoveYForWhole= y;

    }
    public void setNode(PocketMain node) {
        this.node = node;
    }

    public void setCurrentLineDistance(Point2D currentLineDistance) {
        this.currentLineDistance = currentLineDistance;
    }

    public Point2D getCurrentLineDistance() {
        return currentLineDistance;
    }

    public void setLatestLineDistance(Point2D latestLineDistance) {
        this.latestLineDistance = latestLineDistance;
    }
    public Point2D getLatestLineDistance() {
        return latestLineDistance;
    }

    public Point2D getLatestLineDistanceForWholeMove() {
        return latestLineDistanceForWholeMove;
    }

    public void setLatestLineDistanceForWholeMove(Point2D latestLineDistanceForWholeMove) {
        this.latestLineDistanceForWholeMove = latestLineDistanceForWholeMove;
    }

    public Point2D getCurrentLineDistanceForWholeMove() {
        return currentLineDistanceForWholeMove;
    }

    public void setCurrentLineDistanceForWholeMove(Point2D currentLineDistanceForWholeMove) {
        this.currentLineDistanceForWholeMove = currentLineDistanceForWholeMove;
    }
    public PathData getPath() {
        return path;
    }
    public PocketMain getPocketMain() {
        return node;
    }
    public double distanceRemains(){
        return path.total() - s;
    }
    public void moveForward() {
        if (v <= 0 && a <= 0){
            v *= -1;
            a *= -1;
        }
    }
    public void moveBackward() {
        if (v >= 0){
            v *= -1;
            a *= -1;
        }
    }
    public double getAngleNeeded(){
        return AngleNeeded;
    }
    public void setAngleNeeded(double angleNeeded) {
        AngleNeeded = angleNeeded;
    }


}
