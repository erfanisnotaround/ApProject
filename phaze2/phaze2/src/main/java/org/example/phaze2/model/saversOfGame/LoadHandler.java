package org.example.phaze2.model.saversOfGame;

import javafx.geometry.Point2D;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAbilityController;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.controllers.factories.FollowerFactory;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.levelDetails.necessary.*;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.GroupStash;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.SingleGroupBuffer;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.Palette;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.types.MergerBehavior;
import org.example.phaze2.model.levelSavesAndTheirPojo.*;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.*;

public class LoadHandler {

    List<PocketMain> pocketMains;
    List<SystemView> systemViews;
    List<Connection> connections;

    Map<String , PocketMain> pocketMainMap;
    Map<String , SystemView> systemViewMap;

    private ConnectionUI connectionUI;
    private LoadAndSaveCompleterNecessaries loadCompleterNecessaries;
    private FollowerAbilityController followerAbilityController;
    private GameState gameState;

    public LoadHandler(LoadAndSaveCompleterNecessaries loadCompleterNecessaries) {
        this.loadCompleterNecessaries = loadCompleterNecessaries;
        this.connectionUI = loadCompleterNecessaries.getConnectionUI();
        this.followerAbilityController = loadCompleterNecessaries.getFollowerAbilityController();
        this.gameState = loadCompleterNecessaries.getGameState();
        pocketMains = gameState.getResources().getPockets();
        systemViews = gameState.getResources().getSystemViews();
        connections = gameState.getResources().getConnections();
        pocketMainMap = gameState.getResources().getPocketMainMap();
        systemViewMap = gameState.getResources().getSystemViewMap();
    }


    public void StartSettingInfoOfLevel(LevelPojo InfoOfLevel){
        List<PocketPojo> pocketPojo = InfoOfLevel.getPockets();
        List<SystemsPojo> systemsPojo = InfoOfLevel.getSystems();
        List<ConnectionPojo> connectionPojo = InfoOfLevel.getConnections();



        SetPockets(pocketPojo);
        SetSystemViews(systemsPojo);
        SetConnections(connectionPojo);

        loadLevelThings(InfoOfLevel.getCurrentDetails());

    }
    private void SetPockets(List<PocketPojo> pocketPojoList) {
        // 1) Index save by id
        Map<String, PocketPojo> byId = new HashMap<>();
        for (PocketPojo pj : pocketPojoList) byId.put(pj.getPocketID(), pj);

        // 2) Collect current ids
        Set<String> have = new HashSet<>(pocketMainMap.keySet());
        Set<String> want = new HashSet<>(byId.keySet());

        for (String id : diff(have, want)) {
            PocketMain p = pocketMainMap.remove(id);
            if (p == null) continue;
            gameState.getVisualConstant().getView().remove(p);     // PocketViewPort
            gameState.getVisualConstant().getRepo().remove(p);     // PocketRepository

;
        }

        for (String id : diff(want, have)) {
            PocketPojo pj = byId.get(id);
            PocketMain p = new PocketMain(pj.getType(), gameState);
            p.setPocketId(pj.getPocketID());
            p.setGroupId(pj.getGroupId());

            gameState.getVisualConstant().getView().add(p);
            gameState.getVisualConstant().getRepo().add(p);

            gameState.getVisualConstant().getEffect().apply(p , pj.getGroupId());
            pocketMainMap.put(id, p);

            if (pj.getGroupId() != null) {
                gameState.getVisualConstant().getEffect().apply(p, pj.getGroupId());
            }
        }

        for (PocketPojo pj : pocketPojoList) {
            PocketMain target = pocketMainMap.get(pj.getPocketID());
            applyPocketPojo(target, pj); // below
        }
    }

    private static Set<String> diff(Set<String> a, Set<String> b) {
        Set<String> out = new HashSet<>(a); out.removeAll(b); return out;
    }
    private void applyPocketPojo(PocketMain targetPocket, PocketPojo pocketPojo){
        // If type differs, switch behaviour cleanly
        if (targetPocket.getType() != pocketPojo.getType()) {
            targetPocket.setBehaviour(pocketPojo.getType()); // your code already switches types here
        }
        targetPocket.setGroupId(pocketPojo.getGroupId());
        targetPocket.setAvailableTime(pocketPojo.getAvailableTime());
        targetPocket.setItAffected(pocketPojo.isItAffected());
        targetPocket.setHP(pocketPojo.getHP());
        targetPocket.setIsItCollided(pocketPojo.isItCollided());
        targetPocket.setIsItMoved(pocketPojo.isItMoved());
        targetPocket.setDelay(pocketPojo.getDelay());
        targetPocket.setFirstPocketType(pocketPojo.getFirstPocketType());
        targetPocket.setTypeBeforeChange(pocketPojo.getTypeBeforeChange());

        if (pocketPojo.getWhichSystemViewThisPocketIsAffectedBy() != null) {
            targetPocket.setWhichSystemViewThisPocketIsAffectedBy(
                    systemViewMap.get(pocketPojo.getWhichSystemViewThisPocketIsAffectedBy()));
        } else {
            targetPocket.setWhichSystemViewThisPocketIsAffectedBy(null);
        }

        targetPocket.setPlaceOfX(pocketPojo.getPlaceOfX());
        targetPocket.setPlaceOfY(pocketPojo.getPlaceOfY());
        targetPocket.setLayoutX(pocketPojo.getXX());
        targetPocket.setLayoutY(pocketPojo.getYY());

        SetPathMover(targetPocket, pocketPojo); // your existing method
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
            PocketMain pocketMain = pocketMainMap.get(pocketMovingOnItName);
            curve.setPocketMovingOnIt(pocketMain);

            pocketMain.getPathMover().setCurve(curve);

        }
        else curve.setPocketMovingOnIt(null);

        for (AnchorPojo anchorPojo : curvePojo.getMiddlePoints()) {
            setMiddlePoints(curve , anchorPojo);
        }
        setFollowers(curvePojo , curve);

        return curve;

    }
    private void setMiddlePoints(Curve curve , AnchorPojo anchorPojo) {
        Anchor anchor = new Anchor(new Point2D(anchorPojo.getCenterX() , anchorPojo.getCenterY()));
        anchor.setLatestCord(new Point2D(anchorPojo.getLatestCordX() , anchorPojo.getLatestCordY()));
        curve.getAnchors().add(anchor);
    }
    private void setFollowers(CurvePojo curvePojo , Curve curve) {
        for (FollowerPojo followerPojo : curvePojo.getFollowers()) {
            Follower follower  = FollowerFactory.createFollower(followerPojo.getFollowerType());
            follower.setRatio(followerPojo.getRatio());
            curve.getFollowers().add(follower);

        }
    }

    private void loadLevelThings(LevelCurrentDetailsPojo levelCurrentDetailsPojo) {
        CoinsManager coinsManager = loadCompleterNecessaries.getCoinsManager();
        coinsManager.setCoinsBeforeStart(levelCurrentDetailsPojo.getCoinsHave());
        coinsManager.setNumberOfCoins(levelCurrentDetailsPojo.getCoinsHave());
        restoreMergerSlots(levelCurrentDetailsPojo.getMergerSlots());
        loadAbilities(levelCurrentDetailsPojo);
        setAbilities(levelCurrentDetailsPojo.getAliveAbilities());

    }
    private void loadAbilities(LevelCurrentDetailsPojo levelCurrentDetailsPojo) {
        var effect = gameState.getVisualConstant().getEffect();
        var hp = levelCurrentDetailsPojo.getHuePalettePojo();

        Palette pal = new Palette();
        pal.nextHue = hp.getNextHue();
        pal.hues.putAll(hp.getHues());

        effect.restore(pal);

        for (PocketMain pocketMain : gameState.getResources().getPockets()){
            effect.ApplyIfThere(pocketMain);
        }
    }
    private void restoreMergerSlots(Map<String, String[]> saved) {
        if (saved == null) return;
        for (Map.Entry<String, String[]> e : saved.entrySet()) {
            String systemId = e.getKey();
            String[] slotIds = e.getValue();

            SystemView sv = systemViewMap.get(systemId);
            if (!(sv instanceof MergerBehavior mb)) continue;

            GroupStash stash = mb.getStash();
            PocketMain[] slots = stash.getSlot();

            Arrays.fill(slots, null);

            String groupId = null;
            int n = slotIds.length;
            for (int i = 0; i < n; i++) {
                String pid = slotIds[i];
                if (pid == null) continue;
                PocketMain p = pocketMainMap.get(pid);

                if (p == null) continue;


                // ensure pocket is not simultaneously in system capacity
                // (defensive: if your systems kept references, clean them)
                // Optional: remove from any capacity arrays that still hold it.

                slots[i] = p;
                if (groupId == null && p.getGroupId() != null) groupId = p.getGroupId();
            }

            if (stash instanceof SingleGroupBuffer sb) {
                sb.setGroupId(groupId);
            }
        }
    }
    private void setAbilities(Set<AbilityExecutorPojo> abilities) {
        loadCompleterNecessaries.getAfterPreShow().getAbilityInfo().clear();
        for (AbilityExecutorPojo abilityExecutorPojo : abilities) {
            loadCompleterNecessaries.getAfterPreShow().getAbilityInfo().add(abilityExecutorPojo);
            loadCompleterNecessaries.getAbilityAliveManager().AddAliveAbility(abilityExecutorPojo.getAbilityType());
            loadCompleterNecessaries.getAbilityManager().RegisterAbility(abilityExecutorPojo.getAbilityType());
        }
    }

}
