package com.example.phaze1.controllers.sceneControllers;
import com.example.phaze1.Model.Constants.constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class gameOverSceneManager {
    int badPockets;
    int goodPockets;
    boolean WinOrLose;
    public  gameOverSceneManager(int badPockets, int goodPockets, boolean WinOrLose) {
        this.badPockets = badPockets;
        this.goodPockets = goodPockets;
        this.WinOrLose = WinOrLose;
    }
    public void goToAfterGame() throws IOException {
        FXMLLoader Loader = new FXMLLoader(
                getClass().getResource("/com/example/phaze1/fxmlFiles/GameOverScene.fxml")
        );
        Parent root = Loader.load();

        GameOverScreenController controller = new GameOverScreenController();
        controller.setGoodPocketsAndBadPockets(goodPockets , badPockets , WinOrLose);
        Loader.setController(controller);
        Scene scene = new Scene(root);
        Stage stage = constants.getPrimaryStage();
        stage.setScene(scene);
        stage.show();
    }
}
