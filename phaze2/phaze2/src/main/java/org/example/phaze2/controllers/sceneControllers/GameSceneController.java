package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.bringingLevelToReality.SystemVisualizer;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.SystemView;
import org.example.phaze2.model.portConnectingDetails.ConnectionHandler;
import org.example.phaze2.model.sceneModel.GameModel;
import org.example.phaze2.model.controllersInterfaces.Initializer;

import java.util.List;

public class GameSceneController implements Initializer, ControlledScreen , DataReceivingController<GoingToGamaInformation> {
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
    public void initialize() {
        MenuButton.setOnAction(event -> {
            menuButtonClicked();
        });
        StartButton.setOnAction(event -> {
            startButtonClicked();
        });


        connectionUI = new ConnectionUI(ContainerPane , gameModel.getLevelInformation().getWireManager());
        systemVisualizer = new SystemVisualizer(gameModel.getLevelInformation().getFirstUnAvaialbleLevel() , connectionUI);
        List<Pocket> pockets = systemVisualizer.getPockets();
        List<SystemView> systemViews = systemVisualizer.getSystemViews();
        addingShapes(systemViews , pockets);




    }
    void menuButtonClicked() {
        gameModel.MenuButtonClicked();
    }
    void startButtonClicked() {
        gameModel.StartButtonClicked();
    }

    @Override
    public void initData(GoingToGamaInformation data) {
        gameModel.setLevelInformation(data);

    }

    private void addingShapes(List<SystemView> systemViews , List<Pocket> pockets) {

        for (Pocket pocket : pockets) {
            ContainerPane.getChildren().add(pocket);
        }
        for (SystemView systemView : systemViews) {
            ContainerPane.getChildren().add(systemView);
        }
    }
}
