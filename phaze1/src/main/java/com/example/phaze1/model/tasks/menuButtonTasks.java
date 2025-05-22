package com.example.phaze1.model.tasks;

import com.example.phaze1.model.agents.GraphicAgent;
import com.example.phaze1.model.constants.constants;
import com.example.phaze1.controllers.sceneControllers.GameSceneController;
import com.example.phaze1.controllers.sceneControllers.LevelSceneController;
import com.example.phaze1.controllers.sceneControllers.SettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class menuButtonTasks {
    FXMLLoader fxmlLoader;
    GraphicAgent graphicAgent = GraphicAgent.getInstance();
    public void StartButton() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/phaze1/fxmlFiles/gameScene.fxml"));
        Stage stage = constants.getPrimaryStage();
        Parent root = fxmlLoader.load();
        GameSceneController gameSceneController = fxmlLoader.getController();
        gameSceneController.setCurrentLevel(lastLevel());
        gameSceneController.initialize();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        graphicAgent.setState(GameStatus.START_GAME);
    }
    public int lastLevel() {
        for (int i = 0 ; i < constants.getLevels().size(); i++) {
            if (!constants.getLevels().get(i).isLevelPassed()){
                return i;
            }
        }
        return 0;
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
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        Parent root = fxmlLoader.load();
        SettingsController settingsController = fxmlLoader.getController();
        settingsController.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ExitButton() throws IOException {
        Stage stage = constants.getPrimaryStage();
        stage.close();
    }
}
