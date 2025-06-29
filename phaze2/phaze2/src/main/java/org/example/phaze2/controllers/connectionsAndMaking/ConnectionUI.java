package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.PortInfo;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.ConnectionChecker;
import org.example.phaze2.model.portConnectingDetails.ConnectionHandler;

import javax.swing.plaf.ColorUIResource;

public class ConnectionUI {
    Pane Container;
    WireManager wireManager;
    CurveLayerManager curveLayerManager;
    ConnectionHandler connectionHandler;

    public ConnectionUI(Pane Container, WireManager wireManager) {
        this.Container = Container;
        this.wireManager = wireManager;
        curveLayerManager = new CurveLayerManager(Container);
        connectionHandler = new ConnectionHandler(wireManager , new WireRendererManager(curveLayerManager) , this);
    }
    public void registerExitGate(Node gate, SystemView system, int subIndex, PortTypes type) {
        connectionHandler.RegisterExitGate(gate, system, subIndex, type);
        gate.addEventHandler(MouseEvent.MOUSE_PRESSED,  mouseEvent -> connectionHandler.onPress(mouseEvent));
        gate.addEventHandler(MouseEvent.MOUSE_DRAGGED,  mouseEvent -> connectionHandler.onDrag(mouseEvent));
        gate.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> connectionHandler.onRelease(mouseEvent));
    }

    public void registerEnterGate(Node gate, SystemView system, int subIndex, PortTypes type) {
        connectionHandler.RegisterEnter(gate, system, subIndex, type);
    }
    public void RegisterCurve(Curve curve){
        curve.setOnMouseClicked(evt -> {
            connectionHandler.selectCurve(curve);
            evt.consume();
        });
    }
    public void resetSelection(){
        connectionHandler.resetSelection();
    }
}
