package com.example.phaze1.controllers.ControllingPocketMovement;

import java.io.IOException;

public class TemporalProgressManager {
    MakingMovements makingMovements;
    public TemporalProgressManager(MakingMovements makingMovements) {
        this.makingMovements = makingMovements;
    }
    public void basicsOfSending(double speed , double availableTime) throws IOException {
        makingMovements.goForPocketMovement(speed , availableTime);
    }
}
