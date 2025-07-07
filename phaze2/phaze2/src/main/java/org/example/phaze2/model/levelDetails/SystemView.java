package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.moverController.PathPrioritizing;
import org.example.phaze2.model.constants.CurrentLevelConstants;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SwitchingPocketMovementInSystems;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.ArrayList;
import java.util.Random;

public class SystemView extends Pane implements SystemBehavior , SwitchingPocketMovementInSystems {
    protected boolean isItDown = false;
    protected final SystemTypes systemType;


    protected SystemBehavior behavior;  // nakaramad fellan

    protected final double systemHeight;
    protected final double systemWidth = CurrentLevelConstants.getInstance().getWidth();
    protected BooleanProperty lightBoolean = new SimpleBooleanProperty(false);
    protected Light light;
    protected double x;
    protected double y;
    protected String systemID;
    protected boolean isItStartSystem;
    protected int numberOfSubSystems;
    protected boolean isTheLightOn;
    protected Pocket[] capacity = new Pocket[5];
    protected ArrayList<SubSystemView> SubSystems = new ArrayList<>();
    protected BooleanProperty IsItDown = new SimpleBooleanProperty(false);
    protected PathPrioritizing pathPrioritizing = new PathPrioritizing();
    protected Random random = new Random();
    public SystemView(SystemTypes systemType , int numberOfSubSystems) {
        this.numberOfSubSystems = numberOfSubSystems;
        systemHeight = numberOfSubSystems* CurrentLevelConstants.getInstance().getHeightOfSubSystems() + CurrentLevelConstants.getInstance().getUpperHeight() + CurrentLevelConstants.getInstance().getGapOFBottom();
        setPrefSize(systemWidth, systemHeight);
        this.systemType = systemType;


        String style =
                "-fx-background-color: #781305;" +
                        " -fx-background-radius: 5;" +
                        " -fx-border-color: #5f0f04;" +
                        " -fx-border-width: 2;" +
                        " -fx-border-radius: 5;";
        setStyle(style);
    }

    @Override
    public Connection behave(Pocket EntryPocket) {
        return null;
    }

    @Override
    public void switchPocket(Pocket pocket) {

    }
    public SystemTypes getSystemType() {
        return systemType;
    }

    public SystemBehavior getBehavior() {
        return behavior;
    }

    public double getSystemHeight() {
        return systemHeight;
    }

    public double getSystemWidth() {
        return systemWidth;
    }

    public ArrayList<SubSystemView> getSubSystems() {
        return SubSystems;
    }

    public void setSubSystems(ArrayList<SubSystemView> subSystems) {
        SubSystems = subSystems;
    }

    public Pocket[] getCapacity() {
        return capacity;
    }

    public void setCapacity(Pocket[] capacity) {
        this.capacity = capacity;
    }

    public boolean isTheLightOn() {
        return isTheLightOn;
    }

    public void setTheLightOn(boolean theLightOn) {
        isTheLightOn = theLightOn;
    }

    public int getNumberOfSubSystems() {
        return numberOfSubSystems;
    }

    public void setNumberOfSubSystems(int numberOfSubSystems) {
        this.numberOfSubSystems = numberOfSubSystems;
    }

    public boolean isItStartSystem() {
        return isItStartSystem;
    }

    public void setItStartSystem(boolean itStartSystem) {
        isItStartSystem = itStartSystem;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }



    public boolean isLightBoolean() {
        return lightBoolean.get();
    }

    public BooleanProperty lightBooleanProperty() {
        return lightBoolean;
    }

    public void setLightBoolean(boolean lightBoolean) {
        this.lightBoolean.set(lightBoolean);
    }

    public boolean isItDown() {
        return isItDown;
    }

    public void setItDown(boolean itDown) {
        isItDown = itDown;
    }

    public boolean isIsItDown() {
        return IsItDown.get();
    }

    public BooleanProperty isItDownProperty() {
        return IsItDown;
    }

    public void setIsItDown(boolean isItDown) {
        this.IsItDown.set(isItDown);
    }


}
