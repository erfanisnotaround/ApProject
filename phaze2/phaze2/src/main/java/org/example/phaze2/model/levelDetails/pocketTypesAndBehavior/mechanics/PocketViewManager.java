package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.PocketViewPort;

public class PocketViewManager implements PocketViewPort {
    private final Pane ContainerPane;
    public PocketViewManager(Pane pane) {
        this.ContainerPane = pane;
    }

    @Override
    public void add(Node node) {
        ContainerPane.getChildren().add(node);
    }

    @Override
    public void remove(Node node) {
        ContainerPane.getChildren().remove(node);
    }
}
