package com.example.phaze1.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.HashSet;
import java.util.Set;

public class GateConnectorManager {
    private final Pane lineLayer;
    private final Set<Node> exitGates  = new HashSet<>();
    private final Set<Node> enterGates = new HashSet<>();
    private Line  currentLine;
    private Node  startGate;

    public GateConnectorManager(Pane lineLayer) {
        this.lineLayer = lineLayer;
    }
    public void registerExitGate(Node gate) {
        exitGates.add(gate);
        gate.addEventHandler(MouseEvent.MOUSE_PRESSED,   this::onPress);
        gate.addEventHandler(MouseEvent.MOUSE_DRAGGED,   this::onDrag);
        gate.addEventHandler(MouseEvent.MOUSE_RELEASED,  this::onRelease);
    }

    public void registerEnterGate(Node gate) {
        enterGates.add(gate);
    }

    private void onPress(MouseEvent e) {
        Node gate = (Node)e.getSource();
        if (!exitGates.contains(gate)) return;

        Bounds lb = gate.getBoundsInLocal();
        Point2D sceneCenter = gate.localToScene(lb.getWidth()/2, lb.getHeight()/2);
        Point2D start = lineLayer.sceneToLocal(sceneCenter);

        currentLine = new Line(start.getX(), start.getY(), start.getX(), start.getY());
        currentLine.setStrokeWidth(2);
        lineLayer.getChildren().add(currentLine);

        startGate = gate;
        e.consume();
    }

    private void onDrag(MouseEvent e) {
        if (currentLine == null) return;
        Point2D p = lineLayer.sceneToLocal(e.getSceneX(), e.getSceneY());
        currentLine.setEndX(p.getX());
        currentLine.setEndY(p.getY());
        e.consume();
    }

    private void onRelease(MouseEvent e) {
        if (currentLine == null) return;

        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());
        for (Node enterGate : enterGates) {
            Bounds eb = enterGate.getBoundsInLocal();
            Point2D enterCenter = enterGate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (enterCenter.distance(scenePt) < 20) {
                Point2D end = lineLayer.sceneToLocal(enterCenter);
                currentLine.setEndX(end.getX());
                currentLine.setEndY(end.getY());
                cleanup();
                e.consume();
                return;
            }
        }

        lineLayer.getChildren().remove(currentLine);
        cleanup();
        e.consume();
    }

    private void cleanup() {
        currentLine = null;
        startGate   = null;
    }
}
