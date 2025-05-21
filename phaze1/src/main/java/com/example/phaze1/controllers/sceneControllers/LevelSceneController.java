package com.example.phaze1.controllers.sceneControllers;
import com.example.phaze1.controllers.someLevelControlling.levelBringerInLevelScene;
import com.example.phaze1.model.systemsInfoAndManagers.OneLevelInLevelScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LevelSceneController implements Initializable {
    private  levelBringerInLevelScene levelBringerInLevelScene = new levelBringerInLevelScene();
    @FXML
    private AnchorPane MainPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<OneLevelInLevelScene> levelButtons = levelBringerInLevelScene.givingLevelButtons();
        for (OneLevelInLevelScene levelButton : levelButtons) {
            MainPane.getChildren().add(levelButton);
            levelButton.getStyleClass().add("level-icon");
        }

    }
}
