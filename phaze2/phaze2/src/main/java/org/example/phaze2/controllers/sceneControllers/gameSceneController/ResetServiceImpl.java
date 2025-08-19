package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.ResetService;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;

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