package com.example.phaze1.model.SystemsInfo;

import com.example.phaze1.model.GateType;
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

/**
 * Manages user-drawn connections (wires) between gates of your SystemView objects,
 * enforcing a total wire-length budget and matching gate types.
 */
public class GateConnectorManager2 {
    /**
     * Holds metadata for each gate port.
     */
    public static class GatePortInfo {
        public final SystemView system;     // which UI System
        public final int        subIndex;   // which sub-system index
        public final boolean    isExit;     // exit‐gate vs enter‐gate
        public final GateType type;       // SQUARE or TRIANGLE

        public GatePortInfo(SystemView system, int subIndex, boolean isExit, GateType type) {
            this.system   = system;
            this.subIndex = subIndex;
            this.isExit    = isExit;
            this.type      = type;
        }
    }

    /**
     * A recorded connection between two ports, plus its polyline shape.
     */
    public static class Connection {
        public final GatePortInfo from;
        public final GatePortInfo to;
        public final Polyline     curve;
        public Connection(GatePortInfo from, GatePortInfo to, Polyline curve) {
            this.from  = from;
            this.to    = to;
            this.curve = curve;
        }
    }

    private final Pane lineLayer;
    private final WireManager wires;

    private final Map<Node,GatePortInfo> portInfo   = new HashMap<>();
    private final Set<Node>             exitGates  = new HashSet<>();
    private final Set<Node>             enterGates = new HashSet<>();
    private final List<Connection>      connections = new ArrayList<>();

    private Polyline currentCurve;
    private double   startX, startY;
    private Node     startGate;

    private static final int STEPS = 40;

    public GateConnectorManager2(Pane lineLayer, WireManager wires) {
        this.lineLayer = lineLayer;
        this.wires     = wires;
    }

    /**
     * Register an exit‑gate, providing its UI node and metadata.
     */
    public void registerExitGate(Node gate,
                                 SystemView system,
                                 int subIndex,
                                 GateType type) {
        GatePortInfo info = new GatePortInfo(system, subIndex, true, type);
        portInfo.put(gate, info);
        exitGates.add(gate);
        gate.addEventHandler(MouseEvent.MOUSE_PRESSED,  this::onPress);
        gate.addEventHandler(MouseEvent.MOUSE_DRAGGED,  this::onDrag);
        gate.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onRelease);
    }

    /**
     * Register an enter‑gate, providing its UI node and metadata.
     */
    public void registerEnterGate(Node gate,
                                  SystemView system,
                                  int subIndex,
                                  GateType type) {
        GatePortInfo info = new GatePortInfo(system, subIndex, false, type);
        portInfo.put(gate, info);
        enterGates.add(gate);
    }

    private void onPress(MouseEvent e) {
        Node gate = (Node)e.getSource();
        if (!exitGates.contains(gate)) return;

        Bounds lb = gate.getBoundsInLocal();
        Point2D center = gate.localToScene(lb.getWidth()/2, lb.getHeight()/2);
        Point2D start = lineLayer.sceneToLocal(center);

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
        double dx = ex - startX, dy = ey - startY;

        List<Double> pts = new ArrayList<>((STEPS+1)*2);
        for (int i = 0; i <= STEPS; i++) {
            double t = (double)i / STEPS;
            double x = startX + dx * t;
            double y = startY + dy * (t*t*t);
            pts.add(x); pts.add(y);
        }
        currentCurve.getPoints().setAll(pts);

        double len = approximateLength(currentCurve.getPoints());
        currentCurve.setStroke(wires.canUse(len) ? Color.GREEN : Color.RED);
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

        double finalLen = approximateLength(currentCurve.getPoints());
        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());

        GatePortInfo fromInfo = portInfo.get(startGate);
        for (Node gate : enterGates) {
            Bounds eb = gate.getBoundsInLocal();
            Point2D cen = gate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (cen.distance(scenePt) < 10) {
                GatePortInfo toInfo = portInfo.get(gate);
                // enforce matching types and budget
                if (fromInfo.type == toInfo.type && wires.canUse(finalLen)) {
                    wires.addWire(finalLen);
                    currentCurve.setStroke(Color.GREEN);
                    connections.add(new Connection(fromInfo, toInfo, currentCurve));
                } else {
                    lineLayer.getChildren().remove(currentCurve);
                }
                cleanup(); e.consume(); return;
            }
        }
        // didn't drop on a valid enter gate
        lineLayer.getChildren().remove(currentCurve);
        cleanup();
        e.consume();
    }

    private double approximateLength(ObservableList<Double> pts) {
        double sum = 0;
        for (int i = 2; i < pts.size(); i+=2) {
            double x0 = pts.get(i-2), y0 = pts.get(i-1);
            double x1 = pts.get(i),   y1 = pts.get(i+1);
            sum += Math.hypot(x1-x0, y1-y0);
        }
        return sum;
    }

    private void cleanup() {
        currentCurve = null;
        startGate    = null;
    }

    /** Returns every GatePortInfo‑typed connection */
    public List<Connection> getConnections() {
        return Collections.unmodifiableList(connections);
    }

    /** Returns raw Polylines on the lineLayer */
    public List<Polyline> getConnectionShapes() {
        List<Polyline> list = new ArrayList<>();
        for (Node n : lineLayer.getChildren()) if (n instanceof Polyline) list.add((Polyline)n);
        return Collections.unmodifiableList(list);
    }
}
