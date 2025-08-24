package org.example.phaze2.controllers.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import org.example.phaze2.controllers.factories.LevelButtonFactory;
import org.example.phaze2.model.AppContext;
import org.example.phaze2.model.agentsAndManagers.SceneManager;
import org.example.phaze2.model.controllersInterfaces.ControlledScreen;
import org.example.phaze2.model.controllersInterfaces.Maker;
import org.example.phaze2.model.jsonRefrencesAndLOadings.Level;
import org.example.phaze2.model.sceneModel.LevelsModel;

import java.util.List;

public class LevelController implements Maker, ControlledScreen {
    private SceneManager sceneManager;
    private final LevelsModel levelsModel = new LevelsModel();
    private AppContext appContext;

    @FXML private Button BackButton;
    @FXML private TilePane levelsPane;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        levelsModel.setSceneManager(sceneManager);
        tryRenderLevels();
    }

    @Override
    public void MakeFirst() {
        BackButton.setOnAction(e -> BackButtonOnAction());
        levelsPane.setHgap(12);
        levelsPane.setVgap(12);
        levelsPane.setPrefColumns(4);
    }

    @Override
    public void PassContext(AppContext appContext) {
        this.appContext = appContext;
        levelsModel.setAppContext(appContext);
        tryRenderLevels();
    }

    void BackButtonOnAction() {
        levelsModel.BackButtonOnAction();
    }

    private void tryRenderLevels() {
        if (sceneManager == null || appContext == null || levelsPane == null) return;

        List<Level> descriptors = appContext.getLevels();
        if (descriptors != null && !descriptors.isEmpty()) {
            renderDescriptors(descriptors);

        }


    }

    private void renderDescriptors(List<Level> levels) {
        levelsPane.getChildren().clear();
        levels.forEach(ld ->
                levelsPane.getChildren().add(
                        LevelButtonFactory.create(levels.indexOf(ld) , levelsModel::onLevelSelected)
                )
        );
    }

    private void renderIds(List<Integer> levelIds) {
        levelsPane.getChildren().clear();
        levelIds.forEach(id ->
                levelsPane.getChildren().add(
                        LevelButtonFactory.create(id, levelsModel::onLevelSelected)
                )
        );
    }
}
