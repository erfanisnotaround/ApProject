// GameWiring.java
package org.example.phaze2.controllers.sceneControllers;

import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.abilityManagers.AbilityManager;
import org.example.phaze2.controllers.abilityManagers.following.FollowerAdder;
import org.example.phaze2.controllers.collisionAndWinning.CollisionMaker;
import org.example.phaze2.controllers.connectionsAndMaking.ConnectionUI;
import org.example.phaze2.controllers.hudChangeListeneres.HudListener;
import org.example.phaze2.controllers.moverController.checkings.AllMovingAndReadyCheckers;
import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.ResetServiceImpl;
import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.ResetService;
import org.example.phaze2.controllers.shopController.ShopController;
import org.example.phaze2.controllers.winAndPocketLoss.*;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.hudModels.BasicTransfer;
import org.example.phaze2.model.hudModels.CoinsManager;
import org.example.phaze2.model.hudModels.NumberOfPocketLossManager;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelSavesAndTheirPojo.AfterPreShow;
import org.example.phaze2.model.sceneModel.GameModel;
import org.example.phaze2.model.saversOfGame.LoadAndSaveCompleterNecessaries;
import org.example.phaze2.model.saversOfGame.SaveAndLoadController;
import org.example.phaze2.model.winAndPocketLossModel.DefaultGameDataProvider;
import org.example.phaze2.model.winAndPocketLossModel.GameOverType;
import org.example.phaze2.model.winAndPocketLossModel.PocketLossCondition;
import org.example.phaze2.model.winAndPocketLossModel.WinCondition;
import org.example.phaze2.viewRelated.bringingLevelToReality.SystemVisualizer;
import org.example.phaze2.viewRelated.hudView.MakeHUD;

import java.util.List;

public final class GameWiring {

    public record Result(
            WholeMovement movement,
            BackgroundConditionScheduler scheduler,
            GameOverEvaluator evaluator,
            SystemVisualizer systemVisualizer,
            ConnectionUI connectionUI,
            FollowerAdder followerAdder,
            ShopController shopController,
            SaveAndLoadController saveAndLoad,
            HudListener hudListener,
            CollisionMaker engine,
            ResetService resetService,
            AfterPreShow afterPreShow
    ) {}

    public static Result bootstrap(GameModel model, GameState gs,
                                   Pane container, Pane main, Pane hud,
                                   SceneManager sceneManager,
                                   NumberOfPocketLossManager lossMgr) {

        // 0) Visual shells (view/repo/effect/splitter/merger/policy)
        VisualShellConfigurator.configure(gs, container);
        ResetService resetService = new ResetServiceImpl(gs);

        // 1) HUD + Constants
        MakeHUD makeHUD = new MakeHUD(hud, 3, 6);
        makeHUD.makeHUD();
        hud.setVisible(false);
        hud.setMouseTransparent(true);
        hud.setFocusTraversable(false);

        CoinsManager coins = new CoinsManager();
        gs.getHudStuffDAta().setCoinsManager(coins);
        Constants.getInstance().setNumberOfPocketLossManager(lossMgr);


        HudListener hudListener = new HudListener(makeHUD, gs);
        hudListener.Start();

        // 2) Clear resources (your exact clears)
        gs.getResources().getPockets().clear();
        gs.getResources().getSystemViews().clear();
        gs.getResources().getExitConnections().clear();
        gs.getResources().getConnections().clear();
        gs.getResources().getPocketMainMap().clear();
        gs.getResources().getSystemViewMap().clear();

        // 3) Build connection UI + visualize systems & pockets
        ConnectionUI connectionUI = new ConnectionUI(container, model.getLevelInformation().getWireManager(), main, gs);
        SystemVisualizer visualizer = new SystemVisualizer(model.getLevelInformation().getFirstUnAvaialbleLevel(), connectionUI, gs);

        // Attach and index everything + set baseline seeds
        ResourcesAssembler.assemble(gs, container, visualizer, connectionUI);

        // 4) Movement + Ability + AfterPreShow
        WholeMovement movement = new WholeMovement(coins, gs);
        movement.setResetter(resetService);
        movement.captureOriginalSnapshotFromSeeds(gs.getResources().getPockets());

        FollowerAdder followerAdder = new FollowerAdder(container);
        AbilityManager abilityManager = new AbilityManager(movement, coins, followerAdder, gs);
        ShopController shopController = new ShopController(sceneManager.getStage(), main, abilityManager);

        var afterPreShow = new AfterPreShow(abilityManager, movement);
        afterPreShow.setSystemViews(gs.getResources().getSystemViews());
        afterPreShow.setPocketMains(gs.getResources().getPockets());

        // 5) Collision engine
        List<PocketMain> pockets = gs.getResources().getPockets();


        AllMovingAndReadyCheckers allMovingAndReady = new AllMovingAndReadyCheckers(gs);
        allMovingAndReady.check();

        DeadPocketPlacementStrategy lossBin = new SimpleCornerLossBin(container, 1500, 900, 32, 8);
        PocketReaper reaper = new PocketReaper(pockets, lossBin, gs);

        DefaultGameDataProvider dataProvider = new DefaultGameDataProvider(gs);
        GameOverEvaluator evaluator = new GameOverEvaluator(new FxSceneGameOverHandler(sceneManager), gs)
                .add(new PocketLossCondition(dataProvider), GameOverType.POCKET_LOSS)
                .add(new WinCondition(dataProvider),        GameOverType.WIN);

        BackgroundConditionScheduler scheduler =
                new BackgroundConditionScheduler(reaper, evaluator, new FxSceneGameOverHandler(sceneManager), gs);
        scheduler.start();

        gs.getPocketWinAndLoss().setLossBin(lossBin);
        gs.getPocketWinAndLoss().setReaper(reaper);
        gs.getPocketWinAndLoss().setEvaluator(evaluator);
        gs.getPocketWinAndLoss().setDataProvider(dataProvider);
        gs.getPocketWinAndLoss().setBgScheduler(scheduler);

        BasicTransfer basicTransfer = new BasicTransfer(gs);
        gs.getHudStuffDAta().setBasicTransfer(basicTransfer);

        SaveAndLoadController saveAndLoad = new SaveAndLoadController(
                model.getChosenLevel(),
                new LoadAndSaveCompleterNecessaries(followerAdder, connectionUI,
                        gs.getHudStuffDAta().getAbilityAliveManager(),
                        coins, abilityManager, afterPreShow, gs)
        );

        CollisionMaker engine = new CollisionMaker(pockets, gs);
        engine.start();

        return new Result(movement, scheduler, evaluator, visualizer, connectionUI,
                followerAdder, shopController, saveAndLoad, hudListener, engine , resetService , afterPreShow);
    }
}
