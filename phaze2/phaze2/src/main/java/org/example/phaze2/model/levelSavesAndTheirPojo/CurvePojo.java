package org.example.phaze2.model.levelSavesAndTheirPojo;

import java.util.ArrayList;
import java.util.List;

public class CurvePojo {
    private String pocketMovingOnIt;
    private int HP;
    private double latestAcceptableLength;
    private List<AnchorPojo> middlePoints;
    private List<FollowerPojo> followers;
    private boolean isItUsed;

    public String getPocketMovingOnIt() {
        return pocketMovingOnIt;
    }

    public void setPocketMovingOnIt(String pocketMovingOnIt) {
        this.pocketMovingOnIt = pocketMovingOnIt;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public double getLatestAcceptableLength() {
        return latestAcceptableLength;
    }

    public void setLatestAcceptableLength(double latestAcceptableLength) {
        this.latestAcceptableLength = latestAcceptableLength;
    }

    public List<AnchorPojo> getMiddlePoints() {
        return middlePoints;
    }

    public void setMiddlePoints(List<AnchorPojo> middlePoints) {
        this.middlePoints = middlePoints;
    }

    public boolean isItUsed() {
        return isItUsed;
    }

    public void setItUsed(boolean itUsed) {
        isItUsed = itUsed;
    }

    public List<FollowerPojo> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FollowerPojo> followers) {
        this.followers = followers;
    }
}
