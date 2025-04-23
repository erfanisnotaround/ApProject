package com.example.phaze1;
import com.example.phaze1.model.PhotoAgent;
import com.example.phaze1.model.constants;
import com.example.phaze1.controllers.menuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;




public class Main extends Application {
    PhotoAgent photoAgent = new PhotoAgent();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setMaximized(true);
        stage.setTitle("BluePrint hell");
//        Image image = photoAgent.gettingImage();
//        stage.getIcons().add(new Image("file:icon.png"));
        constants.setPrimaryStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/menuScreen.fxml"));
        Parent root = fxmlLoader.load();
        menuController menuController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}