package com.example.phaze1.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class bringingLevelToReality {
    private Pane LineContainer;
    GateConnectorManager gateConnectorManager;
    public bringingLevelToReality(Pane LineContainer) {
        this.LineContainer = LineContainer;
        gateConnectorManager = new GateConnectorManager(LineContainer , constants.getWireManager());
    }

    private level currentLevel;
    Pane eachSystem;
    public level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(level currentLevel) {
        this.currentLevel = currentLevel;
    }
    public ArrayList<Pane> makingEachSystems(int level) throws IOException {
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
        Pane pane = new Pane();
        double LightBar = 20;
        double StrokeOfSubsystems = 40;
        double height = system.getNumberOfSubSystems()*StrokeOfSubsystems + LightBar;
        double width = 100;
        double distanceOfMainRectangle = 20;
        double distanceOfSubRectangle = 30;

        pane.setPrefSize(width, height);
        pane.setLayoutX(system.getX());
        pane.setLayoutY(system.getY());
        getEachSystem(pane ,system , height , width , distanceOfMainRectangle , distanceOfSubRectangle , StrokeOfSubsystems , LightBar);
        return pane;
    }
    public void getEachSystem(Pane pane ,Systems system , double height , double width , double distanceOfMainRectangle , double distanceOfSubRectangle , double StrokeOfSubSystems , double LightBar){
        Rectangle rectangle = new Rectangle(width -distanceOfMainRectangle , height);
        rectangle.setLayoutX(distanceOfMainRectangle/2);
        Rectangle lightRectangle = new Rectangle(width/4 , LightBar/4);
        lightRectangle.setLayoutX(distanceOfSubRectangle/2 + LightBar/4);
        lightRectangle.setLayoutY(6);
        lightRectangle.setFill(Color.LIGHTBLUE);
        lightRectangle.setArcWidth(10);
        lightRectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        rectangle.setFill(Color.GRAY);

        pane.getChildren().add(rectangle);
        pane.getChildren().add(lightRectangle);
        for (int i = 0; i < system.getNumberOfSubSystems(); i++) {
            Rectangle subSystem = new Rectangle(width - distanceOfSubRectangle , StrokeOfSubSystems);
            rectangle.setArcWidth(5);
            rectangle.setArcHeight(5);
            subSystem.setLayoutX(distanceOfSubRectangle/2);
            subSystem.setLayoutY(LightBar -5  + StrokeOfSubSystems * i );
            addingFinalThingsTOSubSystems(pane , system.getSubSystems().get(i) , i , distanceOfSubRectangle , StrokeOfSubSystems , LightBar);
            pane.getChildren().add(subSystem);
        }

    }
    public void addingFinalThingsTOSubSystems(Pane pane , SubSystem subSystem , int i  , double distanceOfSubRectangle , double StrokeOfSubSystems , double LightBar ){
        if (subSystem.DoesItHaveEnterGate()){
            Shape EnterGate = subSystem.getEnterGate().createShape();
            EnterGate.setUserData(subSystem.getEnterGate());
            EnterGate.setLayoutX(distanceOfSubRectangle/2 - 5);
            EnterGate.setLayoutY(LightBar-5 + i * StrokeOfSubSystems + StrokeOfSubSystems/2);
            EnterGate.setScaleX(2);
            EnterGate.setScaleY(2);
            gateConnectorManager.registerEnterGate(EnterGate);
            pane.getChildren().add(EnterGate);
            EnterGate.toFront();
        }
        if (subSystem.DoesItHavaExitGate()){
            Shape ExitGate = subSystem.getExitGate().createShape();
            ExitGate.setUserData(subSystem.getExitGate());
            ExitGate.setLayoutX(100 - distanceOfSubRectangle/2 );
            ExitGate.setLayoutY(LightBar-5 + i * StrokeOfSubSystems + StrokeOfSubSystems/2);
            ExitGate.setScaleX(2);
            ExitGate.setScaleY(2);
            gateConnectorManager.registerExitGate(ExitGate);
            pane.getChildren().add(ExitGate);
        }
    }






}
