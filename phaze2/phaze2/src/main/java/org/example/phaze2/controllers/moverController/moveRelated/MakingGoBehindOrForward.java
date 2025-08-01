package org.example.phaze2.controllers.moverController.moveRelated;

import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.BehindOnCurveOrNot;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public class MakingGoBehindOrForward implements CheckIfBehindOrNot{

    PocketMain pocketMain;
    BehindOnCurveOrNot behindOnCurveOrNot = new BehindOnCurveOrNot();
    public MakingGoBehindOrForward(PocketMain pocketMain) {
        this.pocketMain = pocketMain;
    }

    @Override
    public void BehindOrNot(PocketMain pocket, Curve curve) {

        Point2D closesPoint = behindOnCurveOrNot.ClosesPointOn( pocket.centre() , curve);

        boolean moveFor = behindOnCurveOrNot.BehindOrNot(pocketMain.centre() , curve , closesPoint);
        if (!moveFor && pocket.isIsItMoved()) {
            pocketMain.getPathMover().moveForward();
        }
        else if (moveFor && pocket.isIsItMoved())pocketMain.getPathMover().moveBackward();



    }
}
