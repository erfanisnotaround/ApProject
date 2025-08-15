package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelSavesAndTheirPojo.CurvePojo;

import java.util.concurrent.atomic.AtomicReference;

public class PathMover extends AnimationTimer {
    private static final double EPS = 1e-4;
    private double multiplier = 1;
    private Curve curve;
    private PocketMain node;
    private PathData path;
    private double s = 0;
    private double v;
    private double a;
    private double commitedAcceleration = 0;
    private long lastNs = -1;
    private boolean paused = false;

    private Point2D latestLineDistance = new Point2D(0, 0);
    private Point2D currentLineDistance = new Point2D(0, 0);


    private Point2D latestLineDistanceForWholeMove = new Point2D(0, 0);
    private Point2D currentLineDistanceForWholeMove = new Point2D(0, 0);

    private double lineDistancePerMoveXForWhole = 0;
    private double lineDistancePerMoveYForWhole = 0;

    private final AtomicReference<Point2D> pendingKick = new AtomicReference<>(Point2D.ZERO);

    private double lineDistancePerMoveX = 0;
    private double lineDistancePerMoveY = 0;
    private double STEPS = 10;
    private double AngleNeeded;
    private boolean rotate;
    private GameState gameState;
    public PathMover(double angle , GameState gameState) {
        AngleNeeded = angle;
        this.gameState = gameState;
    }

    public synchronized void  move(Curve pl, double initialSpeed, double acceleration,
                      boolean rotateAlongTangent , double multiplier) {


        stop();



        this.curve = pl;
        this.path = PathData.fromPolyline(pl);
        this.v = initialSpeed * multiplier;
        this.a = acceleration * multiplier;

        this.commitedAcceleration = acceleration * multiplier;

        this.rotate = rotateAlongTangent;
        this.multiplier = multiplier;


        curve.setPocketMovingOnIt(node);
        node.setIsItMoved(true);
        curve.setIsItUsed(true);


        start();


    }

    @Override public void handle(long now) {

        if (paused) return;

        if (lastNs < 0) {
            lastNs = now; return;
        }


        Point2D kick = pendingKick.getAndSet(Point2D.ZERO);
        if (kick.getX() != 0 || kick.getY() != 0) {
            latestLineDistance = latestLineDistance.add(kick);
            lineDistancePerMoveX = kick.getX() / STEPS;
            lineDistancePerMoveY = kick.getY() / STEPS;
        }

        advanceKickTowardsTarget();


        boolean reachEnd = v >= 0 && s >= path.total();
        boolean reachStart = v <= 0 && s <=0;




        currentLineDistanceForWholeMove = currentLineDistanceForWholeMove.add(lineDistancePerMoveXForWhole , lineDistancePerMoveYForWhole);


        double dt = (now - lastNs) / 1_000_000_000.0;
        lastNs = now;

        s += v * dt + 0.5 * a * dt * dt;
        v += a * dt;

        if (reachStart){
            reverse();
        }


        if (reachEnd) {
            s = path.total();
            stop();
            curve.setPocketMovingOnIt(null);
            curve.setIsItUsed(false);
            node.setIsItMoved(false);
            node.StopStrategyMoving();

            return;
        }



        Point2D p = path.pointAt(s);
        Bounds b = node.getBoundsInLocal();
        double cx = p.getX() - b.getWidth()  * 0.5 + currentLineDistance.getX() + currentLineDistanceForWholeMove.getX();
        double cy = p.getY() - b.getHeight() * 0.5 + currentLineDistance.getY() + currentLineDistanceForWholeMove.getY();
//
//        double cx = p.getX() - b.getWidth()  * 0.5;
//        double cy = p.getY() - b.getHeight() * 0.5;


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



            for (PocketMain pocketMain : gameState.getResources().getPockets()) {
                pocketMain.getPathMover().stop();
            }

            return;
        }



        if (rotate) {
            node.setRotate(Math.toDegrees(path.angleAt(s)) + AngleNeeded);
            node.getHitBox().setRotate(Math.toDegrees(path.angleAt(s)) + AngleNeeded);
        }
    }

    public void reset() {
        s = 0;
        lastNs = -1;
        currentLineDistance = currentLineDistance.multiply(0);
        latestLineDistance = latestLineDistance.multiply(0);
//        start();
    }
    public void enqueueImpulse(double dx, double dy) {
        pendingKick.updateAndGet(prev -> new Point2D(prev.getX() + dx, prev.getY() + dy));
    }

    public void Initialize(){
        this.s = 0;
        this.lastNs = -1;
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


    public void AddWholeMoveVector(double x, double y ) {
        lineDistancePerMoveXForWhole = x * multiplier;
        lineDistancePerMoveYForWhole = y * multiplier;
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
    public double getMultiplier(){return multiplier;}
    public void setMultiplier(double multiplier) {this.multiplier = multiplier;}
    public double getS(){return s;}
    public double GetAcceleration(){
        return a;
    }
    public boolean GetRotate(){
        return rotate;
    }
    public double GetSteps(){
        return STEPS;
    }
    public double getLineDistancePerMoveX(){return lineDistancePerMoveX;}
    public double getLineDistancePerMoveY(){return lineDistancePerMoveY;}

    public double getLineDistancePerMoveXForWhole(){return lineDistancePerMoveXForWhole;}
    public double getLineDistancePerMoveYForWhole(){return lineDistancePerMoveYForWhole;}
    public void setA(double a) {this.a = a;}
    public void SetS(double s) {this.s = s;}

    public void setLineDistancePerMove(double lineDistancePerMoveX , double  lineDistancePerMoveY) {
        this.lineDistancePerMoveX = lineDistancePerMoveX;
        this.lineDistancePerMoveY = lineDistancePerMoveY;
    }
    public void setLineDistancePerMoveForWhole(double lineDistancePerMoveXForWhole , double  lineDistancePerMoveYForWhole) {
        this.lineDistancePerMoveXForWhole = lineDistancePerMoveXForWhole;
        this.lineDistancePerMoveYForWhole = lineDistancePerMoveYForWhole;
    }
    public void returnToCommitedAcceleration() {
        this.a = commitedAcceleration;
    }
    public void setCurve(Curve curve) {
        this.curve = curve;
    }
    public double getCommitedAcceleration(){
        return commitedAcceleration;
    }
    private void advanceKickTowardsTarget() {
        // amount still to apply
        Point2D remaining = latestLineDistance.subtract(currentLineDistance);
        double rx = remaining.getX();
        double ry = remaining.getY();

        // close enough? snap and stop stepping
        if (Math.abs(rx) <= EPS && Math.abs(ry) <= EPS) {
            currentLineDistance = latestLineDistance;
            lineDistancePerMoveX = 0;
            lineDistancePerMoveY = 0;
            return;
        }

        // move by at most one "step" toward the target so we don't overshoot
        double stepX = Math.copySign(Math.min(Math.abs(lineDistancePerMoveX), Math.abs(rx)), rx);
        double stepY = Math.copySign(Math.min(Math.abs(lineDistancePerMoveY), Math.abs(ry)), ry);

        currentLineDistance = currentLineDistance.add(stepX, stepY);
    }
    public void stopAndDetachNow() {
        if (curve != null) {
            curve.setIsItUsed(false);
            curve.setPocketMovingOnIt(null);

        }
        stop();


    }
    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }
}
