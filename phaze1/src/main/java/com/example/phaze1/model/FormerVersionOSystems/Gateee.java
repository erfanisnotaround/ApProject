package com.example.phaze1.model.FormerVersionOSystems;

import com.example.phaze1.model.SystemsInfo.GateType;
import com.example.phaze1.model.WireManager;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import java.util.*;

public class Gateee {
    private List<connection> connections = new ArrayList<>();
    private final Pane    lineLayer;
    private final WireManager wires;
    private final Set<Node> exitGates  = new HashSet<>();
    private final Set<Node> enterGates = new HashSet<>();

    private Polyline currentCurve;
    private double   startX, startY;
    private Node     startGate;

    private static final int STEPS = 40;

    public Gateee(Pane lineLayer, WireManager wires) {
        this.lineLayer = lineLayer;
        this.wires     = wires;
    }

    public void registerExitGate(Node gate) {
        exitGates.add(gate);
        gate.addEventHandler(MouseEvent.MOUSE_PRESSED,  this::onPress);
        gate.addEventHandler(MouseEvent.MOUSE_DRAGGED,  this::onDrag);
        gate.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onRelease);
    }

    public void registerEnterGate(Node gate) {
        enterGates.add(gate);
    }

    private void onPress(MouseEvent e) {
        Node gate = (Node)e.getSource();
        if (!exitGates.contains(gate)) return;

        Bounds lb = gate.getBoundsInLocal();
        Point2D sceneC = gate.localToScene(lb.getWidth()/2, lb.getHeight()/2);
        Point2D start  = lineLayer.sceneToLocal(sceneC);

        startX = start.getX();
        startY = start.getY();
        startGate = gate;

        currentCurve = new Polyline();
        currentCurve.setStrokeWidth(3);
        currentCurve.setStroke(Color.BLACK);
        lineLayer.getChildren().add(currentCurve);

        e.consume();
    }

    private void onDrag(MouseEvent e) {
        if (currentCurve == null) return;

        Point2D p = lineLayer.sceneToLocal(e.getSceneX(), e.getSceneY());
        double ex = p.getX(), ey = p.getY();

//         build cubic curve y = startY + (ey-startY)*(t^3),
//         x = startX + (ex-startX)*t
        double dx = ex - startX;
        double dy = ey - startY;

        List<Double> pts = new ArrayList<>( (STEPS+1)*2 );
        for (int i = 0; i <= STEPS; i++) {
            double t = (double)i / STEPS;
            double x = startX + dx * t;
            double y = startY + dy * (t*t*t);
            pts.add(x);
            pts.add(y);
        }
        currentCurve.getPoints().setAll(pts);

        double length = approximatePolylineLength(currentCurve);
        currentCurve.setStroke(wires.canUse(length) ? Color.GREEN : Color.RED);

        e.consume();
    }

    private void onRelease(MouseEvent e) {
        if (currentCurve == null) return;

        Point2D p = lineLayer.sceneToLocal(e.getSceneX(), e.getSceneY());
        double ex = p.getX(), ey = p.getY();
        double dx = ex - startX, dy = ey - startY;
        List<Double> pts = new ArrayList<>((STEPS+1)*2);
        for (int i = 0; i <= STEPS; i++) {
            double t = (double)i / STEPS;
            pts.add(startX + dx*t);
            pts.add(startY + dy*(t*t*t));
        }
        currentCurve.getPoints().setAll(pts);

        double finalLen = approximatePolylineLength(currentCurve);
        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());

        for (Node enterGate : enterGates) {
            Bounds eb = enterGate.getBoundsInLocal();
            Point2D center = enterGate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (center.distance(scenePt) < 5) {
                GateType startType = (GateType)startGate.getUserData();
                GateType endType = (GateType)enterGate.getUserData();
                if (startType != endType){
                    lineLayer.getChildren().remove(currentCurve);
                    cleanup();
                    e.consume();
                    return;
                }
                if (wires.canUse(finalLen)) {
                    wires.addWire(finalLen);
                    currentCurve.setStroke(Color.GREEN);
                    connections.add(new connection((GateType) startGate.getUserData(),(GateType) enterGate.getUserData(),currentCurve));
                } else {
                    lineLayer.getChildren().remove(currentCurve);
                }
                cleanup();
                e.consume();
                return;
            }
        }

        lineLayer.getChildren().remove(currentCurve);
        cleanup();
        e.consume();
    }

    private double approximatePolylineLength(Polyline poly) {
        double len = 0;
        ObservableList<Double> pts = poly.getPoints();
        for (int i = 2; i < pts.size(); i += 2) {
            double x0 = pts.get(i-2), y0 = pts.get(i-1);
            double x1 = pts.get(i  ), y1 = pts.get(i+1);
            len += Math.hypot(x1 - x0, y1 - y0);
        }
        return len;
    }

    private void cleanup() {
        currentCurve = null;
        startGate    = null;
    }
    public List<connection> getConnection() {
        return connections;
    }
    public List<Polyline> getConnections() {
        List<Polyline> list = new ArrayList<>();
        for (Node n : lineLayer.getChildren()) {
            if (n instanceof Polyline) list.add((Polyline)n);
        }
        return Collections.unmodifiableList(list);
    }
}
