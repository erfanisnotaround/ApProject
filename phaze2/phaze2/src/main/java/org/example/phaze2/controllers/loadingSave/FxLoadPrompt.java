package org.example.phaze2.controllers.loadingSave;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.phaze2.model.loadingSaves.Decision;

public final class FxLoadPrompt implements LoadPrompt {
    private final Stage owner;
    public FxLoadPrompt(Stage owner) { this.owner = owner; }

    @Override
    public Decision ask(String title, String message) {
        ButtonType load   = new ButtonType("Load saved game", ButtonBar.ButtonData.YES);
        ButtonType fresh  = new ButtonType("Start fresh",     ButtonBar.ButtonData.NO);
        ButtonType cancel = new ButtonType("Cancel",          ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert a = new Alert(Alert.AlertType.CONFIRMATION, message, load, fresh, cancel);
        a.initOwner(owner);
        a.initModality(Modality.WINDOW_MODAL);
        a.setTitle(title);
        a.setHeaderText(null);

        var result = a.showAndWait().orElse(cancel);
        if (result == load)  return Decision.LOAD;
        if (result == fresh) return Decision.FRESH;
        return Decision.CANCEL;
    }
}