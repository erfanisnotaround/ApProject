package org.example.phaze2.model.sceneModel;

import org.example.phaze2.controllers.moverController.moveRelated.WholeMovement;
import org.example.phaze2.model.GoingToGamaInformation;
import org.example.phaze2.model.constants.PositionStatus;
import org.example.phaze2.model.agentsAndManagers.SceneManager;

public class GameModel {
    private SceneManager sceneManager;
    private GoingToGamaInformation levelInformation;
    private double AvailableNeededTime;
    private final double basicMoveMultiplier = 1;
    private final double basicTempoMultiplier = 15;
    private final double moveUnitOfSlider = 100;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    public void MenuButtonClicked() {
        sceneManager.switchScreen(PositionStatus.MENU);
    }
    public void StartButtonClicked() {

    }


    public GoingToGamaInformation getLevelInformation() {
        return levelInformation;
    }

    public void setLevelInformation(GoingToGamaInformation levelInformation) {
        this.levelInformation = levelInformation;
        this.AvailableNeededTime = levelInformation.getLevel().getAvailableTime();
    }
    public void SetAvailableNeededTime(double availableNeededTime) {
        this.AvailableNeededTime = availableNeededTime;
    }
    public double getAvailableNeededTime() {
        return AvailableNeededTime;
    }
    public double getBasicMoveMultiplier() {
        return basicMoveMultiplier;
    }

    public double getMoveUnitOfSlider() {
        return moveUnitOfSlider;
    }

    public double getBasicTempoMultiplier() {
        return basicTempoMultiplier;
    }
}
