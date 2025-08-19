package org.example.phaze2.model.settingModel;

import java.util.HashMap;
import java.util.Map;

public class SettingsData {
    // 0..1
    private double musicVolume = 0.6;                  // 0..1
    private Map<String,String> keymap = new HashMap<>(); // SceneActions.name -> KeyCode.name

    public double getMusicVolume() { return musicVolume; }
    public void setMusicVolume(double v) { this.musicVolume = v; }

    public Map<String, String> getKeymap() { return keymap; }
    public void setKeymap(Map<String, String> m) { this.keymap = m; }
}
