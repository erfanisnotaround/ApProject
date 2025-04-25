package com.example.phaze1.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;

public class bringingLevelToReality {
    private Pane LineContainer;
    public bringingLevelToReality(Pane LineContainer) {
        this.LineContainer = LineContainer;
    }
    GateConnectorManager gateConnectorManager = new GateConnectorManager(LineContainer);

    private level currentLevel;
    Pane eachSystem;
    public level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(level currentLevel) {
        this.currentLevel = currentLevel;
    }
    public ArrayList<Pane> makingEachSystems(int level){
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.setLevel(level);
        currentLevel = levelLoader.loadingCurrentLevel();
        ArrayList<Pane> systems = new ArrayList<>();
        for (Systems system : currentLevel.getSystems()){
            eachSystem = makeASystem(system);
            systems.add(eachSystem);
        }
        return systems;

    }
    public Pane makeASystem(Systems system){
        Pane pane = new VBox();
        double height = system.getNumberOfSubSystems()*15 + 10;
        double width = 30;
        pane.setPrefSize(width, height);
        getEachSystem(pane ,system , height , width);
        return pane;
    }
    public void getEachSystem(Pane pane ,Systems system , double height , double width){
        Rectangle rectangle = new Rectangle(width -6 , height);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        rectangle.setFill(Color.GRAY);
        pane.getChildren().add(rectangle);
        for (int i = 0; i < system.getNumberOfSubSystems(); i++) {
            Rectangle subSystem = new Rectangle(width - 8 , 15);
            addingFinalThingsTOSubSystems(pane , system.getSubSystems().get(i) , i);
            pane.getChildren().add(subSystem);
        }

    }
    public void addingFinalThingsTOSubSystems(Pane pane , SubSystem subSystem , int i ){
        if (subSystem.DoesItHaveEnterGate()){
            Shape EnterGate = subSystem.getEnterGate().createShape();
            EnterGate.setLayoutX(5);
            EnterGate.setLayoutY(10 + i * 15 + 7.5);
            gateConnectorManager.registerEnterGate(EnterGate);
            pane.getChildren().add(EnterGate);
        }
        if (subSystem.DoesItHavaExitGate()){
            Shape ExitGate = subSystem.getExitGate().createShape();
            ExitGate.setLayoutX(25);
            ExitGate.setLayoutY(15 + i * 15 + 7.5);
            gateConnectorManager.registerExitGate(ExitGate);
            pane.getChildren().add(ExitGate);
        }
    }






}
