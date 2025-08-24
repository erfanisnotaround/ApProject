package org.example.phaze2.model.settingModel;

public interface SettingObserver {
    default void onKeymapChanged() {}
    default void onVolumeChanged(double volume01) {}
}
