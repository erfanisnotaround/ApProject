package org.example.phaze2.model.winAndPocketLossModel;

import javafx.geometry.Point2D;

public interface GameCondition {

    boolean check();
    String reason();
    int[] statusAtFiring();
    void reset();
}
