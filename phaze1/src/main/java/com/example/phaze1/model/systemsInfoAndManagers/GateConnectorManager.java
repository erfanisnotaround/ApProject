package com.example.phaze1.model.systemsInfoAndManagers;

import com.example.phaze1.model.agents.mediaAgent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.util.*;

public class GateConnectorManager {

    private final Pane lineLayer;
    private final WireManager wires;
    private MediaView mediaView = new MediaView();
    private mediaAgent audioAgent =  new mediaAgent("src/main/resources/com/example/phaze1/music/game-bonus-2-294436.mp3",mediaView);
    private final Map<Node,GatePortInfo> portInfo      = new HashMap<>();
    private final Set<Node>              exitGates     = new HashSet<>();
    private final Set<Node>              enterGates    = new HashSet<>();
    private final Map<GatePortInfo,Connection> exitConnections = new HashMap<>();
    private final List<Connection>       connections   = new ArrayList<>();

    private Curve    currentCurve;
    private Node     startGate;
    private double   startX, startY;

    private Curve    selectedCurve;

    private static final int STEPS = 40;

    public GateConnectorManager(Pane lineLayer, WireManager wires) {
        this.lineLayer = lineLayer;
        this.wires     = wires;

        lineLayer.setFocusTraversable(true);
        lineLayer.getChildren().add(mediaView);
        lineLayer.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getTarget() == lineLayer) {
                clearSelection();
                lineLayer.requestFocus();
            }
        });

        lineLayer.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE && selectedCurve != null) {
                deleteConnection(selectedCurve.connection);
                selectedCurve = null;
                e.consume();
            }
        });
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
        audioAgent.Stop();
        audioAgent.getMediaPlayer().seek(Duration.ZERO);
        audioAgent.setAudioVolume();
        audioAgent.Play();
        Bounds b = gate.getBoundsInLocal();
        Point2D c = gate.localToScene(b.getWidth()/2, b.getHeight()/2);
        Point2D s = lineLayer.sceneToLocal(c);

        startX = s.getX();
        startY = s.getY();
        startGate = gate;

        currentCurve = new Curve();
        currentCurve.setStrokeWidth(3);
        currentCurve.setStroke(Color.BLACK);
        lineLayer.getChildren().add(0, currentCurve);
        e.consume();
    }

    private void onDrag(MouseEvent e) {
        if (currentCurve == null) return;

        Point2D p = lineLayer.sceneToLocal(e.getSceneX(), e.getSceneY());
        double dx = p.getX() - startX,
                dy = p.getY() - startY;

        List<Double> pts = new ArrayList<>((STEPS+1)*2);
        for (int i=0; i<=STEPS; i++) {
            double t = (double)i/STEPS;
            pts.add(startX + dx*t);
            pts.add(startY + dy*(t*t*t));
        }
        currentCurve.getPoints().setAll(pts);

        double len = currentCurve.ApproximateLength();
        currentCurve.setStroke(wires.canUse(len) ? Color.GREEN : Color.RED);
        e.consume();
    }

    private void onRelease(MouseEvent e) {
        if (currentCurve == null) return;

        Point2D p = lineLayer.sceneToLocal(e.getSceneX(), e.getSceneY());
        double dx = p.getX() - startX,
                dy = p.getY() - startY;

        List<Double> pts = new ArrayList<>((STEPS+1)*2);
        for (int i=0; i<=STEPS; i++) {
            double t = (double)i/STEPS;
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
            if (cen.distance(scenePt) < 5) {
                GatePortInfo toInfo = portInfo.get(gate);
                if (fromInfo.type == toInfo.type && wires.canUse(finalLen)) {
                    Curve myCurve = currentCurve;
                    Connection conn = new Connection(
                            fromInfo, toInfo,
                            myCurve,
                            startGate, gate
                    );
                    wires.addWire(finalLen);
                    myCurve.setStroke(Color.GREEN);

                    exitConnections.put(fromInfo, conn);
                    exitConnections.put(toInfo, conn);
                    connections.add(conn);

                    myCurve.setOnMouseClicked(evt -> {
                        selectCurve(myCurve);
                        evt.consume();
                    });
                    if (myCurve!=null)myCurve.setStroke(Color.GREEN);
                    exitGates.remove(startGate);
                    enterGates.remove(gate);

                    cleanup();
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

    private void cleanup() {
        currentCurve = null;
        startGate    = null;
    }

    private void selectCurve(Curve c) {
        clearSelection();
        selectedCurve = c;
        c.setStroke(Color.BLUE);
        lineLayer.requestFocus();
    }

    private void clearSelection() {
        if (selectedCurve != null) {
            selectedCurve.setStroke(Color.BLACK);
            selectedCurve = null;
        }
    }

    private void deleteConnection(Connection conn) {
        lineLayer.getChildren().remove(conn.curve);
        connections.remove(conn);
        exitConnections.remove(conn.from);
        exitGates.add(conn.getFromNode());
        enterGates.add(conn.getToNode());

        wires.removeWire(conn.curve.ApproximateLength());
    }

    public Map<GatePortInfo,Connection> getExitConnections() {
        return exitConnections;
    }
    public Map<Node,GatePortInfo> GateInfo(){
        return portInfo;
    }
    public List<Connection> getConnections() {
        return Collections.unmodifiableList(connections);
    }
    public List<Polyline> getConnectionShapes() {
        List<Polyline> list = new ArrayList<>();
        for (Node n: lineLayer.getChildren())
            if (n instanceof Polyline)
                list.add((Polyline)n);
        return Collections.unmodifiableList(list);
    }
}
