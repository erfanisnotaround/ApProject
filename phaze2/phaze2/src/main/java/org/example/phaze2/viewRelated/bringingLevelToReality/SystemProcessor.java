package org.example.phaze2.viewRelated.bringingLevelToReality;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.constants.CurrentLevelConstants;
import org.example.phaze2.model.jsonRefrencesAndLOadings.SubSystem;
import org.example.phaze2.model.jsonRefrencesAndLOadings.System;
import org.example.phaze2.model.levelDetails.necessary.Light;
import org.example.phaze2.model.levelDetails.necessary.SubSystemView;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.SystemBehaviorFactory;

import java.util.ArrayList;
import java.util.List;

public class SystemProcessor implements Runnable{
    private final List<System> systemInfos;
    private List<SystemView> systemViews = new ArrayList<>();
    private ConnectionUI connectionUI;
    public SystemProcessor(List<System> systemInfos , ConnectionUI connectionUI) {
        this.systemInfos = systemInfos;
        this.connectionUI = connectionUI;
    }
    @Override
    public void run() {
        for (System system : systemInfos) {
            systemViews.add(processSystem(system));
        }
    }
    public SystemView processSystem(System system) {
        SystemView systemView = SystemBehaviorFactory.create(system);
        MakeOnlySystem(systemView , system);

        return systemView;
    }
    public void MakeOnlySystem(SystemView systemView , System systemInfo) {
        systemView.setSystemID(systemInfo.getSystemName());
        systemView.setX(systemInfo.getX());
        systemView.setY(systemInfo.getY());
        systemView.setItStartSystem(systemInfo.isItStartSystem());
        systemView.setTheLightOn(systemInfo.isTheLightOn());


        systemView.setLayoutX(systemInfo.getX());
        systemView.setLayoutY(systemInfo.getY());
        addNameLabel(systemView , systemInfo.getSystemName());
        addLight(systemView);


        for (int i = 0; i < systemInfo.getNumberOfSubSystems(); i++) {
            SubSystemView subSystemView =new SubSystemView(CurrentLevelConstants.getInstance().getWidthOfSubSystems(), CurrentLevelConstants.getInstance().getHeightOfSubSystems());
            processSubSystem(systemInfo.getSubSystems().get(i) , subSystemView , systemView , i);
            systemView.getSubSystems().add(subSystemView);
            systemView.getChildren().add(subSystemView);
        }
    }
    public void addNameLabel(SystemView systemView, String name) {
        Label nameLabel = new Label(name);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        nameLabel.setPrefSize(CurrentLevelConstants.getInstance().getLightBarWidth()*2, CurrentLevelConstants.getInstance().getLightBarHeight());
        systemView.getChildren().add(nameLabel);
        nameLabel.setLayoutX(CurrentLevelConstants.getInstance().getNameLabelX());
        nameLabel.setLayoutY(CurrentLevelConstants.getInstance().getNameLabelY());
    }

    public void addLight(SystemView systemView) {
        CurrentLevelConstants constants = CurrentLevelConstants.getInstance();
        Light light = new Light(constants.getLightRadius() , constants.getLightBarWidth() , constants.getLightBarHeight());

        systemView.setLight(light);
        systemView.getChildren().add(light);

        light.setLayoutX(0);
        light.setLayoutY(0);
    }
    public void processSubSystem(SubSystem subSystem , SubSystemView subSystemView , SystemView systemView , int index) {
        subSystemView.setDoesItHavaExitGate(subSystem.DoesItHavaExitGate());
        subSystemView.setDoesItHaveEnterGate(subSystem.DoesItHaveEnterGate());
        subSystemView.setEnterGate(subSystem.getEnterGate());
        subSystemView.setExitGate(subSystem.getExitGate());
        subSystemView.setLayoutX(CurrentLevelConstants.getInstance().getDistanceOFRightForSubSystems());
        subSystemView.setLayoutY(CurrentLevelConstants.getInstance().getHeightOfSubSystems() * index +
                CurrentLevelConstants.getInstance().getUpperHeight());

        putPorts(systemView , subSystemView , index);

    }
    public void putPorts(SystemView systemView , SubSystemView subSystemView , int index) {

        if (subSystemView.DoesItHaveEnterGate()){
            Port EnterGate = new Port(subSystemView.getEnterGate().createShape());
            subSystemView.setEnterPort(EnterGate);

            EnterGate.setLayoutX(CurrentLevelConstants.getInstance().getDistanceOFRightForSubSystems()/4);
            EnterGate.setLayoutY(CurrentLevelConstants.getInstance().getUpperHeight()  +
                    index * CurrentLevelConstants.getInstance().getHeightOfSubSystems() +
                    CurrentLevelConstants.getInstance().getHeightOfSubSystems()/2);


            connectionUI.registerEnterGate(EnterGate , systemView , index , subSystemView.getEnterGate());
            systemView.getChildren().add( EnterGate);
        }

        if (subSystemView.DoesItHavaExitGate()){
            Port ExitGate = new Port(subSystemView.getExitGate().createShape());

            subSystemView.setExitPort(ExitGate);

            ExitGate.setLayoutX(CurrentLevelConstants.getInstance().getWidth() - CurrentLevelConstants.getInstance().getDistanceOFRightForSubSystems()/2);
            ExitGate.setLayoutY(CurrentLevelConstants.getInstance().getUpperHeight()  +
                    index * CurrentLevelConstants.getInstance().getHeightOfSubSystems() +
                    CurrentLevelConstants.getInstance().getHeightOfSubSystems()/2);



            connectionUI.registerExitGate(ExitGate , systemView , index , subSystemView.getExitGate());
            systemView.getChildren().add(ExitGate);
        }

    }

    public List<SystemView> getSystemViews() {
        return systemViews;
    }
}
