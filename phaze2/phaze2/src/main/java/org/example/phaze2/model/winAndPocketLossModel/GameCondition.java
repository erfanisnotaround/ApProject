package org.example.phaze2.model.winAndPocketLossModel;

public interface GameCondition {

    boolean check();
    String reason();
    void reset();
}
