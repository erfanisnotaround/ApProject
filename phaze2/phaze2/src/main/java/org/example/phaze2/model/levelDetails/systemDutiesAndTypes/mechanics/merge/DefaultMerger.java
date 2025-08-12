package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge;

import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.PocketRepository;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.PocketViewPort;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.PocketVisualEffect;

import java.util.List;

public class DefaultMerger implements PocketMerger {
    private final PocketViewPort view;
    private final PocketRepository repo;
    private final PocketVisualEffect effect;
    private GameState gameState;

    public DefaultMerger(PocketViewPort view, PocketRepository repo, PocketVisualEffect effect , GameState gameState) {
        this.view = view;
        this.repo = repo;
        this.effect = effect;
        this.gameState = gameState;
    }

    @Override
    public PocketMain merge(List<PocketMain> parts, PocketTypes type, String groupId, SystemView system) {
        if (parts.isEmpty()) return null;

        PocketMain seed = parts.get(0);

        for (PocketMain part : parts) {
            part.setCapturedByMerger(true);
            view.remove(part);
            repo.remove(part);
        }

        PocketMain merged = new PocketMain(type , gameState);
        merged.setGroupId(groupId);
        merged.setLayoutX(seed.getLayoutX());
        merged.setLayoutY(seed.getLayoutY());
        merged.setAvailableTime(seed.getAvailableTime());
        merged.setMovementManager(seed.getMovementManager());
        effect.apply(merged, groupId);

        view.add(merged);
        repo.add(merged);

        return merged;
    }
}
