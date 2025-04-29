package com.example.phaze1.controllers;
import com.example.phaze1.Model.menuButtonTasks;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    menuButtonTasks menuButtonTasks = new menuButtonTasks();
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
        menuButtonTasks.StartButton();
    }
    public void ExitButtonClicked() throws IOException {
        menuButtonTasks.ExitButton();
    }
    public void SettingsButtonClicked() throws IOException {
        menuButtonTasks.SettingButton();
    }
    public void LevelsButtonClicked() throws IOException {
        menuButtonTasks.LevelsButton();
    }
}
