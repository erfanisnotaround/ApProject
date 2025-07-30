package org.example.phaze2.controllers.sceneControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.phaze2.controllers.collisionAndWinning.CollisionMaker;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.SceneActions;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.saversOfGame.SaveAndLoadController;
import org.example.phaze2.viewRelated.bringingLevelToReality.SystemVisualizer;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.sceneModel.GameModel;
import org.example.phaze2.model.controllersInterfaces.Maker;

import java.util.List;

public class GameSceneController implements Maker, ControlledScreen , DataReceivingController<GoingToGamaInformation> {
    private SceneManager sceneManager;
    private GameModel gameModel = new GameModel();
    private SystemVisualizer systemVisualizer;
    private ConnectionUI connectionUI;
    CollisionMaker collisionManager = new CollisionMaker();
    WholeMovement movementMaker = new WholeMovement();
    SaveAndLoadController saveAndLoadController = new SaveAndLoadController();

    private Scene scene;

    @FXML
    private Slider SliderOfTemporalProgress;
    @FXML
    private Button MenuButton;
    @FXML
    private Button StartButton;
    @FXML
    private Pane ContainerPane;
    @FXML
    private Pane main;
    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameModel.setSceneManager(sceneManager);
    }


    @Override
    public void MakeFirst() {
        Constants.getInstance().container = ContainerPane;
        System.out.println(21);
        Constants.getInstance().getPockets().clear();
        connectionUI = new ConnectionUI(ContainerPane , gameModel.getLevelInformation().getWireManager());
        systemVisualizer = new SystemVisualizer(gameModel.getLevelInformation().getFirstUnAvaialbleLevel() , connectionUI);
        List<PocketMain> pockets = systemVisualizer.getPockets();
        List<SystemView> systemViews = systemVisualizer.getSystemViews();


        Constants.getInstance().getSystemViews().addAll(systemViews);
        Constants.getInstance().getPockets().addAll(pockets);



        addingShapes(systemViews , pockets);



        MenuButton.setOnAction(event -> {
            menuButtonClicked();
        });

        StartButton.setOnAction(event -> {
            startButtonClicked();
        });

        SliderOfTemporalProgress.valueProperty().addListener((observable, oldValue, newValue) -> {
            chooseTheDestinationTimeOfTemporal(newValue.doubleValue());
        });

        SliderOfTemporalProgress.setMax(4000);

        main.setFocusTraversable(true);
        main.requestFocus();

        main.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode pressedKey = event.getCode();

            for (SceneActions action : SceneActions.values()) {
                if (action.getKeyCode() == pressedKey) {
                    handleAction(action);
                    break;
                }
            }
        });






        collisionManager.Start();
        saveAndLoadController.startAutoSave(gameModel.getChosenLevel());


    }
    void handleAction(SceneActions action) {
        switch (action) {
            case StartTempo -> movementMaker.StartSending( gameModel.getBasicTempoMultiplier() ,gameModel.getAvailableNeededTime());
            case OpenShop -> System.out.println("Opening shop...");
            case CloseShop -> System.out.println("Closing shop...");
            case MoveSliderToRight -> moveTempo(1);
            case MoveSliderToLeft -> moveTempo(-1);
        }
    }
    void moveTempo (double forward) {
        double Step = forward * gameModel.getMoveUnitOfSlider();
        SliderOfTemporalProgress.setValue(SliderOfTemporalProgress.getValue() + Step );
    }
    void menuButtonClicked() {
        gameModel.MenuButtonClicked();
        saveAndLoadController.writeLevelsToDisk();
//        collisionManager.stop();
    }
    void startButtonClicked() {
        main.requestFocus();
        gameModel.StartButtonClicked();
        movementMaker.StartSending(gameModel.getBasicMoveMultiplier() , gameModel.getAvailableNeededTime());
    }
    void chooseTheDestinationTimeOfTemporal(double time){
        main.requestFocus();
        gameModel.SetAvailableNeededTime(time);
    }

    @Override
    public void initData(GoingToGamaInformation data) {
        System.out.println(1);

        ContainerPane.getChildren().clear();
        gameModel.setLevelInformation(data);

    }

    private void addingShapes(List<SystemView> systemViews , List<PocketMain> pockets) {

        for (SystemView systemView : systemViews) {
            ContainerPane.getChildren().addFirst(systemView);
        }
        for (Pocket pocket : pockets) {
            ContainerPane.getChildren().addFirst(pocket);
            pocket.setLayoutX(-1000);
        }
    }
}
