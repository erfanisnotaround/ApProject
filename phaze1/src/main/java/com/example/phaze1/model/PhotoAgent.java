package com.example.phaze1.model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.Objects;

public class PhotoAgent {
    public Image gettingImage(String address) {
        File file = new File(Objects.requireNonNull(getClass().getResource(address)).getFile());
        return new Image(file.toURI().toString());
    }
}
