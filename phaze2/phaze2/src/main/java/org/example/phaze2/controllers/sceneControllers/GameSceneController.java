// GameSceneController.java  (drop-in replacement)
package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import org.example.phaze2.controllers.loadingSave.FxLoadPrompt;
import org.example.phaze2.controllers.loadingSave.LoadPrompt;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.*;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAdder;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.GameSession;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.MovementService;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.constants.SceneActions;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.controllersInterfaces.Maker;
import org.example.phaze2.model.hudModels.NumberOfPocketLossManager;
import org.example.phaze2.model.loadingSaves.LoadGameStartup;
import org.example.phaze2.model.sceneModel.GameModel;

public class GameSceneController implements Maker, ControlledScreen, DataReceivingController<GoingToGamaInformation> {
    @FXML private Slider SliderOfTemporalProgress;
    @FXML private Button MenuButton, StartButton;
    @FXML private Pane ContainerPane, main, HUD;

    private SceneManager sceneManager;
    private final GameModel gameModel = new GameModel();
    private final GameState gameState = new GameState();
    private AppContext appContext;
    private GameSession session;
    private GameWiring.Result wiring;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameModel.setSceneManager(sceneManager);
    }

    @Override
    public void initData(GoingToGamaInformation data) {
        ContainerPane.getChildren().clear();            // you had this
        gameModel.setLevelInformation(data);
        gameState.getResources().setWireManager(data.getWireManager());
    }

    @Override
    public void MakeFirst() {
        this.appContext.keyMgr().syncSceneActionsFromSettings();
        wiring = GameWiring.bootstrap(
                gameModel, gameState, ContainerPane, main, HUD, sceneManager,
                new NumberOfPocketLossManager()
        );

        MovementService movementSvc = new WholeMovementService(wiring.movement());

        session = new GameSessionImpl(movementSvc, wiring.resetService(),
                wiring.scheduler(), wiring.evaluator() , wiring.movement().getMovementListeners());

        LoadPrompt loadPrompt  = new FxLoadPrompt(sceneManager.getStage());
        LoadGameStartup loadStartup = new LoadGameStartup(loadPrompt);
        loadStartup.maybeOfferLoadOrFresh(wiring.saveAndLoad() , wiring.afterPreShow());

        SliderOfTemporalProgress.setMax(gameModel.getWholeAvailableTIme());
        SliderOfTemporalProgress.valueProperty().addListener((obs, o, v) ->
                gameModel.SetAvailableNeededTime(v.doubleValue()));

        MenuButton.setOnAction(e -> {
            gameModel.MenuButtonClicked();

        });

        StartButton.setOnAction(e -> {
            main.requestFocus();
            session.startFresh(gameModel.getBasicMoveMultiplier(), gameModel.getWholeAvailableTIme());
        });

        main.setFocusTraversable(true);
        main.requestFocus();

        Scene scene = sceneManager.getScene();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            KeyCode key = e.getCode();
            for (SceneActions action : SceneActions.values()) {
                if (action.getKeyCode() == key) {
                    e.consume();
                    handleActionPressed(action);
                    break;
                }
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            KeyCode key = e.getCode();
            for (SceneActions action : SceneActions.values()) {
                if (action.getKeyCode() == key) {
                    e.consume();
                    handleActionReleased(action);
                    break;
                }
            }
        });

        sceneManager.getScene().setOnMouseMoved(wiring.followerAdder()::FollowTheMouse);
    }

    @Override
    public void PassContext(AppContext appContext) {
        this.appContext = appContext;
    }

    private void handleActionPressed(SceneActions action) {
        main.requestFocus();
        switch (action) {
            case StartTempo -> {
                session.startFromCurrent(gameModel.getBasicTempoMultiplier(), gameModel.getAvailableNeededTime());
            }
            case OpenShop -> wiring.shopController().OpenShop();
            case CloseShop -> wiring.shopController().CloseShop();
            case MoveSliderToRight -> moveTempo(1);
            case MoveSliderToLeft -> moveTempo(-1);
            case Open_Close_HUD -> HUD.setVisible(true);
            case Release_Follower -> wiring.followerAdder().ReleaseFollower();
            case DELETE_SELECTION -> wiring.connectionUI().removeConnection();
        }
    }

    private void handleActionReleased(SceneActions action) {
        switch (action) {
            case Open_Close_HUD -> HUD.setVisible(false);         // you did this on release
            default -> { /* no-op like your prints */ }
        }
    }

    private void moveTempo(double forward) {
        double step = forward * gameModel.getMoveUnitOfSlider();
        SliderOfTemporalProgress.setValue(SliderOfTemporalProgress.getValue() + step);
    }
}
