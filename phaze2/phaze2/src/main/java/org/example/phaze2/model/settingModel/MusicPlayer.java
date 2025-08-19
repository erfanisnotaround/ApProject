package org.example.phaze2.model.settingModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;

public interface MusicPlayer {
    void load(String mediaUrl);
    void play();
    void pause();
    void stop();
    void setLoop(boolean loop);
    void setVolume(double volume0to1);
    DoubleProperty volumeProperty();   // bind to a Slider
    BooleanProperty playingProperty(); // observe playing state
    void dispose();
}