package com.example.phaze1.controllers.sceneControllers;
import com.example.phaze1.Model.Agents.mediaAgent;
import com.example.phaze1.Model.SystemsInfoAndManagers.GatePortInfo;
import com.example.phaze1.Model.SystemsInfoAndManagers.ViewOfSubSystem;
import com.example.phaze1.controllers.ControllingPocketMovement.MakingMovements;
import com.example.phaze1.Model.SystemsInfoAndManagers.BringItOn;
import com.example.phaze1.Model.SystemsInfoAndManagers.SystemView;
import com.example.phaze1.Model.Constants.constants;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
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
        MediaView mediaView = new MediaView();
        mediaAgent player = new mediaAgent("D:/music/11 The Beatles - Yesterday (Remastered 2015).mp3" ,mediaView);
        player.Play();
        mainPane.getChildren().add(mediaView);
        Button startButton = new Button("Start");
        mainPane.getChildren().add(startButton);
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
        startButton.setOnAction(event -> {
            try {
                m.goForPocketMovement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5) , event -> {
            try {
                m.goForPocketMovement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
