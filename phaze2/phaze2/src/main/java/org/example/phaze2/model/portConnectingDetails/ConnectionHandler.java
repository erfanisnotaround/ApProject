package org.example.phaze2.model.portConnectingDetails;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.connectionsAndMaking.WireRendererManager;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;

import java.util.ArrayList;
import java.util.List;

public class ConnectionHandler implements CurveBuilder {
    private final ConnectionUI connectionUI;
    private final ConnectionChecker ruleEngine;
    private final WireManager wireManager;
    private final ConnectionRegistry registry;
    private final WireRendererManager wireRenderer;


    private Curve    currentCurve;
    private Node     startGate;
    private double   startX, startY;

    private Curve    selectedCurve;
    Point2D EndPoint;
    Point2D StartPoint;

    private static final int STEPS = 40;


    public ConnectionHandler(WireManager wireManager,
                              WireRendererManager wireRenderer
    ,ConnectionUI connectionUI) {

        this.wireManager = wireManager;
        this.ruleEngine = new ConnectionChecker(wireManager);
        this.registry = new ConnectionRegistry();
        this.wireRenderer = wireRenderer;
        this.connectionUI = connectionUI;
    }
    public void onPress(MouseEvent mouseEvent) {
        Node ExitNode = (Node) mouseEvent.getSource();
        if (registry.getExitGates().contains(ExitNode)) return;
        Point2D center = getCenterInScene(ExitNode);
        StartPoint = ExitNode.sceneToLocal(center);
        startX = StartPoint.getX();
        startY = StartPoint.getY();
        startGate = ExitNode;

        currentCurve = new Curve();
        currentCurve.setStrokeWidth(3);
        currentCurve.setStroke(Color.GREEN);
        wireRenderer.getLayerManager().addCurve(currentCurve);
        mouseEvent.consume();
    }

    public void onDrag(MouseEvent dragEvent) {
        if (currentCurve == null) return;

        EndPoint = wireRenderer.getLayerManager().getLayer().sceneToLocal(dragEvent.getSceneX(), dragEvent.getSceneY());
        build(StartPoint, EndPoint);

        double length = currentCurve.ApproximateLength();
        currentCurve.setStroke(wireManager.canUse(length) ? Color.GREEN : Color.RED);
        dragEvent.consume();
    }



    public void onRelease(MouseEvent e) {
        if (currentCurve == null) return;

        EndPoint = wireRenderer.getLayerManager().getLayer().sceneToLocal(e.getSceneX(), e.getSceneY());
        build(StartPoint , EndPoint);

        double finalLen = currentCurve.ApproximateLength();
        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());
        PortInfo fromInfo = registry.getPortMap().get(startGate);

        for (Node gate : registry.getEnterGates()) {
            Bounds eb = gate.getBoundsInLocal();
            Point2D cen = gate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (cen.distance(scenePt) < 5) {
                PortInfo toInfo = registry.getPortMap().get(gate);
                if (ruleEngine.isConnectionValid(fromInfo , toInfo , finalLen)) {
                    Curve myCurve = currentCurve;
                    Connection conn = new Connection(fromInfo, toInfo, myCurve, startGate, gate);
                    myCurve.setStroke(Color.GREEN);

                    addConnection(conn);

                    if (myCurve!=null){
                        myCurve.setStroke(Color.GREEN);
                        connectionUI.RegisterCurve(myCurve);
                    }
                    registry.removeGates(startGate , gate);

                    cleanup();
                } else {
                    wireRenderer.remove(currentCurve);
                }
                cleanup();
                e.consume();
                return;
            }
        }

        wireRenderer.remove(currentCurve);
        cleanup();
        e.consume();
    }
    private void cleanup() {
        currentCurve = null;
        startGate    = null;
    }

    private Point2D getCenterInScene(Node gate) {
        Bounds b = gate.getBoundsInLocal();
        return gate.localToScene(b.getWidth()/2, b.getHeight()/2);
    }
    public void addConnection(Connection connection) {
        registry.addConnection(connection);
        wireManager.addWire(connection.getCurve().ApproximateLength());
    }

    public void removeConnection(Connection conn) {
        registry.removeConnection(conn);
        wireManager.removeWire(conn.getCurve().ApproximateLength());
        wireRenderer.remove(conn.getCurve());
    }

    public void RegisterEnter(Node gate , SystemView system, int subIndex, PortTypes type) {
        PortInfo info = new PortInfo(system, subIndex, true, type);
        registry.registerEnter(gate , info);
    }
    public void RegisterExitGate(Node gate , SystemView system, int subIndex, PortTypes type) {
        PortInfo info = new PortInfo(system, subIndex, false, type);
        registry.registerExit(gate , info);
    }
    public void selectCurve(Curve c) {
        clearSelection();
        selectedCurve = c;
        c.setStroke(Color.BLUE);
        wireRenderer.getLayerManager().requestFocus();
    }
    private void clearSelection() {
        if (selectedCurve != null) {
            selectedCurve.setStroke(Color.BLACK);
            selectedCurve = null;
        }
    }


    @Override
    public void build(Point2D StartPoint, Point2D EndPoint) {
        double dx = EndPoint.getX() - StartPoint.getX();
        double dy = EndPoint.getY() - StartPoint.getY();

        List<Double> points = new ArrayList<>();
        for (int i = 0; i <= STEPS; i++) {
            double t = (double) i / STEPS;
            points.add(StartPoint.getX() + dx * t);
            points.add(StartPoint.getY() + dy * Math.pow(t, 3)); // one-bend cubic
        }

        currentCurve.getPoints().setAll(points);
    }
}
