package org.example.phaze2.model.settingModel;

import javafx.scene.input.KeyCode;
import org.example.phaze2.model.constants.SceneActions;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class KeyBindingManager {
    private final SettingsService settings;
    private final Map<KeyCode, SceneActions> reverse = new HashMap<>();
    private final Map<SceneActions, KeyCode> cache   = new EnumMap<>(SceneActions.class);

    public KeyBindingManager(SettingsService settings) { this.settings = settings; }

    public void syncSceneActionsFromSettings() {
        reverse.clear();
        cache.clear();
        for (SceneActions a : SceneActions.values()) {
            KeyCode k = settings.getKey(a);
            a.setKeyCode(k);          // <- updates your enum actionKey
            reverse.put(k, a);
            cache.put(a, k);
        }
    }

    public SceneActions find(KeyCode code) { return reverse.get(code); }

    public KeyCode keyFor(SceneActions action) { return cache.get(action); }
}