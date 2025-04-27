package com.example.phaze1.controllers;
import com.example.phaze1.model.bringingLevelToReality;
import com.example.phaze1.model.constants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    private ArrayList<Pane>  systems =  new ArrayList<>();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane LinePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bringingLevelToReality LoadingSystems = new bringingLevelToReality(LinePane);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
            System.out.println(constants.getGateConnectorManager().getConnections().size());
        }));
        timeline.setCycleCount(-1);
        timeline.play();
        try {
            systems= LoadingSystems.makingEachSystems(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Pane system : systems){
            mainPane.getChildren().add(system);
        }
    }
}
