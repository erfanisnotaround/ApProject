package org.example.phaze2.model;

import org.example.phaze2.model.audio.MusicService;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.settingModel.KeyBindingManager;
import org.example.phaze2.model.settingModel.SettingsService;

import java.util.List;

public final class AppContext {
    private final SettingsService settings;
    private final KeyBindingManager keyMgr;
    private final MusicService music;
    private final LevelOfGameLoader levelLoader;
    private final List<Level> levels;
    public AppContext(SettingsService s, KeyBindingManager k , MusicService music , LevelOfGameLoader levelLoader , List<Level> levels) {
        settings=s; keyMgr=k; this.music=music;
        this.levelLoader=levelLoader;
        this.levels=levels;
    }
    public SettingsService settings() { return settings; }
    public KeyBindingManager keyMgr() { return keyMgr; }

    public MusicService getMusic() {
        return music;
    }
    public void writeItAgain(){
        levelLoader.writeItAgain(levels);
    }

    public LevelOfGameLoader getLevelLoader() {
        return levelLoader;
    }

    public List<Level> getLevels() {
        return levels;
    }
}