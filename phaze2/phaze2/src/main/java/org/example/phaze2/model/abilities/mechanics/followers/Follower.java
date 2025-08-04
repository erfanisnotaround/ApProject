package org.example.phaze2.model.abilities.mechanics.followers;

import javafx.scene.shape.Circle;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

public abstract class Follower extends Circle {
    private double Ratio = 0;

    private final FollowerType FollowerType;

    public Follower(FollowerType FollowerType) {
        this.FollowerType = FollowerType;
    }

    abstract void execute(PocketMain pocket);


    public FollowerType getFollowerType() {
        return FollowerType;
    }
    public void setRatio(double Ratio) {
        this.Ratio = Ratio;
    }
    public double getRatio() {
        return Ratio;
    }

}
