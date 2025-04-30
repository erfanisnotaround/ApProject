package com.example.phaze1.Model.SystemsInfoAndManagers;

import com.example.phaze1.Model.Constants.constants;
import com.example.phaze1.Model.levelLoadingStuff.LevelLoader;
import com.example.phaze1.Model.levelLoadingStuff.SubSystem;
import com.example.phaze1.Model.levelLoadingStuff.Systems;
import com.example.phaze1.Model.levelLoadingStuff.level;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;

public class BringItOn {
    GateConnectorManager manager;
    private double lengthOfSubSystems = 40;
    private double width = 100;
    private double height = 0;
    private double lightBar = 30;
    private double distanceOFRight = 8;
    private level currentLevel;
    Pane LineContainer;
    public BringItOn(Pane LineContainer) {
        this.LineContainer = LineContainer;

    }
    public ArrayList<SystemView> makingEachSystems(int level) throws IOException {
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.setLevel(level);
        currentLevel = levelLoader.loadingCurrentLevel();
        manager = new GateConnectorManager(LineContainer , constants.getWireManager());
        constants.setPortInfo(manager.GateInfo());
        constants.setConnections(manager.getConnections());
        constants.setExitConnections(manager.getExitConnections());
        ArrayList<SystemView> systems = new ArrayList<>();
        for (Systems system : currentLevel.getSystems()){
            systems.add(makeASystem(system));
        }
        return systems;
    }
    public SystemView makeASystem(Systems system) {
         SystemView newSystem = new SystemView();
         newSystem.setLayoutX(system.getX());
         newSystem.setLayoutY(system.getY());
         newSystem.x = system.getX();
         newSystem.y = system.getY();
         newSystem.isItFinisherSystem = system.isItFinisherSystem();
         newSystem.isItStartSystem = system.isItStartSystem();
         newSystem.numberOfSubSystems = system.getNumberOfSubSystems();
         newSystem.isTheLightOn = system.isTheLightOn();
         newSystem.light = new Light(10 , distanceOFRight*2 , lightBar/4);
         newSystem.light.setLayoutX(distanceOFRight );
         newSystem.light.setLayoutY(lightBar / 2);
         newSystem.light.isItOn = false;
         newSystem.getChildren().add(newSystem.light);
         height = system.getNumberOfSubSystems()*lengthOfSubSystems+ lightBar + lightBar/4;
         newSystem.setPrefSize(width, height);
         for (int i = 0 ; i < newSystem.numberOfSubSystems ; i++){
             ViewOfSubSystem temp = makeViewOfSubSystem(newSystem ,system , system.getSubSystems().get(i),i);
             newSystem.SubSystems.add(temp);
             newSystem.getChildren().add(temp);
         }
         return newSystem;
    }
    public ViewOfSubSystem makeViewOfSubSystem( SystemView newSystem,Systems system , SubSystem subSystem , int i) {
        ViewOfSubSystem newSubSystem = new ViewOfSubSystem(width - distanceOFRight*2 , lengthOfSubSystems);
        newSubSystem.doesItHaveEnterGate = subSystem.DoesItHaveEnterGate();
        newSubSystem.doesItHavaExitGate = subSystem.DoesItHavaExitGate();
        newSubSystem.setLayoutX(distanceOFRight);
        newSubSystem.setLayoutY(lightBar + i * lengthOfSubSystems);
        addThingsToSystems(system , newSubSystem , subSystem , newSystem , i);
        return newSubSystem;
    }
    public void addThingsToSystems(Systems system , ViewOfSubSystem newSubSystem , SubSystem subSystem , SystemView currentSystem , int i) {
        if (newSubSystem.doesItHavaExitGate){
            newSubSystem.ExitGate = subSystem.getExitGate();
            newSubSystem.ExitGate.setParent(currentSystem);
            Shape Port = newSubSystem.ExitGate.createShape();
            Port.setScaleX(2);
            Port.setScaleY(2);
            Port.setLayoutX(width - distanceOFRight);
            Port.setLayoutY(lightBar + i * lengthOfSubSystems + lengthOfSubSystems/2);
            Port.setUserData(newSubSystem.ExitGate);
            newSubSystem.ExitPort = Port;
            currentSystem.getChildren().add(Port);
            manager.registerExitGate(Port ,currentSystem , i , newSubSystem.ExitGate);
        }
        if (newSubSystem.doesItHaveEnterGate){
            newSubSystem.EnterGate = subSystem.getEnterGate();
            newSubSystem.EnterGate.setParent(currentSystem);
            Shape Port = newSubSystem.EnterGate.createShape();
            Port.setScaleX(2);
            Port.setScaleY(2);
            Port.setLayoutX(distanceOFRight - 5);
            Port.setLayoutY(lightBar + i * lengthOfSubSystems + lengthOfSubSystems/2);
            Port.setUserData(newSubSystem.EnterGate);
            newSubSystem.EnterPort = Port;
            currentSystem.getChildren().add(Port);
            manager.registerEnterGate(Port ,currentSystem , i , newSubSystem.EnterGate);
        }
    }
}
