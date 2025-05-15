package com.example.phaze1.controllers.sceneControllers;

import com.example.phaze1.Model.Agents.PhotoAgent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOverScreenController implements Initializable {
    private PhotoAgent Photoagent = new PhotoAgent();
    boolean win = false;
    int goodPockets;
    int badPockets;
    @FXML
    private ImageView WinOrLose;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(win);
        if (!win){
            WinOrLose.setImage(Photoagent.gettingImage("D:/programming/project of Ap/faz 1/Phazes/phaze1/src/main/resources/com/example/phaze1/Images/lose.png"));
        }
        else{
            WinOrLose.setImage(Photoagent.gettingImage("D:/programming/project of Ap/faz 1/Phazes/phaze1/src/main/resources/com/example/phaze1/Images/Win.png"));
        }
    }
    public void setGoodPocketsAndBadPockets(int goodPockets , int badPockets , boolean win ) {
        this.goodPockets = goodPockets;
        this.badPockets = badPockets;
        this.win = win;
    }
}
