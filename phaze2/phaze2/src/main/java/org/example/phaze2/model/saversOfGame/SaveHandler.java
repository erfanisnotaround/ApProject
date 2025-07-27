package org.example.phaze2.model.saversOfGame;

import javafx.geometry.Point2D;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.agentsAndManagers.JsonManager;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.necessary.Anchor;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.PortInfo;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelSavesAndTheirPojo.*;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.List;

public class SaveHandler {

    private int CurrentLevel;
    private JsonManager jsonManager;
    public SaveHandler(int currentLevel) {
        this.CurrentLevel = currentLevel;
        jsonManager = new JsonManager("org/example/phaze2/jsonFiles/levelSaves.json");
    }
    public void StartSave() {
        SystemSavings();
        PocketSave();
        ConnectionSave();

    }
    private void SystemSavings(){

        List<SystemView> systemViews = Constants.getInstance().getSystemViews();
        List<SystemsPojo> systemsPojoLists = new ArrayList<>();

        List<SystemView> systemViewCopy = new ArrayList<>(systemViews);
        for(SystemView systemView : systemViewCopy){
            synchronized (systemView){
                SystemsPojo systemsPojo = getSystemPojo(systemView);
                systemsPojoLists.add(systemsPojo);
            }

        }
    }
    private SystemsPojo getSystemPojo(SystemView systemView){

        SystemsPojo systemsPojo = new SystemsPojo();

        systemsPojo.setSystemID(systemView.getSystemID());
        systemsPojo.setNumberOfSubSystems(systemView.getNumberOfSubSystems());
        systemsPojo.setSystemType(systemView.getSystemType());
        systemsPojo.setSystemHeight(systemView.getSystemHeight());
        systemsPojo.setSystemWidth(systemView.getSystemWidth());
        systemsPojo.setX(systemView.getLayoutX());
        systemsPojo.setY(systemView.getLayoutY());
        systemsPojo.setItDown(systemView.isItDown());
        systemsPojo.setLightBoolean(systemView.isLightBoolean());

        CapacitySet(systemsPojo , systemView);
        return systemsPojo;

    }
    private void CapacitySet(SystemsPojo systemsPojo , SystemView systemView){

        systemsPojo.setCapacity(new String[systemView.getCapacity().length]);
        for (int i = 0 ; i < systemView.getCapacity().length; i++){
            if (systemView.getCapacity()[i] != null){
                systemsPojo.getCapacity()[i] = systemView.getCapacity()[i].getPocketId();
                continue;
            }
            systemsPojo.getCapacity()[i] = null;
        }
    }

    private void PocketSave(){

        List<PocketMain> pocketMains = Constants.getInstance().getPockets();
        List<PocketPojo> pocketPojoList = new ArrayList<>();

        List<PocketMain> pocketMainCopy = new ArrayList<>(pocketMains);
        for (PocketMain pocketMain : pocketMainCopy){
            synchronized (pocketMain){
                PocketPojo pocketPojo = PocketLoad(pocketMain);
                pocketPojoList.add(pocketPojo);
            }

        }
    }
    private PocketPojo PocketLoad(PocketMain pocketMain){

        PocketPojo pocketPojo = new PocketPojo();
        pocketPojo.setPocketID(pocketMain.getPocketId());
        pocketPojo.setItAffected(pocketMain.isItAffected());
        pocketPojo.setHP(pocketMain.getHP());
        pocketPojo.setItCollided(pocketMain.isIsItCollided());
        pocketPojo.setItMoved(pocketMain.isIsItMoved());
        pocketPojo.setType(pocketMain.getType());
        pocketPojo.setDelay(pocketMain.getDelay());
        pocketPojo.setFirstPocketType(pocketMain.getFirstPocketType());
        pocketPojo.setTypeBeforeChange(pocketMain.getTypeBeforeChange());
        pocketPojo.setPlaceOfX(pocketMain.getPlaceOfX());
        pocketPojo.setPlaceOfY(pocketMain.getPlaceOfY());

        pocketPojo.setWhichSystemViewThisPocketIsAffectedBy(pocketMain.getWhichSystemViewThisPocketIsAffectedBy().getSystemID());
        pocketPojo.setAvailableTime(pocketMain.getAvailableTime());

        return pocketPojo;
    }


    private void ConnectionSave(){
        List<ConnectionPojo> connectionPojos = new ArrayList<>();
        List<Connection> connections = Constants.getInstance().getConnections();


        List<Connection> connectionsCopy = new ArrayList<>(connections);
        for (Connection connection : connectionsCopy){

            synchronized (connection){
                ConnectionPojo connectionPojo = ConnectionLoad(connection);
                connectionPojos.add(connectionPojo);
            }

        }
    }
    private ConnectionPojo ConnectionLoad(Connection connection){

        ConnectionPojo connectionPojo = new ConnectionPojo();

        connectionPojo.setFromPort(SetPortPojo(connection.getFromPort().getPortInfo()));
        connectionPojo.setToPort(SetPortPojo(connection.getToPort().getPortInfo()));

        SetCurvePojo(connection.getCurve());



        return connectionPojo;
    }
    private PortPojo SetPortPojo(PortInfo portInfo){


        PortPojo portPojo = new PortPojo();
        portPojo.setExit(portInfo.isExit());
        portPojo.setType(portInfo.getType());
        portPojo.setSubIndex(portInfo.getSubIndex());
        portPojo.setSystemID(portInfo.getSystem().getSystemID());
        portPojo.setBeforeChange(portInfo.getBeforeChange());

        return portPojo;
    }
    private CurvePojo SetCurvePojo(Curve curve){

        CurvePojo curvePojo = new CurvePojo();
        curvePojo.setHP(curve.getHP());
        curvePojo.setItUsed(curve.isIsItUsed());
        curvePojo.setPocketMovingOnIt(curve.getPocketMovingOnIt().getPocketId());
        curvePojo.setLatestAcceptableLength(curve.getLatestAcceptableLength());


        setMiddlePointsOfCurvePojo(curvePojo, curve);



        return curvePojo;
    }
    private void setMiddlePointsOfCurvePojo(CurvePojo curvePojo , Curve curve){

        List<AnchorPojo> anchorPojos = new ArrayList<>();
        for (Anchor anchor : curve.getAnchors()){
            AnchorPojo anchorPojo = new AnchorPojo();
            anchorPojo.setCenterX(anchor.getCenter().getX());
            anchorPojo.setCenterY(anchor.getCenter().getY());
            anchorPojo.setLatestCordX(anchor.getLatestCord().getX());
            anchorPojo.setLatestCordY(anchor.getLatestCord().getY());

            anchorPojos.add(anchorPojo);
        }

        curvePojo.setMiddlePoints(anchorPojos);
    }
    private PathMoverPojo SetPathMoverPojo(PocketPojo pocketPojo , PathMover mover){
        PathMoverPojo pojo = new PathMoverPojo();
        pojo.setMultiplier(mover.getMultiplier());
        pojo.setS(mover.getS());
        pojo.setV(mover.getSpeed());
        pojo.setA(mover.GetAcceleration());
        pojo.setAngleNeeded(mover.getAngleNeeded());
        pojo.setRotate(mover.GetRotate());
        pojo.setSTEPS(mover.GetSteps());

        Point2D ltd = mover.getLatestLineDistance();
        Point2D ctd = mover.getCurrentLineDistance();
        Point2D ltw = mover.getLatestLineDistanceForWholeMove();
        Point2D ctw = mover.getCurrentLineDistanceForWholeMove();

        pojo.setLatestLineDistanceX(ltd.getX());
        pojo.setLatestLineDistanceY(ltd.getY());
        pojo.setCurrentLineDistanceX(ctd.getX());
        pojo.setCurrentLineDistanceY(ctd.getY());

        pojo.setLatestLineDistanceForWholeMoveX(ltw.getX());
        pojo.setLatestLineDistanceForWholeMoveY(ltw.getY());
        pojo.setCurrentLineDistanceForWholeMoveX(ctw.getX());
        pojo.setCurrentLineDistanceForWholeMoveY(ctw.getY());

        pojo.setLineDistancePerMoveX(mover.getLineDistancePerMoveX());
        pojo.setLineDistancePerMoveY(mover.getLineDistancePerMoveY());
        pojo.setLineDistancePerMoveXForWhole(mover.getLineDistancePerMoveXForWhole());
        pojo.setLineDistancePerMoveYForWhole(mover.getLineDistancePerMoveYForWhole());



        return pojo;

    }
}
