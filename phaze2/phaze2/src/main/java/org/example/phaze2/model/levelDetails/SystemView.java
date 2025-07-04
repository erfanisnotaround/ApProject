package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.constants.CurrentLevelConstants;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehaviorFactory;

import java.util.ArrayList;

public class SystemView extends Pane {
    private boolean isItDown = false;
    private final SystemTypes systemType;


    private  SystemBehavior behavior;  // nakaramad fellan


    private final double systemHeight;
    private final double systemWidth = CurrentLevelConstants.getInstance().getWidth();
    private BooleanProperty lightBoolean = new SimpleBooleanProperty(false);
    private Light light;
    private double x;
    private double y;
    private String systemID;
    private boolean isItStartSystem;
    private int numberOfSubSystems;
    private boolean isTheLightOn;
    private Pocket[] capacity = new Pocket[5];
    private ArrayList<SubSystemView> SubSystems = new ArrayList<>();

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
}
