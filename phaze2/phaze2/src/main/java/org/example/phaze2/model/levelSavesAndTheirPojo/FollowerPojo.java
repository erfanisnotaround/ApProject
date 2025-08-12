package org.example.phaze2.model.levelSavesAndTheirPojo;

import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;

public class FollowerPojo {
    private FollowerType followerType;
    private double ratio ;

    public FollowerType getFollowerType() {
        return followerType;
    }

    public void setFollowerType(FollowerType followerType) {
        this.followerType = followerType;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
