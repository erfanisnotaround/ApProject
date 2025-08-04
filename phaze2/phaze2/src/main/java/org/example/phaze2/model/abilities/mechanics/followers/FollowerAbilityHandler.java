package org.example.phaze2.model.abilities.mechanics.followers;

import javafx.geometry.Point2D;
import org.example.phaze2.controllers.moverController.moveRelated.PathData;

import java.util.ArrayList;
import java.util.List;

public class FollowerAbilityHandler {




    public double getRatio(Point2D CenterOfFollower , PathData pathData) {

        return pathData.estimateS(CenterOfFollower);
    }

    public Point2D getPointAt(double ratio , PathData pathData) {

        return pathData.pointAt(ratio);
    }

}
