package org.example.phaze2;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.LevelOfGameLoader;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.audio.JavaSoundMusicPlayer;
import org.example.phaze2.model.audio.MusicService;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.settingModel.FileSettingsRepository;
import org.example.phaze2.model.settingModel.KeyBindingManager;
import org.example.phaze2.model.settingModel.SettingsService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("BLUE PRINT HELL");
//        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        var repo = new FileSettingsRepository("D:\\programming\\project of Ap\\faz 1\\Phazes\\phaze2\\phaze2\\src\\main\\resources\\org\\example\\phaze2\\jsonFiles\\settings.Json");
        var settings = new SettingsService(repo);
        var keyMgr   = new KeyBindingManager(settings);
        keyMgr.syncSceneActionsFromSettings();   // seed enum at app start

        var music = new MusicService(settings , new JavaSoundMusicPlayer());

        var levelLoader = new LevelOfGameLoader();
        List<Level> levels = levelLoader.getLevels();
        var ctx = new AppContext(settings, keyMgr , music , levelLoader , levels );
        SceneManager sceneManager = new SceneManager(primaryStage , ctx);
        sceneManager.switchScreen(PositionStatus.MENU);


        primaryStage.show();

        Constants.getInstance().setPrimaryStage(primaryStage);
        Constants.getInstance().setSceneManager(sceneManager);
        
    }
}
