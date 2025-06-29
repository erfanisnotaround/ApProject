package org.example.phaze2.model.levelDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.example.phaze2.model.constants.CurrentLevelConstants;
import org.example.phaze2.model.constants.SystemTypes;
import org.example.phaze2.model.levelDetails.systemDuties.SystemBehavior;
import org.example.phaze2.model.levelDetails.systemDuties.SystemBehaviorFactory;

import java.util.ArrayList;

public class SystemView extends Pane {
    private final SystemTypes systemType;
    private final SystemBehavior behavior;

    private final double systemHeight;
    private final double systemWidth = 100;
    public BooleanProperty lightBoolean = new SimpleBooleanProperty(false);
    public Button startButton;
    public Light light;
    public double x;
    public double y;
    public String systemID;
    public boolean isItStartSystem;
    public int numberOfSubSystems;
    public boolean isTheLightOn;
    public Pocket[] capacity = new Pocket[5];
    public ArrayList<SubSystemView> SubSystems = new ArrayList<>();

    public SystemView(SystemTypes systemType , int numberOfSubSystems) {
        this.numberOfSubSystems = numberOfSubSystems;
        systemHeight = numberOfSubSystems* CurrentLevelConstants.getInstance().getHeightOfSubSystems() + CurrentLevelConstants.getInstance().getUpperHeight();
        this.systemType = systemType;
        this.behavior = SystemBehaviorFactory.create(systemType);
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
}
