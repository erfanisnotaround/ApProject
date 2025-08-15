package org.example.phaze2.model.saversOfGame;

import javafx.geometry.Point2D;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAbilityController;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAdder;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;
import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.model.agentsAndManagers.JsonManager;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.hudModels.AbilityAliveManager;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.levelDetails.necessary.Anchor;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.PortInfo;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelSavesAndTheirPojo.*;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.*;

public class SaveHandler {

    private List<SystemsPojo> systemsPojoLists = new ArrayList<>();
    private List<PocketPojo> pocketPojoLists = new ArrayList<>();
    private List<ConnectionPojo> connectionPojoLists = new ArrayList<>();

    private List<SystemView> systemViewCopy;
    private List<PocketMain> pocketMainCopy;
    private List<Connection> connectionsCopy;
    LoadAndSaveCompleterNecessaries loadAndSaveCompleterNecessaries;
    public SaveHandler(LoadAndSaveCompleterNecessaries loadAndSaveCompleterNecessaries) {
        this.loadAndSaveCompleterNecessaries = loadAndSaveCompleterNecessaries;
    }
    public LevelPojo StartSave(){

        GameState gameState = loadAndSaveCompleterNecessaries.getGameState();

        List<SystemView> systemViews = gameState.getResources().getSystemViews();
         systemViewCopy = new ArrayList<>(systemViews);

        List<PocketMain> pocketMains = gameState.getResources().getPockets();
         pocketMainCopy = new ArrayList<>(pocketMains);


        List<Connection> connections = gameState.getResources().getConnections();
        connectionsCopy = new ArrayList<>(connections);



        System.out.println(connectionsCopy.size());
        int Coins = loadAndSaveCompleterNecessaries.getCoinsManager().getNumberOfCoins();

        Map<AbilityTypes , AbilityExecutable> aliveAbilities = new HashMap<>(loadAndSaveCompleterNecessaries.getAbilityManager().getExecutableMap());



        LevelCurrentDetailsPojo currentDetails = setLevelsRequired(aliveAbilities ,  Coins);

        LevelPojo levelPojo = new LevelPojo();

        List<SystemsPojo> systemsPojoList = SystemSavings();
        List<PocketPojo> pocketPojoList = PocketSave();
        List<ConnectionPojo> connectionPojoList = ConnectionSave();

        levelPojo.setCurrentDetails(currentDetails);
        levelPojo.setSystems(systemsPojoList);
        levelPojo.setPockets(pocketPojoList);
        levelPojo.setConnections(connectionPojoList);

        return levelPojo;
    }
    private List<SystemsPojo> SystemSavings(){


        systemsPojoLists.clear();

        for(SystemView systemView : systemViewCopy){
            synchronized (systemView){
                SystemsPojo systemsPojo = getSystemPojo(systemView);
                systemsPojoLists.add(systemsPojo);
            }

        }

        return systemsPojoLists;
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

    private List<PocketPojo> PocketSave(){

        pocketPojoLists.clear();


        for (PocketMain pocketMain : pocketMainCopy){
            synchronized (pocketMain){
                PocketPojo pocketPojo = PocketLoad(pocketMain);
                pocketPojoLists.add(pocketPojo);
            }

        }

        return pocketPojoLists;
    }
    private PocketPojo PocketLoad(PocketMain pocketMain){


        PocketPojo pocketPojo = new PocketPojo();
        pocketPojo.setPocketID(pocketMain.getPocketId());
        pocketPojo.setItAffected(pocketMain.isItAffected());
        pocketPojo.setHP(pocketMain.getHP());
        pocketPojo.setItCollided(pocketMain.isIsItCollided());
        pocketPojo.setGroupId(pocketMain.getGroupId());

        pocketPojo.setItMoved(pocketMain.isIsItMoved());
        pocketPojo.setType(pocketMain.getType());
        pocketPojo.setDelay(pocketMain.getDelay());
        pocketPojo.setFirstPocketType(pocketMain.getFirstPocketType());
        pocketPojo.setTypeBeforeChange(pocketMain.getTypeBeforeChange());
        pocketPojo.setPlaceOfX(pocketMain.getPlaceOfX());
        pocketPojo.setPlaceOfY(pocketMain.getPlaceOfY());

        pocketPojo.setXX(pocketMain.getLayoutX());
        pocketPojo.setYY(pocketMain.getLayoutY());




        SystemView systemView = pocketMain.getWhichSystemViewThisPocketIsAffectedBy();
        if (systemView != null){
            pocketPojo.setWhichSystemViewThisPocketIsAffectedBy(systemView.getSystemID());
        }
        else {
            pocketPojo.setWhichSystemViewThisPocketIsAffectedBy(null);
        }
        pocketPojo.setAvailableTime(pocketMain.getAvailableTime());

        pocketPojo.setPathMover(SetPathMoverPojo(pocketPojo , pocketMain.getPathMover()));

        return pocketPojo;
    }


    private List<ConnectionPojo> ConnectionSave(){
        connectionPojoLists.clear();

        for (Connection connection : connectionsCopy){

            synchronized (connection){
                ConnectionPojo connectionPojo = ConnectionLoad(connection);
                connectionPojoLists.add(connectionPojo);
            }

        }

        return connectionPojoLists;
    }
    private ConnectionPojo ConnectionLoad(Connection connection){

        ConnectionPojo connectionPojo = new ConnectionPojo();

        connectionPojo.setFromPort(SetPortPojo(connection.getFromPort().getPortInfo()));
        connectionPojo.setToPort(SetPortPojo(connection.getToPort().getPortInfo()));

        connectionPojo.setCurve(SetCurvePojo(connection.getCurve()));

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
        PocketMain pocketMain = curve.getPocketMovingOnIt();
        if (pocketMain != null){
            curvePojo.setPocketMovingOnIt(pocketMain.getPocketId());
        }
        else {


            curvePojo.setPocketMovingOnIt(null);


        }
        curvePojo.setLatestAcceptableLength(curve.getLatestAcceptableLength());


        setMiddlePointsOfCurvePojo(curvePojo, curve);
        setFollowerPointsOfCurvePojo(curvePojo, curve);


        return curvePojo;
    }
    private void setFollowerPointsOfCurvePojo(CurvePojo curvePojo, Curve curve){
        curvePojo.setFollowers(new ArrayList<>());
        for (Follower follower : curve.getFollowers()){
            FollowerPojo followerPojo = new FollowerPojo();
            followerPojo.setFollowerType(follower.getFollowerType());
            followerPojo.setRatio(follower.getRatio());

            curvePojo.getFollowers().add(followerPojo);
        }
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
        pojo.setA(mover.getCommitedAcceleration());
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
    private LevelCurrentDetailsPojo setLevelsRequired(Map<AbilityTypes , AbilityExecutable> abilities , int Coins){
        LevelCurrentDetailsPojo pojo = new LevelCurrentDetailsPojo();
        pojo.setCoinsHave(Coins);

        setAbilitiesWeHad(abilities , pojo);

        return pojo;
    }
    private void setAbilitiesWeHad(Map<AbilityTypes , AbilityExecutable> abilities , LevelCurrentDetailsPojo pojo){

        Set<AbilityTypes> aliveAbilities = loadAndSaveCompleterNecessaries.getAbilityAliveManager().getAliveAbilities();
        pojo.setAliveAbilities(new HashSet<>());
        for (AbilityExecutable executable : abilities.values()) {
            if (aliveAbilities.contains(executable.AbilityType())){
                pojo.getAliveAbilities().add(processAbilityExecutorPojo(executable));
            }
        }

    }
    private AbilityExecutorPojo processAbilityExecutorPojo(AbilityExecutable executable ){

        AbilityExecutorPojo pojo = new AbilityExecutorPojo();

        pojo.setAbilityType(executable.AbilityType());
        pojo.setCoolDown(executable.cooldown());
        pojo.setLastUsed(executable.lastUsed());
        pojo.setTimeRemaining(executable.remainingActiveMs(loadAndSaveCompleterNecessaries.getAbilityManager().getGameContext()));
        pojo.setTimeBetweenUsedANdNow(loadAndSaveCompleterNecessaries.getAbilityManager().getGameContext().now() - executable.lastUsed());

        return pojo;
    }
}
