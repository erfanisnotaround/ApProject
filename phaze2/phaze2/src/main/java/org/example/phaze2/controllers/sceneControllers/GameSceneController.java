package org.example.phaze2.controllers.sceneControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.moverController.WholeMovement;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.viewRelated.bringingLevelToReality.SystemVisualizer;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.sceneModel.GameModel;
import org.example.phaze2.model.controllersInterfaces.Maker;

import java.util.List;

public class GameSceneController implements Maker, ControlledScreen , DataReceivingController<GoingToGamaInformation> {
    private WholeMovement movementMaker;
    private SceneManager sceneManager;
    private GameModel gameModel = new GameModel();
    private SystemVisualizer systemVisualizer;
    private ConnectionUI connectionUI;
    @FXML
    private Button MenuButton;
    @FXML
    private Button StartButton;
    @FXML
    private Pane ContainerPane;
    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameModel.setSceneManager(sceneManager);
    }


    @Override
    public void MakeFirst() {
        System.out.println(21);


        connectionUI = new ConnectionUI(ContainerPane , gameModel.getLevelInformation().getWireManager());
        systemVisualizer = new SystemVisualizer(gameModel.getLevelInformation().getFirstUnAvaialbleLevel() , connectionUI);
        List<Pocket> pockets = systemVisualizer.getPockets();
        List<SystemView> systemViews = systemVisualizer.getSystemViews();


        Constants.getInstance().getSystemViews().addAll(systemViews);
        Constants.getInstance().getPockets().addAll(pockets);
        addingShapes(systemViews , pockets);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(14)));
        timeline.setCycleCount(1);
//        timeline.play();
        timeline.setOnFinished(e ->{
            pockets.getFirst().move(Constants.getInstance().getConnections().getFirst().getCurve());
            pockets.getLast().move(Constants.getInstance().getConnections().getFirst().getCurve());
        });
        movementMaker = new WholeMovement();

        MenuButton.setOnAction(event -> {
            menuButtonClicked();
        });
        StartButton.setOnAction(event -> {
            startButtonClicked();
            movementMaker.StartSending();
        });




    }
    void menuButtonClicked() {
        gameModel.MenuButtonClicked();
    }
    void startButtonClicked() {
        gameModel.StartButtonClicked();
    }

    @Override
    public void initData(GoingToGamaInformation data) {
        System.out.println(1);

        ContainerPane.getChildren().clear();
        gameModel.setLevelInformation(data);



    }

    private void addingShapes(List<SystemView> systemViews , List<Pocket> pockets) {

        for (SystemView systemView : systemViews) {
            ContainerPane.getChildren().addFirst(systemView);
        }
        for (Pocket pocket : pockets) {
            ContainerPane.getChildren().addFirst(pocket);
        }
    }
}
