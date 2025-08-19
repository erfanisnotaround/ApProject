package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.HudPort;

public final class HudAdapter implements HudPort {
    private final Pane hud;
    public HudAdapter(Pane hud) { this.hud = hud; }
    @Override public void show() { hud.setVisible(true); }
    @Override public void hide() { hud.setVisible(false); }
}
