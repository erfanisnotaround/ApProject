package org.example.phaze2.model;

import org.example.phaze2.model.settingModel.KeyBindingManager;
import org.example.phaze2.model.settingModel.SettingsService;

public final class AppContext {
    private final SettingsService settings;
    private final KeyBindingManager keyMgr;
    public AppContext(SettingsService s, KeyBindingManager k) { settings=s; keyMgr=k; }
    public SettingsService settings() { return settings; }
    public KeyBindingManager keyMgr() { return keyMgr; }
}