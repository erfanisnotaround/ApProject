package org.example.phaze2.model.portConnectingDetails;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.connectionsAndMaking.WireRendererManager;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Anchor;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.PortInfo;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.necessary.Port;

public class ConnectionHandler {
    private final Pane Container;
    private final ConnectionUI connectionUI;
    private final ConnectionChecker ruleEngine;
    private final WireManager wireManager;
    private final ConnectionRegistry registry;
    private final WireRendererManager wireRenderer;


    private Curve    currentCurve;
    private Port     startGate;
    private double   startX, startY;

    private Curve    selectedCurve;
    Point2D EndPoint;
    Point2D StartPoint;

    private static final int STEPS = 40;


    public ConnectionHandler(WireManager wireManager,
                              WireRendererManager wireRenderer
    ,ConnectionUI connectionUI , Pane container) {

        this.wireManager = wireManager;
        this.ruleEngine = new ConnectionChecker(wireManager);
        this.registry = new ConnectionRegistry();
        this.wireRenderer = wireRenderer;
        this.connectionUI = connectionUI;
        this.Container = container;
    }
    public void onPress(MouseEvent mouseEvent) {
        Port ExitNode = (Port) mouseEvent.getSource();
        if (!registry.getExitGates().contains(ExitNode)) return;
        StartPoint = getCenterNode(ExitNode);
        startX = StartPoint.getX();
        startY = StartPoint.getY();
        startGate = ExitNode;

        currentCurve = new Curve();
        currentCurve.setFill(Color.GREEN);
        wireRenderer.renderCurve(currentCurve);
        mouseEvent.consume();
    }

    public void onDrag(MouseEvent dragEvent) {
        if (currentCurve == null) return;

        EndPoint = wireRenderer.getLayerManager().getLayer().sceneToLocal(dragEvent.getSceneX(), dragEvent.getSceneY());
        currentCurve.build(StartPoint, EndPoint);

        double length = currentCurve.ApproximateLength();
        currentCurve.setFill(wireManager.canUse(length) ? Color.GREEN : Color.RED);
        dragEvent.consume();
    }



    public void onRelease(MouseEvent e) {
        if (currentCurve == null) return;

        EndPoint = wireRenderer.getLayerManager().getLayer().sceneToLocal(e.getSceneX(), e.getSceneY());
        currentCurve.build(StartPoint , EndPoint);

        double finalLen = currentCurve.ApproximateLength();
        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());
        PortInfo fromInfo = startGate.getPortInfo();

        for (Port gate : registry.getEnterGates()) {
            Bounds eb = gate.getBoundsInLocal();
            Point2D cen = gate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (cen.distance(scenePt) < 5) {
                PortInfo toInfo = gate.getPortInfo();
                if (ruleEngine.isConnectionValid(fromInfo , toInfo , finalLen)) {
                    EndPoint = getCenterNode(gate);
                    currentCurve.build(StartPoint , EndPoint);
                    Curve myCurve = currentCurve;
                    Connection conn = new Connection(myCurve , startGate, gate);
                    myCurve.setConnection(conn);
                    myCurve.setFill(Color.GREEN);
                    addConnection(conn);


                    if (myCurve!=null){
                        myCurve.setFill(Color.GREEN);
                        connectionUI.RegisterCurve(myCurve);
                    }
                    registry.removeGates(startGate , gate);

                    cleanup();
                } else {
                    wireRenderer.removeNode(currentCurve);
                }
                cleanup();
                e.consume();
                return;
            }
        }

        wireRenderer.removeNode(currentCurve);
        cleanup();
        e.consume();
    }

    public void addAnchor(Curve curve , Anchor anchor) {
        curve.AddAnchor(anchor);
        curve.build(curve.getFirstPoint(), curve.getLastPoint());
        if (!wireManager.canUse(curve.ApproximateLength())) {
            curve.setFill(Color.RED);

        }

    }
    public void DraggingAnchor(Curve curve , Anchor anchor) {

        curve.build(curve.getFirstPoint() , curve.getLastPoint());

        curve.setFill(wireManager.canUse(curve.ApproximateLength()) ? Color.GREEN : Color.RED);


    }
    public void onAnchorReleased(Anchor anchor , Curve curve) {
        if (!wireManager.canUse(curve.ApproximateLength())) {
            anchor.setCenter(anchor.getLatestCord());
            curve.build(curve.getFirstPoint() , curve.getLastPoint());

        }
        else {
            wireManager.removeWire(curve.getLatestAcceptableLength());
            wireManager.addWire(curve.ApproximateLength());
            anchor.commit();
            curve.setLatestAcceptableLength(curve.ApproximateLength());
        }
        curve.setFill(Color.GREEN);

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
        wireRenderer.removeNode(conn.getCurve());
        Container.getChildren().remove(conn.getCurve());
    }

    public void RegisterEnter(Port gate , SystemView system, int subIndex, PortTypes type) {
        PortInfo info = new PortInfo(system, subIndex, true, type);
        registry.registerEnter(gate , info);
    }
    public void RegisterExitGate(Port gate , SystemView system, int subIndex, PortTypes type) {
        PortInfo info = new PortInfo(system, subIndex, false, type);
        registry.registerExit(gate , info);
    }
    public void selectCurve(Curve c) {
        clearSelection();
        selectedCurve = c;
        c.setFill(Color.BLUE);

        wireRenderer.getLayerManager().requestFocus();
    }
    private void clearSelection() {
        if (selectedCurve != null) {
            selectedCurve.setFill(Color.GREEN);
            selectedCurve = null;
        }
    }
    public void resetSelection() {
        if (selectedCurve == null) return;
        selectedCurve.setFill(Color.GREEN);
    }

    public Point2D getCenterNode(Node node){
        Point2D center = getCenterInScene(node);
        return wireRenderer.getLayerManager().getLayer().sceneToLocal(center);
    }

    public void RegisterACurve(Connection connection) {
        Point2D TempStarter = getCenterNode(connection.getFromPort().getShape());
        Point2D TempEnder = getCenterNode(connection.getToPort().getShape());

        connection.getCurve().build(TempStarter, TempEnder);
        double length = connection.getCurve().ApproximateLength();
        if (wireManager.canUse(length)) {
            registry.addConnection(connection);
            wireRenderer.renderCurve(connection.getCurve());
            wireManager.addWire(length);
        }


    }



}
