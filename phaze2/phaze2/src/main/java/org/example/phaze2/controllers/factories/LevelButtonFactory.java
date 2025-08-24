package org.example.phaze2.controllers.factories;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import org.example.phaze2.model.levelsModel.LevelDescriptor;

import java.util.function.IntConsumer;

public final class LevelButtonFactory {
    private LevelButtonFactory() {}


    public static Button create(LevelDescriptor ld, IntConsumer selectCallback) {
        Button b = new Button(ld.title());
        b.getStyleClass().add("level-button");

        if (ld.suggestedWidth() != null)  b.setPrefWidth(ld.suggestedWidth());
        if (ld.suggestedHeight() != null) b.setPrefHeight(ld.suggestedHeight());

        b.setDisable(ld.locked());
        b.setTooltip(new Tooltip(ld.locked() ? "Locked" : "Play " + ld.title()));

        b.getStyleClass().add("level-icon");
        b.setOnAction(e -> {
            if (!ld.locked()) {
                selectCallback.accept(ld.id()); // int!
            }
        });

        b.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
            }
        });

        return b;
    }

    public static Button create(int levelId, IntConsumer selectCallback) {
        return create(new LevelDescriptor(levelId, "Level " + levelId, false, null, null), selectCallback);
    }
}