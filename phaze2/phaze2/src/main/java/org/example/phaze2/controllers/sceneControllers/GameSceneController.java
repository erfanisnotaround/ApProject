package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.abilityManagers.AbilityManager;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAdder;
import org.example.phaze2.controllers.collisionAndWinning.CollisionMaker;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.hudChangeListeneres.HudListener;
import org.example.phaze2.controllers.moverController.checkings.AllMovingAndReadyCheckers;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.controllers.shopController.ShopController;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SceneActions;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.hudModels.NumberOfPocketLossManager;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketViewManager;
import org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting.*;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.saversOfGame.SaveAndLoadController;
import org.example.phaze2.viewRelated.bringingLevelToReality.SystemVisualizer;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.sceneModel.GameModel;
import org.example.phaze2.model.controllersInterfaces.Maker;
import org.example.phaze2.viewRelated.hudView.MakeHUD;

import java.util.List;

public class GameSceneController implements Maker, ControlledScreen , DataReceivingController<GoingToGamaInformation> {
    private SceneManager sceneManager;
    private GameModel gameModel = new GameModel();
    private SystemVisualizer systemVisualizer;
    private ConnectionUI connectionUI;
    private CollisionMaker  engine;
    SaveAndLoadController saveAndLoadController;
    private MakeHUD makeHUD;

    private final CoinsManager coinsManager = new CoinsManager();
    private final NumberOfPocketLossManager numberOfPocketLossManager = new NumberOfPocketLossManager();
    private HudListener hudListener;


    private FollowerAdder  followerAdder;
    private AbilityManager abilityManager;

    private ShopController ShopController;
    private final GameState gameState = new GameState();
    WholeMovement movementMaker = new WholeMovement(coinsManager , gameState);
    private final AllMovingAndReadyCheckers allMovingAndReadyCheckers = new AllMovingAndReadyCheckers(gameState);

    List<PocketMain> pockets;
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
    @FXML
    private Pane HUD;
    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameModel.setSceneManager(sceneManager);
    }


    @Override
    public void MakeFirst() {


        gameState.getVisualConstant().setContainerPane(ContainerPane);



        PocketViewPort view = new PocketViewManager(gameState.getVisualConstant().getContainerPane());
        PocketRepository repo = new PocketRepository(gameState.getResources());
        PocketVisualEffect effect = new HueShiftEffect();
        BigPocketSplitter splitter = new BigPocketSplitter(view, repo, effect, gameState);

        gameState.getVisualConstant().setSplitter(splitter);
        gameState.getVisualConstant().setEffect(effect);
        gameState.getVisualConstant().setRepo(repo);
        gameState.getVisualConstant().setView(view);



        followerAdder = new FollowerAdder(ContainerPane);
        abilityManager = new AbilityManager(movementMaker , coinsManager , followerAdder ,gameState);
        ShopController = new ShopController(sceneManager.getStage() , main , abilityManager);



        Constants.getInstance().setCoinsManager(coinsManager);
        Constants.getInstance().setNumberOfPocketLossManager(numberOfPocketLossManager);

        makeHUD = new MakeHUD(HUD , 3 , 6);

        makeHUD.makeHUD();

        hudListener = new HudListener(makeHUD , gameState);


        HUD.setVisible(false);
        HUD.setMouseTransparent(true);
        HUD.setFocusTraversable(false);
        Constants.getInstance().getPockets().clear();
        Constants.getInstance().getSystemViews().clear();
        Constants.getInstance().getExitConnections().clear();
        Constants.getInstance().getConnections().clear();
        Constants.getInstance().getPocketMainMap().clear();
        Constants.getInstance().getSystemViewMap().clear();


        Constants.getInstance().getPockets().clear();
        connectionUI = new ConnectionUI(ContainerPane , gameModel.getLevelInformation().getWireManager() , main , gameState);
        systemVisualizer = new SystemVisualizer(gameModel.getLevelInformation().getFirstUnAvaialbleLevel() , connectionUI , gameState);
        pockets = systemVisualizer.getPockets();
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

        sceneManager.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode pressedKey = event.getCode();

            for (SceneActions action : SceneActions.values()) {
                if (action.getKeyCode() == pressedKey) {
                    event.consume();
                    handleActionPressed(action);
                    break;
                }
            }

        });

        sceneManager.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            KeyCode ReleasedKey = event.getCode();

            for (SceneActions action : SceneActions.values()) {
                if (action.getKeyCode() == ReleasedKey) {
                    event.consume();
                    handleActionReleased(action);
                    break;
                }
            }
        });
        sceneManager.getScene().setOnMouseMoved(followerAdder::FollowTheMouse);





        saveAndLoadController = new SaveAndLoadController(connectionUI , gameModel.getChosenLevel());

        engine = new CollisionMaker(pockets , gameState);
        engine.start();
        hudListener.Start();
        allMovingAndReadyCheckers.check();



    }
    void handleActionPressed(SceneActions action) {

         main.requestFocus();
        switch (action) {
            case StartTempo -> movementMaker.StartSending( gameModel.getBasicTempoMultiplier() ,gameModel.getAvailableNeededTime());
            case OpenShop -> {
                ShopController.OpenShop();
            }
            case CloseShop -> {
                ShopController.CloseShop();
            }
            case MoveSliderToRight -> moveTempo(1);
            case MoveSliderToLeft -> moveTempo(-1);
            case Open_Close_HUD -> {

                HUD.setVisible(true);

            }
            case Release_Follower -> followerAdder.ReleaseFollower();
            case DELETE_SELECTION -> connectionUI.removeConnection();
        }
    }
    void handleActionReleased(SceneActions action) {

        switch (action) {
            case StartTempo -> System.out.println("");
            case OpenShop -> System.out.println("");
            case CloseShop -> System.out.println("");
            case MoveSliderToRight -> System.out.println("");
            case MoveSliderToLeft -> System.out.println("");
            case Open_Close_HUD -> {
                HUD.setVisible(false);
            }
        }
    }
    void moveTempo (double forward) {
        double Step = forward * gameModel.getMoveUnitOfSlider();
        SliderOfTemporalProgress.setValue(SliderOfTemporalProgress.getValue() + Step );
    }
    void menuButtonClicked() {
        gameModel.MenuButtonClicked();
        saveAndLoadController.writeLevelsToDisk();
//        collisionManager.Stop();
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
            gameState.getResources().getSystemViews().add(systemView);
        }
        for (PocketMain pocket : pockets) {
            ContainerPane.getChildren().addLast(pocket);
            gameState.getResources().getPockets().add(pocket);
            pocket.setLayoutX(-1000);
        }
    }
    private List<PocketMain> getPockets() {
        return pockets;
    }

}
