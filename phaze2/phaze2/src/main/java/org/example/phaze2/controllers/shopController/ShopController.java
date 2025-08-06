package org.example.phaze2.controllers.shopController;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.phaze2.controllers.abilityManagers.AbilityManager;
import org.example.phaze2.controllers.abilityManagers.abilityKeys.ShopButton;
import org.example.phaze2.model.constants.SceneActions;

import java.util.ArrayList;
import java.util.List;

public class ShopController {
    private final ShopAdder ShopAdder = new ShopAdder();

    private Stage OwnerStage;
    private Pane ActionHandlerPane;

    private final Stage shop;
    private final List<ShopButton> items;


    private AbilityManager abilityManager;

    public ShopController(Stage OwnerStage, Pane ActionHandlerPane , AbilityManager abilityManager ) {
        this.abilityManager = abilityManager;
        this.OwnerStage = OwnerStage;
        shop = ShopAdder.addStage(OwnerStage , ActionHandlerPane);
        items = ShopAdder.getItems();
        this.ActionHandlerPane = ActionHandlerPane;

        putStageHandler();
        OpeningShops();

    }

    public void OpeningShops(){
        for (ShopButton button : items){
            button.setOnAction(e -> {
                abilityManager.ExecuteAbility(button.getAbilityType());
                Platform.runLater(() -> {
                    ActionHandlerPane.requestFocus();
                });
            });
        }
    }

    private void putStageHandler(){
        Scene ownerScene = shop.getScene();

        EventHandler<KeyEvent> hotKeys = evt -> {

            if (evt.getCode() == SceneActions.CloseShop.getKeyCode()) {
                CloseShop();

            }
            if (evt.getCode() == SceneActions.Release_Follower.getKeyCode()) {
                abilityManager.getGameContext().getFollowerSpawner().ReleaseFollower();
            }
        };

        ownerScene.addEventHandler(KeyEvent.KEY_PRESSED ,  hotKeys);

    }

    public void OpenShop(){

        shop.show();
    }
    public void CloseShop(){

        shop.close();


    }


    public void ShopButtonClicked() {

    }
}
