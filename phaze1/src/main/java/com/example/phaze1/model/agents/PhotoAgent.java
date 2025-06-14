package com.example.phaze1.model.agents;

import javafx.scene.image.Image;

import java.io.File;

public class PhotoAgent {
    public Image gettingImage(String address) {
//        File file = new File(Objects.requireNonNull(getClass().getResource(address)).getFile());
        File file = new File(address);

        return new Image(file.toURI().toString());
    }
}
