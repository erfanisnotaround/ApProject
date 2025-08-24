package org.example.phaze2.model.audio;

import org.example.phaze2.model.settingModel.SettingObserver;
import org.example.phaze2.model.settingModel.SettingsService;

public final class MusicService implements SettingObserver, AutoCloseable {
    private final SettingsService settings;
    private final MusicPlayer player;

    public MusicService(SettingsService settings, MusicPlayer player) {
        this.settings = settings;
        this.player   = player;
        player.setVolume(settings.getVolume());
        settings.addObserver(this);
    }

    public void playLoopResource(String resourcePath) {
        player.load(resourcePath);
        player.setVolume(settings.getVolume());
        player.playLoop();
    }

    public void pause()  { player.pause(); }
    public void resume() { player.resume(); }
    public void stop()   { player.stop(); }

    @Override public void onVolumeChanged(double v01) { player.setVolume(v01); }

    @Override public void close() {
        settings.removeObserver(this);
        player.close();
    }
}
