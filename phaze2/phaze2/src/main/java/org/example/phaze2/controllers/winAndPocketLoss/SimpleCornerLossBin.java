package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class SimpleCornerLossBin implements DeadPocketPlacementStrategy {
    private final Pane container;
    private final double startX, startY, cell, cols;
    private int index = 0;

    public SimpleCornerLossBin(Pane container, double startX, double startY,
                               double cellSize, int columns) {
        this.container = container;
        this.startX = startX; this.startY = startY; this.cell = cellSize; this.cols = columns;
    }

    @Override public void place(PocketMain p) {
        int row = index / (int)cols, col = index % (int)cols;
        double x = startX + col * cell, y = startY + row * cell;
        index++;
        Platform.runLater(() -> {
            p.setLayoutX(x);
            p.setLayoutY(y);
            p.getHitBox().setLayoutX(x);
            p.getHitBox().setLayoutY(y);
            p.toFront();
        });
    }

    @Override
    public void reset() {
        index = 0;
    }

}
