package com.example.phaze1.controllers;
import com.example.phaze1.controllers.ControllingPocketMovement.MakingMovements;
import com.example.phaze1.model.SystemsInfo.BringItOn;
import com.example.phaze1.model.SystemsInfo.SystemView;
import com.example.phaze1.model.FormerVersionOSystems.bringingLevelToReality;
import com.example.phaze1.model.constants;

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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event -> {
            System.out.println(constants.getExitConnections().size());
            try {
                m.goForPocketMovement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }
}
