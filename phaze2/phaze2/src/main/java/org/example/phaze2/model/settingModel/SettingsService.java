package org.example.phaze2.model.settingModel;

import javafx.scene.input.KeyCode;
import org.example.phaze2.model.constants.SceneActions;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class SettingsService {
    private final SettingsRepository repo;
    private volatile SettingsData data;

    // simple debounce so we don't spam disk on every slider tick
    private final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "SettingsAutoSave"); t.setDaemon(true); return t;
    });
    private ScheduledFuture<?> pending;

    public SettingsService(SettingsRepository repo) {
        this.repo = repo;
        this.data = repo.load();
        ensureDefaults();
    }

    public double getVolume() { return clamp01(data.getMusicVolume()); }
    public void setVolume(double v) {
        data.setMusicVolume(clamp01(v));
        scheduleSave();
    }

    public KeyCode getKey(SceneActions action) {
        String codeName = data.getKeymap().get(action.name());
        if (codeName == null) return action.getKeyCode(); // fall back to enum default
        try { return KeyCode.valueOf(codeName); }
        catch (IllegalArgumentException ex) { return action.getKeyCode(); }
    }

    public void setKey(SceneActions action, KeyCode code) {
        Objects.requireNonNull(code, "KeyCode must not be null");
        data.getKeymap().put(action.name(), code.name()); // store enum name
        scheduleSave();
    }

    public Map<String,String> snapshotKeymap() { return Map.copyOf(data.getKeymap()); }

    public void saveNow() { repo.save(data); }

    public void shutdown() {
        if (pending != null) pending.cancel(false);
        ses.shutdownNow();
    }

    private void scheduleSave() {
        if (pending != null) pending.cancel(false);
        pending = ses.schedule(this::saveNow, 250, TimeUnit.MILLISECONDS);
    }

    private void ensureDefaults() {
        // If not set, seed current SceneActions defaults into the map (one time)
        for (SceneActions action : SceneActions.values()) {
            data.getKeymap().putIfAbsent(action.name(), action.getKeyCode().name());
        }
        data.setMusicVolume(clamp01(data.getMusicVolume()));
    }

    private static double clamp01(double v) { return Math.max(0, Math.min(1, v)); }
}
