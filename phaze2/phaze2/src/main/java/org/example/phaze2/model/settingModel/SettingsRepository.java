package org.example.phaze2.model.settingModel;

public interface SettingsRepository {
    SettingsData load();
    void save(SettingsData data);
}
