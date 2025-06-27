package org.example.phaze2.model.constants;

public enum PositionStatus {
    MENU("fxmlFiles/MenuScene.fxml"),
    SETTINGS("fxmlFiles/SettingsScene.fxml"),
    LEVELS("fxmlFiles/LevelsScene.fxml"),
    GAME("fxmlFiles/GameScene.fxml"),
    GAME_OVER("fxmlFiles/GameOVerScene.fxml");

    private String path;
    PositionStatus(String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }
}
