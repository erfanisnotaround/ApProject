package org.example.phaze2.model.audio;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;

public interface MusicPlayer extends AutoCloseable {
    void load(String resourcePath);
    void playLoop();
    void pause();
    void resume();
    void stop();
    void setVolume(double v01);         // 0..1
    double getVolume();

    @Override void close();             // same as stop + dispose
}