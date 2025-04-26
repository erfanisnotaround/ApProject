package com.example.phaze1.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.*;

public class GateConnectorManager {
    private final Pane lineLayer;
    private final WireManager wires;
    private final Set<Node> exitGates  = new HashSet<>();
    private final Set<Node> enterGates = new HashSet<>();
    private Line  currentLine;
    private Node  startGate;
    private double pressLength;

    public GateConnectorManager(Pane lineLayer, WireManager wires) {
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
        Point2D sceneCenter = gate.localToScene(lb.getWidth()/2, lb.getHeight()/2);
        Point2D start = lineLayer.sceneToLocal(sceneCenter);
        currentLine = new Line(start.getX(), start.getY(), start.getX(), start.getY());
        currentLine.setStrokeWidth(5);
        lineLayer.getChildren().add(currentLine);

        pressLength = computeLength(currentLine);
        startGate   = gate;
        e.consume();
    }

    private void onDrag(MouseEvent e) {
        if (currentLine == null) return;
        Point2D p = lineLayer.sceneToLocal(e.getSceneX(), e.getSceneY());
        currentLine.setEndX(p.getX());
        currentLine.setEndY(p.getY());

        double length = computeLength(currentLine);
        if (!wires.canUse(length)) {
            currentLine.setStroke(Color.RED);
        } else {
            currentLine.setStroke(Color.GREEN);
        }
        e.consume();
    }

    private void onRelease(MouseEvent e) {
        if (currentLine == null) return;
        double finalLen = computeLength(currentLine);
        Point2D scenePt = new Point2D(e.getSceneX(), e.getSceneY());

        for (Node enterGate : enterGates) {
            Bounds eb = enterGate.getBoundsInLocal();
            Point2D enterCenter = enterGate.localToScene(eb.getWidth()/2, eb.getHeight()/2);
            if (enterCenter.distance(scenePt) < 5 ) {
                GateType startType = (GateType)startGate.getUserData();
                GateType endType = (GateType)enterGate.getUserData();
                if (startType != endType){
                    lineLayer.getChildren().remove(currentLine);
                    cleanup();
                    e.consume();
                    return;
                }
                if (wires.canUse(finalLen)) {
                    wires.addWire(finalLen);
                    currentLine.setStroke(Color.GREEN);
                    enterGates.remove(enterGate);
                    exitGates.remove(startGate);
                } else {
                    lineLayer.getChildren().remove(currentLine);
                }
                cleanup();
                e.consume();
                return;
            }
        }

        lineLayer.getChildren().remove(currentLine);
        cleanup();
        e.consume();
    }

    private double computeLength(Line L) {
        double dx = L.getEndX() - L.getStartX();
        double dy = L.getEndY() - L.getStartY();
        return Math.hypot(dx, dy);
    }

    private void cleanup() {
        currentLine = null;
        startGate   = null;
    }


    public List<Line> getConnections() {
        List<Line> result = new ArrayList<>();
        for (Node n : lineLayer.getChildren()) {
            if (n instanceof Line) result.add((Line)n);
        }
        return Collections.unmodifiableList(result);
    }
}