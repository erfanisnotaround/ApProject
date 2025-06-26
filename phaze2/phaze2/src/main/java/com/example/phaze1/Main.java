package com.example.phaze1;
import com.example.phaze1.model.agents.PhotoAgent;
import com.example.phaze1.model.constants.Constants;
import com.example.phaze1.controllers.sceneControllers.menuController;
import com.example.phaze1.model.tasks.sideTasks;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.awt.*;
import java.io.IOException;




public class Main extends Application {
    PhotoAgent photoAgent = new PhotoAgent();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setMaximized(true);
        stage.setTitle("BluePrint hell");
        Image image = photoAgent.gettingImage("src/main/resources/com/example/phaze1/Images/horns.png");
        stage.getIcons().add(image);
        stage.initStyle(StageStyle.UNDECORATED);
        Constants.getInstance().setPrimaryStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/menuScreen.fxml"));
        Parent root = fxmlLoader.load();
        menuController menuController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Platform.runLater(() -> {
            Platform.runLater(() -> {
                try {
                    sideTasks.minimizeOthersOnWindows();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    public static void main(String[] args) {
        launch();
    }
}