package com.example.phaze1.model;

import javafx.stage.Stage;

import java.util.Stack;

public class constants {
    private static Stage PrimaryStage;

    public static  void setPrimaryStage(Stage primarystage) {
        PrimaryStage = primarystage;
    }
    public static Stage getPrimaryStage() {
        return PrimaryStage;
    }
}
