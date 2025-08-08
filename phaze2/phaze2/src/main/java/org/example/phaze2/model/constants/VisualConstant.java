package org.example.phaze2.model.constants;

import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketViewManager;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.*;

public final class VisualConstant {
    private Pane ContainerPane;
    private BigPocketSplitter splitter;
    private PocketViewPort view;
    private PocketRepository repo;
    private PocketVisualEffect effect;

    public Pane getContainerPane() {
        return ContainerPane;
    }

    public void setContainerPane(Pane containerPane) {
        ContainerPane = containerPane;
    }

    public BigPocketSplitter getSplitter() {
        return splitter;
    }

    public void setSplitter(BigPocketSplitter splitter) {
        this.splitter = splitter;
    }

    public PocketViewPort getView() {
        return view;
    }

    public void setView(PocketViewPort view) {
        this.view = view;
    }

    public PocketRepository getRepo() {
        return repo;
    }

    public void setRepo(PocketRepository repo) {
        this.repo = repo;
    }

    public PocketVisualEffect getEffect() {
        return effect;
    }

    public void setEffect(PocketVisualEffect effect) {
        this.effect = effect;
    }
}
