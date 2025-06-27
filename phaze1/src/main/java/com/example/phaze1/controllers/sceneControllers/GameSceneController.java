package com.example.phaze1.controllers.sceneControllers;
import com.example.phaze1.model.agents.GraphicAgent;
import com.example.phaze1.model.agents.mediaAgent;
import com.example.phaze1.model.constants.GameStatus;
import com.example.phaze1.model.constants.SceneActions;
import com.example.phaze1.model.systemsInfoAndManagers.*;
import com.example.phaze1.controllers.controllingPocketMovement.*;
import com.example.phaze1.model.constants.constants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class GameSceneController  {

    abilityManager shopManager;
    private double timeChosen = 0;
    TemporalProgressManager temporalProgressManager;
    private CollisionsDetection collisionsDetector;
    private ArrayList<SystemView>  systems =  new ArrayList<>();
    @FXML
    private Label LevelShower;
    @FXML
    private Label WireLeftShower;
    @FXML
    private Label coinsShower;
    @FXML
    private Label PocketLossShower ;
    @FXML
    private ImageView backGround;
    @FXML
    private Slider PrograssSlider;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane LinePane;
    @FXML
    private Pane gamePane;
    private int currentLevel;
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        constants.setLevel(currentLevel);
        LevelShower.setText(String.valueOf(currentLevel));
    }
    public void initialize() {
        MediaView mediaView = new MediaView();
        mediaAgent player = new mediaAgent("D:/music/11 The Beatles - Yesterday (Remastered 2015).mp3" ,mediaView);
        player.setAudioVolume();
        player.Play();
        Button startButton = new Button("Start");
        mainPane.getChildren().add(startButton);
        BringItOn dd = new BringItOn(gamePane);
        try {
            systems= dd.makingEachSystems(currentLevel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gamePane.getChildren().clear();
        for (SystemView system : systems){
            gamePane.getChildren().add(system);
        }
        mainPane.getChildren().add(mediaView);
        WinnerMethod winnerMethod = new WinnerMethod();
        winnerMethod.ListeningToWinningPockets(player);
        PocketLoss tt = new PocketLoss(LinePane , player);
        tt.removeWastedPockets();
        tt.pocketLossProperty().addListener((observable, oldValue, newValue) -> {
            PocketLossShower.setText(newValue.toString());
        });
        MakingMovements m = new MakingMovements(LinePane , systems);
        startButton.setOnAction(event -> {
            try {

                coinsShower.setText("0");
                tt.resetPocketLoss();
                m.test();
                constants.setCouldWeUseGameOver(true);
                m.goForPocketMovement(250, constants.getAvailableTime());
                if (canWeStart()){
                    m.goForPocketMovement(250, constants.getAvailableTime());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        MakingMovements mm = new MakingMovements(LinePane , systems);
        mm.test();
        temporalProgressManager = new TemporalProgressManager(mm);
        PrograssSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            timeChosen = newValue.doubleValue();
        });
        mainPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == SceneActions.StartTempo.getKeyCode()) {
                try {
                    coinsShower.setText("0");
                    tt.resetPocketLoss();
                    m.test();
                    constants.setCouldWeUseGameOver(false);
                    temporalProgressManager.basicsOfSending( 1500
                            , (timeChosen/PrograssSlider.getMax()*constants.getAvailableTime()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (event.getCode() == SceneActions.MoveSliderToRight.getKeyCode()) {
                PrograssSlider.setValue(PrograssSlider.getValue() + 0.005);
            }
            else if (event.getCode() == SceneActions.MoveSliderToLeft.getKeyCode()) {
                PrograssSlider.setValue(PrograssSlider.getValue() - 0.005);
            }
        });
        WireLeftShower.setText(String.valueOf(constants.getWireManager().usedLengthPropertyProperty().get()));
        constants.getWireManager().usedLengthPropertyProperty().addListener((observable, oldValue, newValue) -> {
            WireLeftShower.setText(String.valueOf(newValue.intValue())+".0");
        });
        for (Pocket p : constants.getPockets()){
            p.coinsProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.intValue()>oldValue.intValue()){
                    int coins = Integer.parseInt(coinsShower.getText());
                    coins++;
                    coinsShower.setText(String.valueOf(coins));
                }
            });
        }
        Button shop = new Button("Menu");
        mainPane.getChildren().add(shop);
        shop.setOnAction(event -> {
            GraphicAgent ff = GraphicAgent.getInstance();
            ff.switchSceneForState(GameStatus.MENU);
        });
        shop.setLayoutX(800);
        shopManager = new abilityManager(Integer.parseInt(coinsShower.getText()) , coinsShower , constants.getPockets());
        mainPane.setOnKeyPressed(event -> {
            if (event.getCode() == SceneActions.OpenShop.getKeyCode()) {
                player.Stop();
                shopManager.OpenShop();
                constants.setStopped(true);
            }
        });

        constants.makeEveryPocketNoiseZeroProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                makeEveryPocketNOiseZero();
                constants.setMakeEveryPocketNoiseZero(false);
            }
        });


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            CheckingSystemsForLight();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }
    public void makeEveryPocketNOiseZero() {
        for (Pocket p : constants.getPockets()){
            if (!p.isIsLost()){
                p.setHP(p.getMaxHP());
                p.setDistanceFromTheLine(0);
            }
        }
    }
    public void CheckingSystemsForLight(){
        for (SystemView system : systems){
            if (eachSystemCheckLight(system)){
                system.lightBoolean.set(true);
            }
            else {
                system.lightBoolean.set(false);
            }
        }
    }
    public boolean eachSystemCheckLight(SystemView system){
        Map<Node , GatePortInfo> portInfoMap = constants.getPortInfo();
        Map<GatePortInfo , Connection> connectionMap = constants.getExitConnections();
        Node ExitGate = null;
        Node EnterGate = null;
        for (ViewOfSubSystem subSystem : system.SubSystems){
            if (subSystem.doesItHavaExitGate){
                ExitGate = subSystem.ExitPort;
                if (!connectionMap.containsKey(portInfoMap.get(ExitGate))){
                    return false;
                }
            }
            if (subSystem.doesItHaveEnterGate){
                EnterGate = subSystem.EnterPort;
                if (!connectionMap.containsKey(portInfoMap.get(EnterGate))){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean canWeStart(){
        for (SystemView system : systems){
            if (!system.lightBoolean.get())return false;
        }
        return true;
    }

}