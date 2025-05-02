package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.Constants.constants;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PocketLoss {
    private Pane LineContainer;
    private ArrayList<Pocket> pockets = constants.getPockets();
    public PocketLoss(Pane LineContainer) {
        this.LineContainer = LineContainer;
    }
    public void removeWastedPockets() {
        for (Pocket p : pockets) {
            p.HPProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() == 0.0){
                    LineContainer.getChildren().remove(p);
                    pockets.remove(p);
                    System.out.println("Pocket lost");
                }
            });
        }
    }

}
