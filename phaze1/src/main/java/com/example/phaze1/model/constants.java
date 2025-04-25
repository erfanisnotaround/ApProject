package com.example.phaze1.model;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class constants {
    private static Stage PrimaryStage;
    private static WireManager wireManager = new WireManager(700);
    public static WireManager getWireManager() {
        return wireManager;
    }
    public static void setWireManager(WireManager wireManager) {
        constants.wireManager = wireManager;
    }
    public static  void setPrimaryStage(Stage primarystage) {
        PrimaryStage = primarystage;
    }
    public static Stage getPrimaryStage() {
        return PrimaryStage;
    }
}
