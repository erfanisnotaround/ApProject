package com.example.phaze1.Model.SystemsInfoAndManagers;

import com.example.phaze1.Model.Constants.constants;
import com.example.phaze1.Model.levelLoadingStuff.LevelLoader;
import com.example.phaze1.Model.levelLoadingStuff.SubSystem;
import com.example.phaze1.Model.levelLoadingStuff.Systems;
import com.example.phaze1.Model.levelLoadingStuff.level;
import com.example.phaze1.Model.levelLoadingStuff.pockets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;

public class BringItOn {
    GateConnectorManager manager;
    private final double lengthOfSubSystems = 40;
    private final double width = 100;
    private double height = 0;
    private final double lightBar = 30;
    private final double distanceOFRight = 8;
    private  level currentLevel;
    private final ArrayList<Pocket> Pockets = new ArrayList<>();
    private ArrayList<pockets> pocketsInformation;
    Pane LineContainer;
    public BringItOn(Pane LineContainer) {
        this.LineContainer = LineContainer;

    }
    public ArrayList<SystemView> makingEachSystems(int level) throws IOException {
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.setLevel(level);
        currentLevel = levelLoader.loadingCurrentLevel();
        pocketsInformation = currentLevel.getPockets();
        manager = new GateConnectorManager(LineContainer , constants.getWireManager());
        constants.setPortInfo(manager.GateInfo());
        constants.setConnections(manager.getConnections());
        constants.setExitConnections(manager.getExitConnections());
        ArrayList<SystemView> systems = new ArrayList<>();
        for (Systems system : currentLevel.getSystems()){
            systems.add(makeASystem(system));
        }
        settingPockets();
        return systems;
    }
    public SystemView makeASystem(Systems system) {
         SystemView newSystem = new SystemView();
         newSystem.setLayoutX(system.getX());
         newSystem.setLayoutY(system.getY());
         newSystem.x = system.getX();
         newSystem.y = system.getY();
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
    public void settingPockets() {
        System.out.println(pocketsInformation.size());
        for (pockets p : pocketsInformation){
            Pocket newPocket = p.getType().createShape();
            newPocket.setScaleX(2);
            newPocket.setScaleY(2);
            newPocket.setDelay(p.getDelay());
            newPocket.setType(p.getType());
            newPocket.setWhichSubSystem(p.getWhichSubSystem());
            Pockets.add(newPocket);
            LineContainer.getChildren().add(newPocket);
            newPocket.toFront();
        }
        constants.setPockets(Pockets);
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
            Shape port = newSubSystem.ExitGate.createShape();
            port.setScaleX(2);
            port.setScaleY(2);
            port.setLayoutX(width - distanceOFRight);
            port.setLayoutY(lightBar + i * lengthOfSubSystems + lengthOfSubSystems/2);
            newSubSystem.ExitPort = port;
            currentSystem.getChildren().add(port);
            manager.registerExitGate(port ,currentSystem , i , newSubSystem.ExitGate);
        }
        if (newSubSystem.doesItHaveEnterGate){
            newSubSystem.EnterGate = subSystem.getEnterGate();
            Shape port = newSubSystem.EnterGate.createShape();
            port.setScaleX(2);
            port.setScaleY(2);
            port.setLayoutX(distanceOFRight - 5);
            port.setLayoutY(lightBar + i * lengthOfSubSystems + lengthOfSubSystems/2);
            port.setUserData(newSubSystem.EnterGate);
            newSubSystem.EnterPort = port;
            currentSystem.getChildren().add(port);
            manager.registerEnterGate(port ,currentSystem , i , newSubSystem.EnterGate);
        }
    }
}
