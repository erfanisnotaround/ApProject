package org.example.phaze2.model.loadingSaves;

import org.example.phaze2.controllers.loadingSave.LoadPrompt;
import org.example.phaze2.model.levelSavesAndTheirPojo.AfterPreShow;
import org.example.phaze2.model.saversOfGame.SaveAndLoadController;

public final class LoadGameStartup {
    private final LoadPrompt prompt;

    public LoadGameStartup(LoadPrompt prompt) {
        this.prompt = prompt;
    }

    public void maybeOfferLoadOrFresh(SaveAndLoadController slc , AfterPreShow afterPreShow) {
        if (slc == null || !slc.hasUsableSave()) return;

        var decision = prompt.ask(
                "Continue?",
                "A saved game was found for this level.\nDo you want to load it or start fresh?"
        );
        switch (decision) {
            case LOAD -> {
                slc.stopAutoSave();
                slc.loadTheSave();
                slc.startAutoSave();

                afterPreShow.preShow();
            }
            case FRESH, CANCEL -> {
                // Do nothing; scene is already built “fresh” by wiring.
            }
        }
        slc.startAutoSave();
    }
}
