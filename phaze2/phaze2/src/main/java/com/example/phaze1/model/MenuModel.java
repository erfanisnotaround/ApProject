package com.example.phaze1.model;

import com.example.phaze1.model.tasks.MenuButtonTasks;

import java.io.IOException;

public class MenuModel {
    MenuButtonTasks menuButtonTasks = new MenuButtonTasks();
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
