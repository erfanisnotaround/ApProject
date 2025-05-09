package com.example.phaze1.Model.Agents;

import com.example.phaze1.Model.Constants.GameStatus;
import com.example.phaze1.Model.Constants.constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicAgent {
    private static final GraphicAgent instance = new GraphicAgent();
    private final Stage primaryStage = constants.getPrimaryStage();
    private GameStatus currentState;

    private GraphicAgent() {}

    public static GraphicAgent getInstance() {
        return instance;
    }


    public GameStatus getState() {
        return currentState;
    }

    public void setState(GameStatus newState) {
        currentState = newState;
        switchSceneForState(newState);
    }

    private void switchSceneForState(GameStatus state) {
        String fxml;
        String StyleSheet = null;
        switch (state) {
            case MENU:      fxml = "/com/example/phaze1/fxmlFiles/menuScreen.fxml"; break;
            case START_GAME: fxml = "/com/example/phaze1/fxmlFiles/gameScene.fxml"; break;
            case SETTINGS:    fxml = "/com/example/phaze1/fxmlFiles/settingsScene.fxml"; break;
            case LEVELS: fxml = "/com/example/phaze1/fxmlFiles/LevelsScene.fxml"; break;
            default:        throw new IllegalArgumentException("Unknown state: " + state);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            if (StyleSheet != null) {
                scene.getStylesheets().add(getClass().getResource(StyleSheet).toExternalForm());
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
