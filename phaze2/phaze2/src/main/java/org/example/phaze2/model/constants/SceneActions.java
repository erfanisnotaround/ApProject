package org.example.phaze2.model.constants;

import javafx.scene.input.KeyCode;

public enum SceneActions {
    StartTempo        (KeyCode.ENTER),
    OpenShop          (KeyCode.O),
    CloseShop         (KeyCode.C),
    MoveSliderToRight (KeyCode.RIGHT),
    MoveSliderToLeft  (KeyCode.LEFT),
    Open_Close_HUD(KeyCode.TAB),
    DELETE_SELECTION(KeyCode.DELETE),
    Release_Follower(KeyCode.R);

    private final KeyCode defaultKey;
    private KeyCode actionKey;

    SceneActions(KeyCode defaultKey) {
        this.defaultKey  = defaultKey;
        this.actionKey   = defaultKey;
    }

    public KeyCode getKeyCode() {
        return actionKey;
    }

    public void setKeyCode(KeyCode keyCode) {
        this.actionKey = keyCode;
    }

    public void resetToDefault() {
        this.actionKey = defaultKey;
    }

    public KeyCode getDefaultKeyCode() {
        return defaultKey;
    }
}
