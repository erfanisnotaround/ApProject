package org.example.phaze2.model.constants;

public enum PositionStatus {
    MENU("/org/example/phaze2/fxmlFiles/MenuScene.fxml"),
    SETTINGS("/org/example/phaze2/fxmlFiles/SettingsScene.fxml"),
    LEVELS("/org/example/phaze2/fxmlFiles/LevelsScene.fxml"),
    GAME("/org/example/phaze2/fxmlFiles/GameScene.fxml"),
    AFTER_GAME("/org/example/phaze2/fxmlFiles/GameOVerScene.fxml");

    private String path;
    PositionStatus(String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }
}
