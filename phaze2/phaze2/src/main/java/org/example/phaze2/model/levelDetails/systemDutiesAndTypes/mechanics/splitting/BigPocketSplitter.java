package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting;

import javafx.application.Platform;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.Optional;
import java.util.UUID;

public class BigPocketSplitter implements PocketSplitter {

    private final PocketViewPort view;
    private final PocketRepository repo;
    private final PocketVisualEffect effect;
    private final GameState gameState;

    public BigPocketSplitter(PocketViewPort view,
                             PocketRepository repo,
                             PocketVisualEffect effect,
                             GameState gameState) {
        this.view = view;
        this.repo = repo;
        this.effect = effect;
        this.gameState = gameState;
    }

    @Override public void split(PocketMain bigPocket , SystemView systemView) {
        if (!isBigPocket(bigPocket)) return;

        if (Platform.isFxApplicationThread()) {
            doSplit(bigPocket, systemView);
        } else {
            Platform.runLater(() -> doSplit(bigPocket, systemView));
        }
    }

    private void doSplit(PocketMain bigPocket , SystemView systemView) {
        String group = Optional.ofNullable(bigPocket.getGroupId())
                .filter(s -> !s.isEmpty())
                .orElseGet(() -> UUID.randomUUID().toString());

        int pieces = bigPocket.getMaxHp();
        System.out.println("number of pieces: " + pieces);

        view.remove(bigPocket);
        repo.remove(bigPocket);

        gameState.getResources().removePocketGroupIdGroup(group , bigPocket);

        gameState.getResources().getPockets().remove(bigPocket);

        WholeMovement wholeMovement = bigPocket.getMovementManager();
        for (int i = 0; i < pieces; i++) {
            PocketMain m3 = new PocketMain(PocketTypes.Messenger_3 , gameState);
            m3.setGroupId(group);

            m3.setMovementManager(bigPocket.getMovementManager());
            m3.setAvailableTime(bigPocket.getAvailableTime());

            m3.setLayoutX(bigPocket.getLayoutX());
            m3.setLayoutY(bigPocket.getLayoutY());
            m3.setPocketId(i+group);
            effect.apply(m3, group);

            view.add(m3);
            repo.add(m3);
            gameState.getResources().putPocketGroupIdGroup(group , m3);
            gameState.getResources().getPockets().add(m3);

            wholeMovement.SendingPockets(systemView , m3 , -1);

        }
    }

    public boolean isBigPocket(PocketMain p) {
        return PocketTypeGroup.BIG.getGroups().contains(p.getType());
    }
}
