package com.example.phaze1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to J this opfoabf[" +
                "[a  f[oaif avaFX Application!");
    }
}