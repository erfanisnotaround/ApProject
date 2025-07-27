package org.example.phaze2.model.levelSavesAndTheirPojo;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;

public class PocketPojo {
    private String pocketID;
    private boolean isItAffected;
    private double HP;
    private boolean IsItCollided;
    private boolean IsItMoved;
    private PocketTypes type;
    private double delay;
    private PocketTypes firstPocketType;
    private PocketTypes TypeBeforeChange;
    private double placeOfX;
    private double placeOfY;
    private String WhichSystemViewThisPocketIsAffectedBy;
    private double availableTime;
    private PathMoverPojo pathMover;


    public boolean isItAffected() {
        return isItAffected;
    }

    public void setItAffected(boolean itAffected) {
        isItAffected = itAffected;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public boolean isItCollided() {
        return IsItCollided;
    }

    public void setItCollided(boolean itCollided) {
        IsItCollided = itCollided;
    }

    public boolean isItMoved() {
        return IsItMoved;
    }

    public void setItMoved(boolean itMoved) {
        IsItMoved = itMoved;
    }

    public PocketTypes getType() {
        return type;
    }

    public void setType(PocketTypes type) {
        this.type = type;
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public PocketTypes getFirstPocketType() {
        return firstPocketType;
    }

    public void setFirstPocketType(PocketTypes firstPocketType) {
        this.firstPocketType = firstPocketType;
    }

    public PocketTypes getTypeBeforeChange() {
        return TypeBeforeChange;
    }

    public void setTypeBeforeChange(PocketTypes typeBeforeChange) {
        TypeBeforeChange = typeBeforeChange;
    }

    public double getPlaceOfX() {
        return placeOfX;
    }

    public void setPlaceOfX(double placeOfX) {
        this.placeOfX = placeOfX;
    }

    public double getPlaceOfY() {
        return placeOfY;
    }

    public void setPlaceOfY(double placeOfY) {
        this.placeOfY = placeOfY;
    }

    public String getWhichSystemViewThisPocketIsAffectedBy() {
        return WhichSystemViewThisPocketIsAffectedBy;
    }

    public void setWhichSystemViewThisPocketIsAffectedBy(String whichSystemViewThisPocketIsAffectedBy) {
        WhichSystemViewThisPocketIsAffectedBy = whichSystemViewThisPocketIsAffectedBy;
    }

    public double getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(double availableTime) {
        this.availableTime = availableTime;
    }

    public String getPocketID() {
        return pocketID;
    }

    public void setPocketID(String pocketID) {
        this.pocketID = pocketID;
    }

    public PathMoverPojo getPathMover() {
        return pathMover;
    }

    public void setPathMover(PathMoverPojo pathMover) {
        this.pathMover = pathMover;
    }
}
