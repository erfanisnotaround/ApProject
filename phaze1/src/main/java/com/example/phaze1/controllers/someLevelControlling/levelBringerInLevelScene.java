package com.example.phaze1.controllers.someLevelControlling;
import com.example.phaze1.Model.SystemsInfoAndManagers.OneLevelInLevelScene;
import com.example.phaze1.Model.levelLoadingStuff.LevelLoader;
import com.example.phaze1.Model.Constants.constants;
import com.example.phaze1.Model.levelLoadingStuff.level;
import com.example.phaze1.controllers.sceneControllers.GameSceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class levelBringerInLevelScene {
    private ArrayList<level> levels = constants.getLevels();
    private double CenterX = 1707/2;
    private double FirstCenterY = 300;
    private double distanceOFEachList = 150;
    private double distanceFromCenter = 450;
    public ArrayList<OneLevelInLevelScene> givingLevelButtons(){
        ArrayList<OneLevelInLevelScene> buttons = new ArrayList<>();
        for (int i = 0; i < levels.size(); i++) {
            OneLevelInLevelScene newButton = new OneLevelInLevelScene( levels.get(i).isLevelPassed(),i);
            newButton.setPrefHeight(50);
            newButton.setPrefWidth(250);
            newButton.setText(String.valueOf(i+1));
            if (i%2 == 0){
                newButton.setLayoutX(CenterX - distanceFromCenter);
                newButton.setLayoutY(FirstCenterY + i*distanceOFEachList);
            }
            else{
                newButton.setLayoutX(CenterX + distanceFromCenter);
                newButton.setLayoutY(FirstCenterY + i*distanceOFEachList);
            }
            setOnAction(newButton);
            buttons.add(newButton);
        }
        return buttons;
    }
    public void setOnAction(OneLevelInLevelScene button){
        LevelLoader levelLoader = new LevelLoader();
        button.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/phaze1/fxmlFiles/gameScene.fxml"));
            Stage stage = constants.getPrimaryStage();
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GameSceneController gameSceneController = fxmlLoader.getController();
            gameSceneController.setCurrentLevel(button.getLevel());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }
}
