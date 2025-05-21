package com.example.phaze1.controllers.sceneControllers;

import com.example.phaze1.model.agents.GraphicAgent;
import com.example.phaze1.model.agents.PhotoAgent;
import com.example.phaze1.model.constants.GameStatus;
import com.example.phaze1.model.constants.constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverScreenController implements Initializable {
    GraphicAgent graphicAgent = GraphicAgent.getInstance();
    FXMLLoader fxmlLoader;
    int level  = 0;
    private PhotoAgent Photoagent = new PhotoAgent();
    boolean win = false;
    int goodPockets;
    int badPockets;
    @FXML
    private Label goodPocket, badPocket;
    @FXML
    private Button backToMenu;
    @FXML
    private Button playAgain;
    @FXML
    private ImageView WinOrLose;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        level = constants.getLevel();

        playAgain.setOnAction(event -> {
            try {
                playAgainAction();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        backToMenu.setOnAction(actionEvent -> backToMenuAction());
    }
    public void backToMenuAction() {
        graphicAgent.switchSceneForState(GameStatus.MENU);
    }
    public void playAgainAction() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/phaze1/fxmlFiles/gameScene.fxml"));
        Stage stage = constants.getPrimaryStage();
        Parent root = fxmlLoader.load();
        GameSceneController gameSceneController = fxmlLoader.getController();
        gameSceneController.setCurrentLevel(level);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoodPocketsAndBadPockets(int goodPockets , int badPockets , boolean win ) {
        this.goodPockets = goodPockets;
        this.badPockets = badPockets;
        this.win = win;
        if (!win){
            WinOrLose.setImage(Photoagent.gettingImage("D:/programming/project of Ap/faz 1/Phazes/phaze1/src/main/resources/com/example/phaze1/Images/lose.png"));
        }
        else{
            WinOrLose.setImage(Photoagent.gettingImage("D:/programming/project of Ap/faz 1/Phazes/phaze1/src/main/resources/com/example/phaze1/Images/Win.png"));
        }
        goodPocket.setText(String.valueOf(goodPockets));
        badPocket.setText(String.valueOf(badPockets));

    }
}
