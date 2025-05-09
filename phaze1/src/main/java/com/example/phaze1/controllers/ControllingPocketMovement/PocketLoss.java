package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.Constants.constants;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PocketLoss {
    private Pane LineContainer;
    private ArrayList<Pocket> pockets = constants.getPockets();
    private ArrayList<Pocket> pocketLost = new ArrayList<>();
    private IntegerProperty pocketLoss = new SimpleIntegerProperty(0);
    public PocketLoss(Pane LineContainer) {
        this.LineContainer = LineContainer;
    }
    public void removeWastedPockets() {
        for (Pocket p : pockets) {
            p.HPProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() == 0.0){
                    pockets.remove(p);
                    pocketLost.add(p);
                    pocketLoss.set(pocketLoss.get() + 1);
                    System.out.println("Pocket lost");
                }
            });
        }
    }
    public void resetPocketLoss() {
        pockets.addAll(pocketLost);
        pocketLoss.set(0);
    }

    public int getPocketLoss() {
        return pocketLoss.get();
    }

    public IntegerProperty pocketLossProperty() {
        return pocketLoss;
    }

    public void setPocketLoss(int pocketLoss) {
        this.pocketLoss.set(pocketLoss);
    }
}
