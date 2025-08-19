package org.example.phaze2.controllers.sceneControllers;

// ResourcesAssembler.java


import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.portConnectingDetails.Connection;
import org.example.phaze2.viewRelated.bringingLevelToReality.SystemVisualizer;

import java.util.*;

public final class ResourcesAssembler {
    private ResourcesAssembler() {}

    public static void assemble(GameState gs,
                                Pane container,
                                SystemVisualizer visualizer,
                                ConnectionUI connectionUI) {

        var res = gs.getResources();

        // 0) Hard clear previous state
        container.getChildren().removeAll(res.getSystemViews());
        container.getChildren().removeAll(res.getPockets());
        res.getSystemViews().clear();
        res.getPockets().clear();
        res.getPocketMainMap().clear();
        res.getSystemViewMap().clear();
        res.getPocketGroupIdGroups().clear();
        res.getConnections().clear();
        res.getExitConnections().clear();

        // 1) Fetch new nodes
        List<SystemView> systems = visualizer.getSystemViews();
        List<PocketMain> pockets = visualizer.getPockets();

        for (SystemView s : systems) {
            container.getChildren().addFirst(s);
            res.getSystemViews().add(s);
            if (s.getSystemID() != null) {
                res.getSystemViewMap().put(s.getSystemID(), s);
            }
        }

        for (PocketMain p : pockets) {
            container.getChildren().addLast(p);
            res.getPockets().add(p);

            p.setLayoutX(-1000);
            p.setLayoutY(-1000);

            if (p.getPocketId() != null) {
                res.getPocketMainMap().put(p.getPocketId(), p);
            }

            // group by groupId
            String gid = p.getGroupId();
            if (gid != null) {
                res.putPocketGroupIdGroup(gid, p); // ensures set exists then adds
            }
        }

        res.setBaselinePocketSeeds(new ArrayList<>(pockets));


    }
}

