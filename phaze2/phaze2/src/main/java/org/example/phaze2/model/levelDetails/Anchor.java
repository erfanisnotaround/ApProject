package org.example.phaze2.model.levelDetails;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anchor extends Circle {
    private final Runnable onDragExtras;
    private static final double R = 6;          // handle radius

    public Anchor(Color fill,
                  DoubleProperty x, DoubleProperty y , Runnable extras) {

        super(x.get(), y.get(), R);
        this.onDragExtras = extras;
        setFill(fill.deriveColor(1, 1, 1, 0.9));
        setStroke(Color.BLACK);
        setStrokeWidth(1);

        centerXProperty().bindBidirectional(x);
        centerYProperty().bindBidirectional(y);

        enableDrag(x, y);
    }

    private void enableDrag(DoubleProperty x, DoubleProperty y) {
        setOnMousePressed(e -> getScene().setCursor(Cursor.MOVE));
        setOnMouseReleased(e -> getScene().setCursor(Cursor.DEFAULT));

        setOnMouseDragged(e -> {
            x.set(e.getX());
            y.set(e.getY());
            if (onDragExtras != null) onDragExtras.run();
        });

    }
}