package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting;


import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public interface PocketVisualEffect {
    void apply(PocketMain pocket, String groupId);
    Palette snapshot();
    void restore(Palette p);
    void ApplyIfThere(PocketMain pocketMain);
}
