package com.example.phaze1.controllers;
import com.example.phaze1.controllers.ControllingPocketMovement.MakingMovements;
import com.example.phaze1.Model.SystemsInfo.BringItOn;
import com.example.phaze1.Model.SystemsInfo.SystemView;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    private ArrayList<SystemView>  systems =  new ArrayList<>();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane LinePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        BringItOn dd = new BringItOn(LinePane);

        try {
            systems= dd.makingEachSystems(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (SystemView system : systems){
            mainPane.getChildren().add(system);
        }
        MakingMovements m = new MakingMovements(LinePane , systems);
        try {
            m.goForPocketMovement();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event -> {
            m.StartMovement();
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }
}
