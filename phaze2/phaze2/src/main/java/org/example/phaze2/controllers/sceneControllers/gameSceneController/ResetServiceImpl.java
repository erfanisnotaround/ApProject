package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import javafx.beans.value.ChangeListener;
import org.example.phaze2.controllers.moverController.moveRelated.MovementListenerRegistry;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.ResetService;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public final class ResetServiceImpl implements ResetService {
    private final GameState gs;
    public ResetServiceImpl(GameState gs) { this.gs = gs; }

    @Override public void fullResetToSeeds() {
        var view     = gs.getVisualConstant().getView();
        var repo     = gs.getVisualConstant().getRepo();
        var pockets  = gs.getResources().getPockets();
        var groups   = gs.getResources().getPocketGroupIdGroups();
        var idMap    = gs.getResources().getPocketMainMap();

        for (var p : new ArrayList<>(pockets)) { view.remove(p); repo.remove(p); }
        pockets.clear();
        groups.clear();
        idMap.clear();

        for (PocketMain seed : gs.getResources().getBaselinePocketSeeds()) {
            hardResetSeed(seed);
            view.add(seed); repo.add(seed);

            gs.getResources().putPocketGroupIdGroup(seed.getGroupId(), seed);
            gs.getResources().getPocketMainMap().put(seed.getGroupId(), seed);
        }
    }

    @Override
    public void resetMovementListeners(MovementListenerRegistry registry) {
        for (Map.Entry<Connection, ChangeListener<Boolean>> e : registry.waitingSend().entrySet()) {
            e.getKey().getCurve().isItUsedProperty().removeListener(e.getValue());
        }
        // detach pocket listeners
        for (Map.Entry<PocketMain, ChangeListener<Boolean>> e : registry.pockets().entrySet()) {
            e.getKey().isItMovedProperty().removeListener(e.getValue());
        }
        // detach system listeners
        for (Map.Entry<SystemView, ChangeListener<Boolean>> e : registry.systems().entrySet()) {
            e.getKey().isItDownProperty().removeListener(e.getValue());
        }
        registry.clear();
    }

    @Override
    public void resetConnectionsAndSystems() {

        gs.getHudStuffDAta().getCoinsManager().Commit();
        for (Connection c : gs.getResources().getConnections()) {
            c.getCurve().setIsItUsed(false);
            c.resetIt();
            c.getCurve().setPocketMovingOnIt(null);
            c.getCurve().setHP(c.getCurve().getFullHP());
        }

        for (SystemView sv : gs.getResources().getSystemViews()) {
            sv.setIsItDown(false);
            Arrays.fill(sv.getCapacity(), null);
            sv.reset();
        }
    }

    private void hardResetSeed(PocketMain p) {
        p.getPathMover().reset(); p.getPathMover().stop();
        p.setIsItMoved(false); p.setIsItCollided(false);
        p.setItAffected(false); p.setLastRound(false); p.setDone(false);
        p.setCapturedByMerger(false); p.setPocketIsLostByDisterbute(false);
        p.setHP(p.getMaxHp());
        p.setType(p.getFirstPocketType());
        p.setBehaviour(p.getFirstPocketType());
        p.setWhichSystemViewThisPocketIsAffectedBy(null);
        p.setLayoutX(-1000); p.setLayoutY(-1000);
    }
}