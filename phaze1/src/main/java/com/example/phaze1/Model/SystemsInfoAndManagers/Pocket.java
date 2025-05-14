package com.example.phaze1.Model.SystemsInfoAndManagers;
import javafx.beans.property.*;
import javafx.scene.shape.*;
public class Pocket extends Polygon{
    private BooleanProperty lastRound = new SimpleBooleanProperty(false);
    private IntegerProperty Coins = new SimpleIntegerProperty(0);
    private BooleanProperty isLost = new SimpleBooleanProperty(false);
    private BooleanProperty isWinning = new SimpleBooleanProperty(false);
    private Pocket defaultPocket;
    private boolean inTheGame = false;
    private final double RadiusOFDetection = 40;
    private GateType type;
    private double delay;
    private int whichSubSystem;
    private double distanceFromTheLine = 0;
    private  double MaxDistanceFromTheLine = 9;
    private DoubleProperty availableTime = new SimpleDoubleProperty(30);
    private double speed = 1;
    private DoubleProperty HP = new SimpleDoubleProperty(0);
    private double MaxHP;
    public Pocket(double v, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        super(v, v1, v2, v3, v4, v5, v6, v7);
    }

    public Pocket(double v, double v1, int v2, int v3, double v4, int v5) {
        super(v, v1, v2, v3, v4, v5);
    }

    public double getMaxDistanceFromTheLine() {
        return MaxDistanceFromTheLine;
    }

    public void setMaxDistanceFromTheLine(double maxDistanceFromTheLine) {
        MaxDistanceFromTheLine = maxDistanceFromTheLine;
    }

    public double getDistanceFromTheLine() {
        return distanceFromTheLine;
    }

    public void setDistanceFromTheLine(double distanceFromTheLine) {
        this.distanceFromTheLine = distanceFromTheLine;
    }

    public GateType getType() {
        return type;
    }

    public void setType(GateType type) {
        this.type = type;
        HP.set(this.type.getPocketHp());
        MaxHP = this.type.getPocketHp();
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public int getWhichSubSystem() {
        return whichSubSystem;
    }

    public void setWhichSubSystem(int whichSubSystem) {
        this.whichSubSystem = whichSubSystem;
    }

    public double getRadiusOFDetection() {
        return RadiusOFDetection;
    }

    public boolean isInTheGame() {
        return inTheGame;
    }

    public void setInTheGame(boolean inTheGame) {
        this.inTheGame = inTheGame;
    }

    public double getHP() {
        return HP.get();
    }

    public DoubleProperty HPProperty() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP.set(HP);
    }

    public double getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP(double maxHP) {
        MaxHP = maxHP;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAvailableTime() {
        return availableTime.get();
    }

    public DoubleProperty availableTimeProperty() {
        return availableTime;
    }

    public void setAvailableTime(double availableTime) {
        this.availableTime.set(availableTime);
    }

    public Pocket getDefaultPocket() {
        return defaultPocket;
    }

    public void setDefaultPocket(Pocket defaultPocket) {
        this.defaultPocket = defaultPocket;
    }

    public boolean isIsLost() {
        return isLost.get();
    }

    public BooleanProperty isLostProperty() {
        return isLost;
    }

    public void setIsLost(boolean isLost) {
        this.isLost.set(isLost);
    }

    public int getCoins() {
        return Coins.get();
    }

    public IntegerProperty coinsProperty() {
        return Coins;
    }

    public void setCoins(int coins) {
        this.Coins.set(coins);
    }

    public boolean isIsWinning() {
        return isWinning.get();
    }

    public BooleanProperty isWinningProperty() {
        return isWinning;
    }

    public void setIsWinning(boolean isWinning) {
        this.isWinning.set(isWinning);
    }

    public boolean isLastRound() {
        return lastRound.get();
    }

    public BooleanProperty lastRoundProperty() {
        return lastRound;
    }

    public void setLastRound(boolean lastRound) {
        this.lastRound.set(lastRound);
    }
}