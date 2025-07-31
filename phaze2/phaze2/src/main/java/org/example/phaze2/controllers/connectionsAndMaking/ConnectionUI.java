package org.example.phaze2.controllers.connectionsAndMaking;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.example.phaze2.model.WireManager;
import org.example.phaze2.model.constants.PortTypes;
import org.example.phaze2.model.levelDetails.necessary.Anchor;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.example.phaze2.model.portConnectingDetails.ConnectionHandler;

public class ConnectionUI {
    Pane Container;
    WireManager wireManager;
    CurveLayerManager curveLayerManager;
    ConnectionHandler connectionHandler;

    public ConnectionUI(Pane Container, WireManager wireManager) {
        this.Container = Container;
        this.wireManager = wireManager;
        curveLayerManager = new CurveLayerManager(Container);
        connectionHandler = new ConnectionHandler(wireManager , new WireRendererManager(curveLayerManager) , this , Container);
    }
    public void registerExitGate(Port gate, SystemView system, int subIndex, PortTypes type) {
        connectionHandler.RegisterExitGate(gate, system, subIndex, type);
        gate.addEventHandler(MouseEvent.MOUSE_PRESSED,  mouseEvent -> connectionHandler.onPress(mouseEvent));
        gate.addEventHandler(MouseEvent.MOUSE_DRAGGED,  mouseEvent -> connectionHandler.onDrag(mouseEvent));
        gate.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> connectionHandler.onRelease(mouseEvent));
    }

    public void registerEnterGate(Port gate, SystemView system, int subIndex, PortTypes type) {
        connectionHandler.RegisterEnter(gate, system, subIndex, type);
    }
    public void RegisterCurve(Curve curve){
        curve.setOnMouseClicked(evt -> {

            if (evt.getClickCount() == 2) {
                Anchor anchor = new Anchor(new Point2D(evt.getSceneX() , evt.getSceneY()));
                anchor.setFill(Color.GREEN);
                connectionHandler.addAnchor(curve, anchor);
                curve.setLatestAcceptableLength(curve.ApproximateLength());
                Container.getChildren().addLast(anchor);
                connectionHandler.selectCurve(curve);
                connectionHandler.DraggingAnchor(curve, anchor);
                anchor.commit();
                RegisterAnchor(anchor, curve);


                evt.consume();
            }
            else if (evt.getClickCount() == 1) {
                connectionHandler.selectCurve(curve);
            }
            evt.consume();
            RegisterCurve(curve);
        });
    }
    public void RegisterAnchor(Anchor anchor , Curve curve){
        anchor.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                curveLayerManager.removeNode(anchor);
                curve.RemoveAnchor(anchor);
                curve.build(curve.getFirstPoint() , curve.getLastPoint());
            }
        });
        anchor.setOnMouseDragged(mouseEvent -> {
//            curve.RemoveAnchor(anchor);
            anchor.setCenter(new Point2D(mouseEvent.getSceneX() , mouseEvent.getSceneY()));
//            curve.AddAnchor(anchor);
            connectionHandler.DraggingAnchor(curve, anchor);
        });
        anchor.setOnMouseReleased(mouseEvent -> {
            connectionHandler.onAnchorReleased(anchor, curve);
        });
    }
    public void RegisterConnection(Connection connection){
        connectionHandler.RegisterACurve(connection);
    }
    public void resetSelection(){
        connectionHandler.resetSelection();
    }
}
