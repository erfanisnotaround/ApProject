// VisualShellConfigurator.java
package org.example.phaze2.controllers.sceneControllers;

import javafx.scene.layout.Pane;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.*;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketViewManager;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge.*;

public final class VisualShellConfigurator {
    private VisualShellConfigurator() {}

    /** Sets VisualConstant + MergerConfig on GameState. */
    public static void configure(GameState gs, Pane container) {
        gs.getVisualConstant().setContainerPane(container);

        PocketViewPort view   = new PocketViewManager(container);
        PocketRepository repo = new PocketRepository(gs.getResources());
        PocketVisualEffect effect = new HueShiftEffect();
        BigPocketSplitter splitter = new BigPocketSplitter(view, repo, effect, gs);

        gs.getVisualConstant().setView(view);
        gs.getVisualConstant().setRepo(repo);
        gs.getVisualConstant().setEffect(effect);
        gs.getVisualConstant().setSplitter(splitter);

        PocketMerger merger = new DefaultMerger(view, repo, effect, gs);
        PocketMergePolicy policy = new FlexibleMergePolicy(4);
        gs.getMergerConfig().setMerger(merger);
        gs.getMergerConfig().setPolicy(policy);
    }
}
