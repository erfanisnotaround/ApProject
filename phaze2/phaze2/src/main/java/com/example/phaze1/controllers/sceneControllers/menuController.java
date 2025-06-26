package com.example.phaze1.controllers.sceneControllers;
import com.example.phaze1.model.MenuModel;
import com.example.phaze1.model.tasks.MenuButtonTasks;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    MenuModel menuModel;
    @FXML
    private Button StartButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Button SettingsButton;
    @FXML
    private Button LevelsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StartButton.setOnAction(event -> {
            try {
                StartButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        LevelsButton.setOnAction(event -> {
            try {
                LevelsButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        SettingsButton.setOnAction(event -> {
            try {
                SettingsButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ExitButton.setOnAction(event -> {
            try {
                ExitButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void StartButtonClicked() throws IOException {
        menuModel.StartButtonClicked();
    }
    public void ExitButtonClicked() throws IOException {
        menuModel.ExitButtonClicked();
    }
    public void SettingsButtonClicked() throws IOException {
        menuModel.SettingsButtonClicked();
    }
    public void LevelsButtonClicked() throws IOException {
        menuModel.LevelsButtonClicked();
    }
}
