package com.example.phaze1.model;

import com.example.phaze1.controllers.GameSceneController;
import com.example.phaze1.controllers.LevelSceneController;
import com.example.phaze1.controllers.SettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class menuButtonTasks {
    FXMLLoader fxmlLoader;
    GraphicAgent graphicAgent = GraphicAgent.getInstance();
    public void StartButton() throws IOException {
//        fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/phaze1/fxmlFiles/gameScene.fxml"));
//        Stage stage = constants.getPrimaryStage();
//        Parent root = fxmlLoader.load();
//        GameSceneController gameSceneController = fxmlLoader.getController();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        graphicAgent.setState(GameStatus.START_GAME);
    }
    public void LevelsButton() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/phaze1/fxmlFiles/LevelsScene.fxml"));
        Stage stage = constants.getPrimaryStage();
        Parent root = fxmlLoader.load();
        LevelSceneController levelSceneController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SettingButton() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/phaze1/fxmlFiles/settingsScene.fxml"));
        Stage stage = constants.getPrimaryStage();
        Parent root = fxmlLoader.load();
        SettingsController settingsController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ExitButton() throws IOException {
        Stage stage = constants.getPrimaryStage();
        stage.close();
    }
}
