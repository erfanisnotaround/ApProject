package org.example.phaze2.model.saversOfGame;

import javafx.geometry.Point2D;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.necessary.*;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelSavesAndTheirPojo.*;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;
import java.util.Map;

public class LoadHandler {
    Constants constants = Constants.getInstance();
    List<PocketMain> pocketMains = constants.getPockets();
    List<SystemView> systemViews = constants.getSystemViews();
    List<Connection> connections = constants.getConnections();

    Map<String , PocketMain> pocketMainMap = constants.getPocketMainMap();
    Map<String , SystemView> systemViewMap = constants.getSystemViewMap();

    private ConnectionUI connectionUI;

    public LoadHandler(ConnectionUI connectionUI) {
        this.connectionUI = connectionUI;
    }


    public void StartSettingInfoOfLevel(LevelPojo InfoOfLevel){
        List<PocketPojo> pocketPojo = InfoOfLevel.getPockets();
        List<SystemsPojo> systemsPojo = InfoOfLevel.getSystems();
        List<ConnectionPojo> connectionPojo = InfoOfLevel.getConnections();


        SetPockets(pocketPojo);
        SetSystemViews(systemsPojo);
        SetConnections(connectionPojo);
    }
    private void SetPockets(List<PocketPojo> pocketPojoList) {


        for (PocketPojo pocketPojo : pocketPojoList) {
            SetAPocket(pocketPojo);
        }
    }
    private void SetAPocket(PocketPojo pocketPojo){
        PocketMain targetPocket = pocketMainMap.get(pocketPojo.getPocketID());


        targetPocket.setAvailableTime(pocketPojo.getAvailableTime());
        targetPocket.setItAffected(pocketPojo.isItAffected());
        targetPocket.setHP(pocketPojo.getHP());
        targetPocket.setIsItCollided(pocketPojo.isItCollided());
        targetPocket.setIsItMoved(pocketPojo.isItMoved());

        targetPocket.setDelay(pocketPojo.getDelay());
        targetPocket.setFirstPocketType(pocketPojo.getFirstPocketType());
        targetPocket.setTypeBeforeChange(pocketPojo.getTypeBeforeChange());

        if (pocketPojo.getWhichSystemViewThisPocketIsAffectedBy() != null) {
            targetPocket.setWhichSystemViewThisPocketIsAffectedBy(systemViewMap.get(pocketPojo.getWhichSystemViewThisPocketIsAffectedBy()));
        }
        else targetPocket.setWhichSystemViewThisPocketIsAffectedBy(null);

        targetPocket.setPlaceOfX(pocketPojo.getPlaceOfX());
        targetPocket.setPlaceOfY(pocketPojo.getPlaceOfY());
        targetPocket.setLayoutX(pocketPojo.getXX());
        targetPocket.setLayoutY(pocketPojo.getYY());

        SetPathMover(targetPocket , pocketPojo);
    }

    private void SetPathMover(PocketMain targetPocket , PocketPojo pocketPojo){
        PathMoverPojo pathMoverPojo = pocketPojo.getPathMover();

        PathMover pathMover = targetPocket.getPathMover();

        pathMover.setMultiplier(pathMoverPojo.getMultiplier());
        pathMover.setSpeed(pathMoverPojo.getV());
        pathMover.setA(pathMoverPojo.getA());
        pathMover.SetS(pathMoverPojo.getS());

        pathMover.setLatestLineDistance(new Point2D(pathMoverPojo.getLatestLineDistanceX() , pathMoverPojo.getLatestLineDistanceY()));
        pathMover.setCurrentLineDistance(new Point2D(pathMoverPojo.getCurrentLineDistanceX() , pathMoverPojo.getCurrentLineDistanceY()));
        pathMover.setLatestLineDistanceForWholeMove(new Point2D(pathMoverPojo.getLatestLineDistanceForWholeMoveX() , pathMoverPojo.getLatestLineDistanceForWholeMoveY()));
        pathMover.setCurrentLineDistanceForWholeMove(new Point2D(pathMoverPojo.getCurrentLineDistanceForWholeMoveX() , pathMoverPojo.getCurrentLineDistanceForWholeMoveY()));

        pathMover.setLineDistancePerMove(pathMoverPojo.getLineDistancePerMoveX() , pathMoverPojo.getLineDistancePerMoveY());
        pathMover.setLineDistancePerMoveForWhole(pathMoverPojo.getLineDistancePerMoveXForWhole() , pathMoverPojo.getLineDistancePerMoveYForWhole());

        targetPocket.setPathMover(pathMover);
    }



    private void SetSystemViews(List<SystemsPojo> systemsPojoList) {

        for (SystemsPojo systemsPojo : systemsPojoList) {
            SetASystemView(systemsPojo);
        }
    }
    private void SetASystemView(SystemsPojo systemsPojo) {

        SystemView systemView = systemViewMap.get(systemsPojo.getSystemID());

        systemView.setLayoutX(systemsPojo.getX());
        systemView.setLayoutY(systemsPojo.getY());
        systemView.setIsItDown(systemsPojo.isItDown());
        systemView.setLightBoolean(systemsPojo.isLightBoolean());

        CapacitySet(systemView , systemsPojo);

    }

    private void CapacitySet(SystemView systemView , SystemsPojo systemsPojo) {

        for (int i = 0 ; i < systemsPojo.getCapacity().length ; i++){
            String nameOfPocket = systemsPojo.getCapacity()[i];
            if (nameOfPocket == null){
                systemView.getCapacity()[i] = null;
                continue;
            }
            PocketMain pocketMain = pocketMainMap.get(nameOfPocket);
            systemView.getCapacity()[i] = pocketMain;

        }
    }

    private void SetConnections(List<ConnectionPojo> connectionPojoList) {
        for (ConnectionPojo connectionPojo : connectionPojoList) {
            SetAConnection(connectionPojo);
        }
    }
    private void SetAConnection(ConnectionPojo connectionPojo) {
        SystemView fromSystemView = systemViewMap.get(connectionPojo.getFromPort().getSystemID());
        int fromSubIndex = connectionPojo.getFromPort().getSubIndex();
        SystemView toSystemView = systemViewMap.get(connectionPojo.getToPort().getSystemID());
        int toSubIndex = connectionPojo.getToPort().getSubIndex();

        Port fromPort = fromSystemView.getSubSystems().get(fromSubIndex).getExitPort();
        Port toPort = toSystemView.getSubSystems().get(toSubIndex).getEnterPort();

        Curve curve  = getCurve(connectionPojo.getCurve());

        Connection connection = new Connection(curve, fromPort, toPort);

        curve.setConnection(connection);
        connectionUI.RegisterConnection(connection);
    }
    private Curve getCurve(CurvePojo curvePojo) {
        Curve curve = new Curve();

        curve.setHP(curvePojo.getHP());
        curve.setLatestAcceptableLength(curvePojo.getLatestAcceptableLength());
        curve.setIsItUsed(curvePojo.isItUsed());

        String pocketMovingOnItName = curvePojo.getPocketMovingOnIt();
        if (pocketMovingOnItName != null) {
            curve.setPocketMovingOnIt(pocketMainMap.get(pocketMovingOnItName));
        }
        else curve.setPocketMovingOnIt(null);

        for (AnchorPojo anchorPojo : curvePojo.getMiddlePoints()) {
            setMiddlePoints(curve , anchorPojo);
        }

        return curve;

    }
    private void setMiddlePoints(Curve curve , AnchorPojo anchorPojo) {
        Anchor anchor = new Anchor(new Point2D(anchorPojo.getCenterX() , anchorPojo.getCenterY()));
        anchor.setLatestCord(new Point2D(anchorPojo.getLatestCordX() , anchorPojo.getLatestCordY()));
        curve.getAnchors().add(anchor);
    }


}
