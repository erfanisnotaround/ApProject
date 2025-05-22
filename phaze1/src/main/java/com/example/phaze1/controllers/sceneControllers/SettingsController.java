package com.example.phaze1.controllers.sceneControllers;

import com.example.phaze1.model.agents.mediaAgent;
import com.example.phaze1.model.constants.SceneActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private Stage stage;

    @FXML private Slider volumeSlider;
    @FXML private Label  volumeDouble;
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

    @FXML private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volumeSlider.setValue(mediaAgent.getVolumeLevel()*100);
        volumeDouble.setText(String.format("%.2f", volumeSlider.getValue()));
        volumeSlider.valueProperty().addListener((obs, o, n) -> {
            volumeDouble.setText(String.format("%.2f", n.doubleValue()));
            mediaAgent.setVolumeLevel(n.doubleValue()/100);
        });

        startTempoField.setText(SceneActions.StartTempo.getKeyCode().getName());
        openShopField .setText(SceneActions.OpenShop .getKeyCode().getName());
        closeShopField.setText(SceneActions.CloseShop.getKeyCode().getName());
        moveRightField.setText(SceneActions.MoveSliderToRight.getKeyCode().getName());
        moveLeftField .setText(SceneActions.MoveSliderToLeft.getKeyCode().getName());

        changeStartTempo.setOnAction(e -> awaitKeyFor(SceneActions.StartTempo, startTempoField));
        changeOpenShop .setOnAction(e -> awaitKeyFor(SceneActions.OpenShop , openShopField));
        changeCloseShop.setOnAction(e -> awaitKeyFor(SceneActions.CloseShop, closeShopField));
        changeMoveRight.setOnAction(e -> awaitKeyFor(SceneActions.MoveSliderToRight, moveRightField));
        changeMoveLeft .setOnAction(e -> awaitKeyFor(SceneActions.MoveSliderToLeft, moveLeftField));

        back.setOnAction(e -> {
            if (stage != null) stage.close();
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void awaitKeyFor(SceneActions action, TextField field) {
        field.setText("Press a key…");
        Scene scene = stage.getScene();
        javafx.event.EventHandler<KeyEvent> handler = new javafx.event.EventHandler<>() {
            @Override
            public void handle(KeyEvent evt) {
                KeyCode code = evt.getCode();
                action.setKeyCode(code);
                field.setText(code.getName());
                scene.removeEventHandler(KeyEvent.KEY_PRESSED, this);
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }
}
