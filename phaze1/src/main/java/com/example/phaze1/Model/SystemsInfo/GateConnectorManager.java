package com.example.phaze1.Model.SystemsInfo;

import com.example.phaze1.Model.WireManager;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import java.util.*;

public class GateConnectorManager {
    private GatePortInfo gatePortInfo;
    private  Connection connection;

    private final Pane lineLayer;
    private final WireManager wires ;

    private final Map<Node,GatePortInfo> portInfo  = new HashMap<>();
    private final Set<Node>  exitGates  = new HashSet<>();
    private final Set<Node> enterGates = new HashSet<>();
    private final Map<GatePortInfo , Connection> exitConnections = new HashMap<>();
    private final List<Connection>  connections = new ArrayList<>();

    private Curve currentCurve;
    private double   startX, startY;
    private Node     startGate;

    private static final int STEPS = 40;

    public GateConnectorManager(Pane lineLayer, WireManager wires) {
        this.lineLayer = lineLayer;
        this.wires     = wires;
        if (wires == null) System.out.println("wires is null");
    }

    public void registerExitGate(Node gate, SystemView system, int subIndex, GateType type) {
        GatePortInfo info = new GatePortInfo(system, subIndex, true, type);
        portInfo.put(gate, info);
        exitGates.add(gate);
        gate.addEventHandler(MouseEvent.MOUSE_PRESSED,  this::onPress);
        gate.addEventHandler(MouseEvent.MOUSE_DRAGGED,  this::onDrag);
        gate.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onRelease);
    }
    public void registerEnterGate(Node gate, SystemView system, int subIndex, GateType type) {
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

        currentCurve = new Curve();
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

        double len = currentCurve.ApproximateLength();
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

        double finalLen = currentCurve.ApproximateLength();
        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());

        GatePortInfo fromInfo = portInfo.get(startGate);
        for (Node gate : enterGates) {
            Bounds eb = gate.getBoundsInLocal();
            Point2D cen = gate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (cen.distance(scenePt) < 10) {
                GatePortInfo toInfo = portInfo.get(gate);
                if (fromInfo.type == toInfo.type && wires.canUse(finalLen)) {
                    Connection newConnection = new Connection(fromInfo, toInfo, currentCurve);
                    wires.addWire(finalLen);
                    currentCurve.setStroke(Color.GREEN);
                    exitConnections.put(portInfo.get(startGate),newConnection );
                    connections.add(newConnection);
                } else {
                    lineLayer.getChildren().remove(currentCurve);
                }
                cleanup(); e.consume(); return;
            }
        }
        lineLayer.getChildren().remove(currentCurve);
        cleanup();
        e.consume();
    }

    private void cleanup() {
        currentCurve = null;
        startGate    = null;
    }
    public  Map<GatePortInfo , Connection> getExitConnections() {
        return exitConnections;
    }
    public Map<Node , GatePortInfo> GateInfo(){
        return portInfo;
    }
    public List<Connection> getConnections() {
        return Collections.unmodifiableList(connections);
    }

    public List<Polyline> getConnectionShapes() {
        List<Polyline> list = new ArrayList<>();
        for (Node n : lineLayer.getChildren()) if (n instanceof Polyline) list.add((Polyline)n);
        return Collections.unmodifiableList(list);
    }
}
