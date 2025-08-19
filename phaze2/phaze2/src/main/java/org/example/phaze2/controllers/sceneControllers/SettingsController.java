// src/main/java/org/example/phaze2/controllers/sceneControllers/SettingsController.java
package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.controllersInterfaces.DataReceivingController;
import org.example.phaze2.model.settingModel.*;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.constants.SceneActions;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.Maker;
import org.example.phaze2.model.sceneModel.SettingsModel;


public class SettingsController implements Maker, ControlledScreen , DataReceivingController<SettingsData> {

    private SceneManager sceneManager;
    private final SettingsModel settingsModel = new SettingsModel();

    // Injected services (setters below)
    private SettingsService settings;
    private KeyBindingManager keyMgr;

    // ---- FXML ----
    @FXML private Button BackButton;

    @FXML private Slider volumeSlider;  // 0..100
    @FXML private Label  volumeLabel;

    @FXML private TextField startTempoField;
    @FXML private Button    changeStartTempo;

    @FXML private TextField openShopField;
    @FXML private Button    changeOpenShop;

    @FXML private TextField closeShopField;
    @FXML private Button    changeCloseShop;

    @FXML private TextField moveRightField;
    @FXML private Button    changeMoveRight;

    @FXML private TextField moveLeftField;
    @FXML private Button    changeMoveLeft;

    // ---- Wiring ----
    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        settingsModel.setSceneManager(sceneManager);
    }
    public void setSettingsService(SettingsService s) { this.settings = s; }
    public void setKeyBindingManager(KeyBindingManager k) { this.keyMgr = k; }

    @Override
    public void MakeFirst() {
        if (settings == null || keyMgr == null) {
            throw new IllegalStateException("SettingsService/KeyBindingManager not injected");
        }



        keyMgr.syncSceneActionsFromSettings();

        // Volume
        double v01 = settings.getVolume();
        volumeSlider.setValue(v01 * 100.0);
        volumeLabel.setText(String.format("%.0f%%", volumeSlider.getValue()));
        volumeSlider.valueProperty().addListener((obs, ov, nv) -> {
            double newV01 = nv.doubleValue() / 100.0;
            settings.setVolume(newV01);
            volumeLabel.setText(String.format("%.0f%%", nv.doubleValue()));
        });

        // Initial key labels
        startTempoField.setText(settings.getKey(SceneActions.StartTempo).getName());
        openShopField .setText(settings.getKey(SceneActions.OpenShop ).getName());
        closeShopField.setText(settings.getKey(SceneActions.CloseShop).getName());
        moveRightField.setText(settings.getKey(SceneActions.MoveSliderToRight).getName());
        moveLeftField .setText(settings.getKey(SceneActions.MoveSliderToLeft ).getName());

        // Rebind buttons
        changeStartTempo.setOnAction(e -> awaitKeyFor(SceneActions.StartTempo,        startTempoField));
        changeOpenShop  .setOnAction(e -> awaitKeyFor(SceneActions.OpenShop,          openShopField));
        changeCloseShop .setOnAction(e -> awaitKeyFor(SceneActions.CloseShop,         closeShopField));
        changeMoveRight .setOnAction(e -> awaitKeyFor(SceneActions.MoveSliderToRight, moveRightField));
        changeMoveLeft  .setOnAction(e -> awaitKeyFor(SceneActions.MoveSliderToLeft,  moveLeftField));

        BackButton.setOnAction(e -> {
            settings.saveNow();
            settingsModel.BackButtonClicked();
        });
    }

    @Override
    public void PassContext(AppContext appContext) {
        settings = appContext.settings();
        keyMgr = appContext.keyMgr();
    }

    private void awaitKeyFor(SceneActions action, TextField field) {
        field.setText("Press a key…");
        var scene = sceneManager.getScene();

        javafx.event.EventHandler<KeyEvent> handler = new javafx.event.EventHandler<>() {
            @Override public void handle(KeyEvent evt) {
                KeyCode code = evt.getCode();

                settings.setKey(action, code);   // persist
                action.setKeyCode(code);         // update enum now
                keyMgr.syncSceneActionsFromSettings(); // refresh reverse map

                field.setText(code.getName());
                scene.removeEventHandler(KeyEvent.KEY_PRESSED, this);
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }

    @Override
    public void initData(SettingsData data) {

    }
}
