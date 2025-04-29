package com.example.phaze1.model.SystemsInfo;

import com.example.phaze1.model.constants;
import com.example.phaze1.model.LevelLoader;
import com.example.phaze1.model.SubSystem;
import com.example.phaze1.model.Systems;
import com.example.phaze1.model.level;
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
    public BringItOn(Pane LineContainer) {
        manager = new GateConnectorManager(LineContainer , constants.getWireManager());
        constants.setPortInfo(manager.GateInfo());
        constants.setConnections(manager.getConnections());
        constants.setExitConnections(manager.getExitConnections());
    }
    public ArrayList<SystemView> makingEachSystems(int level) throws IOException {
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.setLevel(level);
        currentLevel = levelLoader.loadingCurrentLevel();
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
         newSystem.systemID = system.getSystemID();
         newSystem.isItFinisherSystem = system.isItFinisherSystem();
         newSystem.isItStartSystem = system.isItStartSystem();
         newSystem.numberOfSubSystems = system.getNumberOfSubSystems();
         newSystem.isTheLightOn = system.isTheLightOn();
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
            currentSystem.getChildren().add(Port);
            manager.registerEnterGate(Port ,currentSystem , i , newSubSystem.EnterGate);
        }
    }
}
